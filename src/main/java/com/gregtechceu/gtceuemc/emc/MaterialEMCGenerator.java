package com.gregtechceu.gtceuemc.emc;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.GTRegistries;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MaterialEMCGenerator {

    private static final Map<String, Long> ELEMENT_EMC_BASE = new LinkedHashMap<>();

    static {
        ELEMENT_EMC_BASE.put("iron", 256L);
        ELEMENT_EMC_BASE.put("gold", 2048L);
        ELEMENT_EMC_BASE.put("copper", 128L);
        ELEMENT_EMC_BASE.put("tin", 512L);
        ELEMENT_EMC_BASE.put("lead", 512L);
        ELEMENT_EMC_BASE.put("silver", 4096L);
        ELEMENT_EMC_BASE.put("platinum", 32768L);
        ELEMENT_EMC_BASE.put("titanium", 8192L);
        ELEMENT_EMC_BASE.put("chrome", 8192L);
        ELEMENT_EMC_BASE.put("zinc", 256L);
        ELEMENT_EMC_BASE.put("nickel", 2048L);
        ELEMENT_EMC_BASE.put("cobalt", 4096L);
        ELEMENT_EMC_BASE.put("manganese", 1024L);
        ELEMENT_EMC_BASE.put("magnesium", 128L);
        ELEMENT_EMC_BASE.put("aluminum", 128L);
        ELEMENT_EMC_BASE.put("tungsten", 16384L);
        ELEMENT_EMC_BASE.put("tantalum", 16384L);
        ELEMENT_EMC_BASE.put("niobium", 8192L);
        ELEMENT_EMC_BASE.put("hafnium", 16384L);
        ELEMENT_EMC_BASE.put("rhenium", 65536L);
        ELEMENT_EMC_BASE.put("osmium", 65536L);
        ELEMENT_EMC_BASE.put("iridium", 131072L);
        ELEMENT_EMC_BASE.put("ruthenium", 32768L);
        ELEMENT_EMC_BASE.put("rhodium", 32768L);
        ELEMENT_EMC_BASE.put("palladium", 16384L);
        ELEMENT_EMC_BASE.put("beryllium", 4096L);
        ELEMENT_EMC_BASE.put("boron", 2048L);
        ELEMENT_EMC_BASE.put("carbon", 64L);
        ELEMENT_EMC_BASE.put("silicon", 64L);
        ELEMENT_EMC_BASE.put("sulfur", 64L);
        ELEMENT_EMC_BASE.put("phosphorus", 128L);
        ELEMENT_EMC_BASE.put("oxygen", 16L);
        ELEMENT_EMC_BASE.put("nitrogen", 16L);
        ELEMENT_EMC_BASE.put("hydrogen", 8L);
        ELEMENT_EMC_BASE.put("helium", 512L);
        ELEMENT_EMC_BASE.put("lithium", 256L);
        ELEMENT_EMC_BASE.put("sodium", 128L);
        ELEMENT_EMC_BASE.put("potassium", 128L);
        ELEMENT_EMC_BASE.put("calcium", 128L);
        ELEMENT_EMC_BASE.put("scandium", 8192L);
        ELEMENT_EMC_BASE.put("vanadium", 4096L);
        ELEMENT_EMC_BASE.put("chromium", 8192L);
        ELEMENT_EMC_BASE.put("gallium", 4096L);
        ELEMENT_EMC_BASE.put("germanium", 4096L);
        ELEMENT_EMC_BASE.put("arsenic", 2048L);
        ELEMENT_EMC_BASE.put("selenium", 2048L);
        ELEMENT_EMC_BASE.put("bromine", 256L);
        ELEMENT_EMC_BASE.put("krypton", 1024L);
        ELEMENT_EMC_BASE.put("rubidium", 2048L);
        ELEMENT_EMC_BASE.put("strontium", 1024L);
        ELEMENT_EMC_BASE.put("yttrium", 8192L);
        ELEMENT_EMC_BASE.put("zirconium", 8192L);
        ELEMENT_EMC_BASE.put("molybdenum", 8192L);
        ELEMENT_EMC_BASE.put("technetium", 32768L);
        ELEMENT_EMC_BASE.put("cadmium", 512L);
        ELEMENT_EMC_BASE.put("indium", 4096L);
        ELEMENT_EMC_BASE.put("antimony", 2048L);
        ELEMENT_EMC_BASE.put("tellurium", 4096L);
        ELEMENT_EMC_BASE.put("iodine", 512L);
        ELEMENT_EMC_BASE.put("xenon", 1024L);
        ELEMENT_EMC_BASE.put("cesium", 2048L);
        ELEMENT_EMC_BASE.put("barium", 1024L);
        ELEMENT_EMC_BASE.put("lanthanum", 8192L);
        ELEMENT_EMC_BASE.put("cerium", 8192L);
        ELEMENT_EMC_BASE.put("praseodymium", 8192L);
        ELEMENT_EMC_BASE.put("neodymium", 8192L);
        ELEMENT_EMC_BASE.put("promethium", 32768L);
        ELEMENT_EMC_BASE.put("samarium", 8192L);
        ELEMENT_EMC_BASE.put("europium", 8192L);
        ELEMENT_EMC_BASE.put("gadolinium", 8192L);
        ELEMENT_EMC_BASE.put("terbium", 8192L);
        ELEMENT_EMC_BASE.put("dysprosium", 8192L);
        ELEMENT_EMC_BASE.put("holmium", 8192L);
        ELEMENT_EMC_BASE.put("erbium", 8192L);
        ELEMENT_EMC_BASE.put("thulium", 8192L);
        ELEMENT_EMC_BASE.put("ytterbium", 8192L);
        ELEMENT_EMC_BASE.put("lutetium", 8192L);
        ELEMENT_EMC_BASE.put("mercury", 512L);
        ELEMENT_EMC_BASE.put("thallium", 2048L);
        ELEMENT_EMC_BASE.put("bismuth", 512L);
        ELEMENT_EMC_BASE.put("polonium", 65536L);
        ELEMENT_EMC_BASE.put("astatine", 65536L);
        ELEMENT_EMC_BASE.put("radon", 65536L);
        ELEMENT_EMC_BASE.put("francium", 131072L);
        ELEMENT_EMC_BASE.put("radium", 131072L);
        ELEMENT_EMC_BASE.put("actinium", 131072L);
        ELEMENT_EMC_BASE.put("thorium", 16384L);
        ELEMENT_EMC_BASE.put("protactinium", 131072L);
        ELEMENT_EMC_BASE.put("uranium", 65536L);
        ELEMENT_EMC_BASE.put("neptunium", 131072L);
        ELEMENT_EMC_BASE.put("plutonium", 131072L);
        ELEMENT_EMC_BASE.put("americium", 131072L);
        ELEMENT_EMC_BASE.put("curium", 131072L);
        ELEMENT_EMC_BASE.put("berkelium", 131072L);
        ELEMENT_EMC_BASE.put("californium", 131072L);
        ELEMENT_EMC_BASE.put("einsteinium", 131072L);
        ELEMENT_EMC_BASE.put("fermium", 131072L);
        ELEMENT_EMC_BASE.put("mendelevium", 131072L);
        ELEMENT_EMC_BASE.put("nobelium", 131072L);
        ELEMENT_EMC_BASE.put("lawrencium", 131072L);
        ELEMENT_EMC_BASE.put("boron_carbide", 8192L);
        ELEMENT_EMC_BASE.put("graphite", 64L);
        ELEMENT_EMC_BASE.put("diamond", 16384L);
        ELEMENT_EMC_BASE.put("lapis", 256L);
        ELEMENT_EMC_BASE.put("coal", 64L);
        ELEMENT_EMC_BASE.put("charcoal", 32L);
        ELEMENT_EMC_BASE.put("salt", 32L);
        ELEMENT_EMC_BASE.put("sand", 16L);
        ELEMENT_EMC_BASE.put("clay", 16L);
        ELEMENT_EMC_BASE.put("redstone", 256L);
        ELEMENT_EMC_BASE.put("glowstone", 512L);
        ELEMENT_EMC_BASE.put("quartz", 512L);
        ELEMENT_EMC_BASE.put("ender_pearl", 4096L);
        ELEMENT_EMC_BASE.put("nether_star", 131072L);
        ELEMENT_EMC_BASE.put("dragon_egg", 1048576L);
    }

    private static final Map<String, Long> ALLOY_EMC_MULTIPLIERS = new HashMap<>();

    static {
        ALLOY_EMC_MULTIPLIERS.put("steel", 3L);
        ALLOY_EMC_MULTIPLIERS.put("bronze", 2L);
        ALLOY_EMC_MULTIPLIERS.put("brass", 2L);
        ALLOY_EMC_MULTIPLIERS.put("invar", 4L);
        ALLOY_EMC_MULTIPLIERS.put("constantan", 4L);
        ALLOY_EMC_MULTIPLIERS.put("electrum", 4L);
        ALLOY_EMC_MULTIPLIERS.put("enderium", 8L);
        ALLOY_EMC_MULTIPLIERS.put("titanium_steel", 8L);
        ALLOY_EMC_MULTIPLIERS.put("tungsten_steel", 8L);
        ALLOY_EMC_MULTIPLIERS.put("chrome_steel", 4L);
        ALLOY_EMC_MULTIPLIERS.put("nichrome", 6L);
        ALLOY_EMC_MULTIPLIERS.put("cupronickel", 3L);
        ALLOY_EMC_MULTIPLIERS.put("aluminum_bronze", 3L);
        ALLOY_EMC_MULTIPLIERS.put("stainless_steel", 4L);
    }

    private final Map<String, Long> customElementEMC = new ConcurrentHashMap<>();
    private final Map<String, Long> customMaterialEMC = new ConcurrentHashMap<>();

    public void registerCustomElementEMC(String elementName, long emc) {
        customElementEMC.put(elementName.toLowerCase(), emc);
    }

    public void registerCustomMaterialEMC(String materialName, long emc) {
        customMaterialEMC.put(materialName.toLowerCase(), emc);
    }

    public long calculateMaterialEMC(Material material) {
        String name = material.getName().toLowerCase();

        if (customMaterialEMC.containsKey(name)) {
            return customMaterialEMC.get(name);
        }

        if (material.hasFlag(MaterialFlags.NO_UNIFICATION)) {
            return 0L;
        }

        if (material.hasFlag(MaterialFlags.DISABLE_MATERIAL_RECIPES)) {
            return 0L;
        }

        if (!material.getMaterialComponents().isEmpty()) {
            return calculateAlloyEMC(material);
        }

        if (material.isElement()) {
            Element element = material.getElement();
            if (element != null) {
                String elementName = element.name().toLowerCase();
                if (customElementEMC.containsKey(elementName)) {
                    return customElementEMC.get(elementName);
                }
                if (ELEMENT_EMC_BASE.containsKey(elementName)) {
                    return ELEMENT_EMC_BASE.get(elementName);
                }
                if (ELEMENT_EMC_BASE.containsKey(name)) {
                    return ELEMENT_EMC_BASE.get(name);
                }
            }
        }

        if (ELEMENT_EMC_BASE.containsKey(name)) {
            return ELEMENT_EMC_BASE.get(name);
        }

        if (material.hasProperty(PropertyKey.GEM)) {
            return 2048L;
        }

        if (material.hasProperty(PropertyKey.ORE)) {
            return 128L;
        }

        if (material.hasProperty(PropertyKey.DUST)) {
            return 64L;
        }

        if (material.hasProperty(PropertyKey.INGOT)) {
            return 256L;
        }

        if (material.hasProperty(PropertyKey.WOOD)) {
            return 32L;
        }

        if (material.hasProperty(PropertyKey.FLUID)) {
            return 16L;
        }

        return 64L;
    }

    private long calculateAlloyEMC(Material material) {
        String name = material.getName().toLowerCase();
        long totalEMC = 0;

        for (MaterialStack component : material.getMaterialComponents()) {
            Material componentMat = component.material();
            long componentAmount = component.amount();
            long componentEMC = calculateMaterialEMC(componentMat);
            totalEMC += componentEMC * componentAmount;
        }

        long divisor = material.getMaterialComponents().stream()
                .mapToLong(MaterialStack::amount)
                .sum();

        if (divisor > 0) {
            totalEMC /= divisor;
        }

        if (ALLOY_EMC_MULTIPLIERS.containsKey(name)) {
            totalEMC *= ALLOY_EMC_MULTIPLIERS.get(name);
        } else {
            totalEMC = (long) (totalEMC * 1.5);
        }

        return Math.max(totalEMC, 16L);
    }

    public void generateAndRegisterAll() {
        EMCRegistry registry = EMCRegistry.getInstance();

        for (Material material : GTRegistries.MATERIALS) {
            long emc = calculateMaterialEMC(material);
            if (emc > 0) {
                registry.registerMaterialEMC(material, emc);
            }
        }

        registry.registerPrefixMultiplier(TagPrefix.ingot, 1L);
        registry.registerPrefixMultiplier(TagPrefix.nugget, 1L);
        registry.registerPrefixMultiplier(TagPrefix.block, 9L);
        registry.registerPrefixMultiplier(TagPrefix.dust, 1L);
        registry.registerPrefixMultiplier(TagPrefix.dustSmall, 1L);
        registry.registerPrefixMultiplier(TagPrefix.dustTiny, 1L);
        registry.registerPrefixMultiplier(TagPrefix.gem, 1L);
        registry.registerPrefixMultiplier(TagPrefix.gemChipped, 1L);
        registry.registerPrefixMultiplier(TagPrefix.gemFlawed, 1L);
        registry.registerPrefixMultiplier(TagPrefix.gemFlawless, 2L);
        registry.registerPrefixMultiplier(TagPrefix.gemExquisite, 4L);
        registry.registerPrefixMultiplier(TagPrefix.rawOre, 1L);
        registry.registerPrefixMultiplier(TagPrefix.rawOreBlock, 9L);
        registry.registerPrefixMultiplier(TagPrefix.crushed, 1L);
        registry.registerPrefixMultiplier(TagPrefix.crushedRefined, 2L);
        registry.registerPrefixMultiplier(TagPrefix.crushedPurified, 4L);
        registry.registerPrefixMultiplier(TagPrefix.plate, 1L);
        registry.registerPrefixMultiplier(TagPrefix.plateDouble, 2L);
        registry.registerPrefixMultiplier(TagPrefix.plateDense, 4L);
        registry.registerPrefixMultiplier(TagPrefix.wireFine, 1L);
        registry.registerPrefixMultiplier(TagPrefix.rod, 1L);
        registry.registerPrefixMultiplier(TagPrefix.gear, 4L);
        registry.registerPrefixMultiplier(TagPrefix.gearSmall, 1L);
        registry.registerPrefixMultiplier(TagPrefix.screw, 1L);
        registry.registerPrefixMultiplier(TagPrefix.bolt, 2L);
        registry.registerPrefixMultiplier(TagPrefix.spring, 2L);
        registry.registerPrefixMultiplier(TagPrefix.springSmall, 1L);
        registry.registerPrefixMultiplier(TagPrefix.frameGt, 4L);
        registry.registerPrefixMultiplier(TagPrefix.foil, 1L);
        registry.registerPrefixMultiplier(TagPrefix.ingotHot, 1L);
        registry.registerPrefixMultiplier(TagPrefix.rotor, 6L);
        registry.registerPrefixMultiplier(TagPrefix.turbineBlade, 4L);
        registry.registerPrefixMultiplier(TagPrefix.lens, 2L);

        registry.registerAllMaterialItems();
    }
}