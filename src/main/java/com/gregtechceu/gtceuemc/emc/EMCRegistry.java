package com.gregtechceu.gtceuemc.emc;

import com.gregtechceu.gtceuemc.GTCEuEMC;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLLoader;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EMCRegistry {

    private static final EMCRegistry INSTANCE = new EMCRegistry();

    private final Object2LongMap<Item> itemEMC = new Object2LongOpenHashMap<>();
    private final Map<Material, Long> materialEMC = new ConcurrentHashMap<>();
    private final Map<TagPrefix, Long> prefixMultipliers = new ConcurrentHashMap<>();

    private volatile boolean frozen = false;

    private EMCRegistry() {}

    public static EMCRegistry getInstance() {
        return INSTANCE;
    }

    public void unfreeze() {
        this.frozen = false;
    }

    public void freeze() {
        this.frozen = true;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void registerItemEMC(Item item, long emc) {
        if (frozen) {
            throw new IllegalStateException("EMC registry is frozen!");
        }
        itemEMC.put(item, emc);
    }

    public void registerItemEMC(ItemStack stack, long emc) {
        registerItemEMC(stack.getItem(), emc);
    }

    public void registerMaterialEMC(Material material, long emc) {
        if (frozen) {
            throw new IllegalStateException("EMC registry is frozen!");
        }
        materialEMC.put(material, emc);
    }

    public void registerPrefixMultiplier(TagPrefix prefix, long multiplier) {
        if (frozen) {
            throw new IllegalStateException("EMC registry is frozen!");
        }
        prefixMultipliers.put(prefix, multiplier);
    }

    public long getItemEMC(Item item) {
        return itemEMC.getOrDefault(item, 0L);
    }

    public long getItemEMC(ItemStack stack) {
        long baseEMC = getItemEMC(stack.getItem());
        if (baseEMC == 0) return 0;
        return baseEMC * stack.getCount();
    }

    public long getMaterialEMC(Material material) {
        return materialEMC.getOrDefault(material, 0L);
    }

    public long getPrefixMultiplier(TagPrefix prefix) {
        return prefixMultipliers.getOrDefault(prefix, 1L);
    }

    public long getMaterialItemEMC(TagPrefix prefix, Material material) {
        ItemStack stack = ChemicalHelper.get(prefix, material);
        if (stack.isEmpty()) return 0;

        if (itemEMC.containsKey(stack.getItem())) {
            return itemEMC.get(stack.getItem());
        }

        long materialEMC = getMaterialEMC(material);
        if (materialEMC == 0) return 0;

        long amount = prefix.materialAmount();
        if (amount <= 0) amount = GTValues.M;

        long multiplier = getPrefixMultiplier(prefix);
        double ratio = (double) amount / GTValues.M;
        return (long) (materialEMC * ratio * multiplier);
    }

    public Object2LongMap<Item> getItemEMCMapping() {
        return itemEMC;
    }

    public void clear() {
        if (frozen) {
            throw new IllegalStateException("EMC registry is frozen!");
        }
        itemEMC.clear();
        materialEMC.clear();
        prefixMultipliers.clear();
    }

    public void applyToProjectE() {
        if (!isProjectELoaded()) {
            return;
        }

        try {
            Class<?> projectEApi = Class.forName("moze_intel.projecte.api.ProjectEAPI");
            java.lang.reflect.Method getEMCProxy = projectEApi.getMethod("getEMCProxy");
            Object proxy = getEMCProxy.invoke(null);

            java.lang.reflect.Method registerCustomEMC = proxy.getClass().getMethod("registerCustomEMC", ItemStack.class, long.class);

            for (Object2LongMap.Entry<Item> entry : itemEMC.object2LongEntrySet()) {
                Item item = entry.getKey();
                long emc = entry.getLongValue();
                if (emc > 0) {
                    registerCustomEMC.invoke(proxy, new ItemStack(item), emc);
                }
            }
        } catch (Exception e) {
            GTCEuEMC.LOGGER.warn("Failed to register EMC values with ProjectE: {}", e.getMessage());
        }
    }

    public void registerAllMaterialItems() {
        try {
            Class<?> gtrRegistries = Class.forName("com.gregtechceu.gtceu.api.registry.GTRegistries");
            Field materialsField = gtrRegistries.getField("MATERIALS");
            Object materialsRegistry = materialsField.get(null);
            
            Field tagPrefixesField = gtrRegistries.getField("TAG_PREFIXES");
            Object tagPrefixesRegistry = tagPrefixesField.get(null);

            Iterator<?> materialsIterator = ((Iterable<?>) materialsRegistry).iterator();
            while (materialsIterator.hasNext()) {
                Material material = (Material) materialsIterator.next();
                long matEMC = getMaterialEMC(material);
                if (matEMC <= 0) continue;

                Iterator<?> prefixesIterator = ((Iterable<?>) tagPrefixesRegistry).iterator();
                while (prefixesIterator.hasNext()) {
                    TagPrefix prefix = (TagPrefix) prefixesIterator.next();
                    ItemStack stack = ChemicalHelper.get(prefix, material);
                    if (!stack.isEmpty() && !itemEMC.containsKey(stack.getItem())) {
                        long itemEMC = getMaterialItemEMC(prefix, material);
                        if (itemEMC > 0) {
                            registerItemEMC(stack.getItem(), itemEMC);
                        }
                    }
                }
            }
        } catch (Exception e) {
            GTCEuEMC.LOGGER.warn("Failed to register material items: {}", e.getMessage());
        }
    }

    private boolean isProjectELoaded() {
        return FMLLoader.getLoadingModList().getModFileById("projecte") != null;
    }
}