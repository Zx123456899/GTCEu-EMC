package com.gregtechceu.gtceuemc.emc;

import com.gregtechceu.gtceuemc.GTCEuEMC;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MachineEMCGenerator {

    private static final Map<String, Long> STEAM_MACHINE_EMC = new HashMap<>();
    private static final Map<String, Long> ELECTRIC_MACHINE_BASE_EMC = new HashMap<>();
    private static final Map<String, Long> MULTIBLOCK_MACHINE_EMC = new HashMap<>();

    static {
        STEAM_MACHINE_EMC.put("steam_furnace", 512L);
        STEAM_MACHINE_EMC.put("steam_boiler", 1024L);
        STEAM_MACHINE_EMC.put("steam_compressor", 1024L);
        STEAM_MACHINE_EMC.put("steam_extractor", 1024L);
        STEAM_MACHINE_EMC.put("steam_packer", 1024L);
        STEAM_MACHINE_EMC.put("steam_cutter", 1024L);
        STEAM_MACHINE_EMC.put("steam_bender", 1024L);
        STEAM_MACHINE_EMC.put("steam_assembler", 2048L);
        STEAM_MACHINE_EMC.put("steam_macerator", 1024L);
        STEAM_MACHINE_EMC.put("steam_sifter", 1024L);
        STEAM_MACHINE_EMC.put("steam_centrifuge", 2048L);
        STEAM_MACHINE_EMC.put("steam_blast_furnace", 2048L);
        STEAM_MACHINE_EMC.put("steam_electrolyzer", 2048L);
        STEAM_MACHINE_EMC.put("steam_chemical_reactor", 2048L);
        STEAM_MACHINE_EMC.put("steam_distillery", 2048L);
        STEAM_MACHINE_EMC.put("steam_cooler", 1024L);
        STEAM_MACHINE_EMC.put("steam_heater", 1024L);
        STEAM_MACHINE_EMC.put("steam_turbine", 4096L);
        STEAM_MACHINE_EMC.put("steam_engine", 4096L);

        ELECTRIC_MACHINE_BASE_EMC.put("electric_furnace", 512L);
        ELECTRIC_MACHINE_BASE_EMC.put("macerator", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("extractor", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("compressor", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("packer", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("cutter", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("bender", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("assembler", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("sifter", 1024L);
        ELECTRIC_MACHINE_BASE_EMC.put("centrifuge", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("blast_furnace", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("electrolyzer", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("chemical_reactor", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("distillery", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("autoclave", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("implosion_compressor", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("thermal_centrifuge", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("fusion_reactor", 1048576L);
        ELECTRIC_MACHINE_BASE_EMC.put("quantum_tank", 131072L);
        ELECTRIC_MACHINE_BASE_EMC.put("creative_chest", 1048576L);
        ELECTRIC_MACHINE_BASE_EMC.put("quantum_chest", 131072L);
        ELECTRIC_MACHINE_BASE_EMC.put("item_collector", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("item_dispenser", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("fluid_collector", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("fluid_dispenser", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("electric_pump", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("electric_boiler", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("diesel_engine", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("gas_turbine", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("steam_turbine", 16384L);
        ELECTRIC_MACHINE_BASE_EMC.put("thermal_turbine", 32768L);
        ELECTRIC_MACHINE_BASE_EMC.put("plasma_turbine", 65536L);
        ELECTRIC_MACHINE_BASE_EMC.put("solar_panel", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("nuclear_reactor", 262144L);
        ELECTRIC_MACHINE_BASE_EMC.put("transformer", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("substation", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("capacitor_bank", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("induction_coil", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("circuit_assembler", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("wiremill", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("extruder", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("rolling_machine", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("forge_hammer", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("wire_cutter", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("plate_bender", 2048L);
        ELECTRIC_MACHINE_BASE_EMC.put("grinder", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("laser_engraver", 8192L);
        ELECTRIC_MACHINE_BASE_EMC.put("lathe", 4096L);
        ELECTRIC_MACHINE_BASE_EMC.put("cnc_machine", 16384L);

        MULTIBLOCK_MACHINE_EMC.put("electric_blast_furnace", 65536L);
        MULTIBLOCK_MACHINE_EMC.put("large_chemical_reactor", 65536L);
        MULTIBLOCK_MACHINE_EMC.put("large_distillery", 32768L);
        MULTIBLOCK_MACHINE_EMC.put("large_autoclave", 32768L);
        MULTIBLOCK_MACHINE_EMC.put("large_centrifuge", 65536L);
        MULTIBLOCK_MACHINE_EMC.put("large_electrolyzer", 65536L);
        MULTIBLOCK_MACHINE_EMC.put("large_implosion_compressor", 131072L);
        MULTIBLOCK_MACHINE_EMC.put("large_thermal_centrifuge", 131072L);
        MULTIBLOCK_MACHINE_EMC.put("pyrolyse_oven", 32768L);
        MULTIBLOCK_MACHINE_EMC.put("vacuum_freezer", 32768L);
        MULTIBLOCK_MACHINE_EMC.put("cryogenic_chamber", 65536L);
        MULTIBLOCK_MACHINE_EMC.put("fusion_reactor", 1048576L);
        MULTIBLOCK_MACHINE_EMC.put("solar_array", 262144L);
        MULTIBLOCK_MACHINE_EMC.put("nuclear_reactor", 524288L);
        MULTIBLOCK_MACHINE_EMC.put("quantum_entangloporter", 262144L);
    }

    private static final long[] TIER_MULTIPLIERS = {
            1L,    // ULV
            2L,    // LV
            4L,    // MV
            8L,    // HV
            16L,   // EV
            32L,   // IV
            64L,   // LuV
            128L,  // ZPM
            256L,  // UV
            512L,  // UHV
            1024L, // UEV
            2048L, // UIV
            4096L, // UXV
            8192L, // OpV
            16384L // MAX
    };

    public static void generateAndRegisterAll() {
        EMCRegistry registry = EMCRegistry.getInstance();

        try {
            Class<?> gtrRegistries = Class.forName("com.gregtechceu.gtceu.api.registry.GTRegistries");
            java.lang.reflect.Field machinesField = gtrRegistries.getField("MACHINES");
            Object machinesRegistry = machinesField.get(null);

            Iterator<?> iterator = ((Iterable<?>) machinesRegistry).iterator();
            while (iterator.hasNext()) {
                MachineDefinition definition = (MachineDefinition) iterator.next();
                String name = definition.getId().getPath().toLowerCase();
                int tier = definition.getTier();

                long baseEMC = 0;

                if (STEAM_MACHINE_EMC.containsKey(name)) {
                    baseEMC = STEAM_MACHINE_EMC.get(name);
                } else if (ELECTRIC_MACHINE_BASE_EMC.containsKey(name)) {
                    baseEMC = ELECTRIC_MACHINE_BASE_EMC.get(name);
                    if (tier >= 0 && tier < TIER_MULTIPLIERS.length) {
                        baseEMC *= TIER_MULTIPLIERS[tier];
                    }
                } else if (MULTIBLOCK_MACHINE_EMC.containsKey(name)) {
                    baseEMC = MULTIBLOCK_MACHINE_EMC.get(name);
                    if (tier >= 0 && tier < TIER_MULTIPLIERS.length) {
                        baseEMC *= TIER_MULTIPLIERS[tier];
                    }
                } else {
                    baseEMC = calculateTieredMachineEMC(tier);
                }

                if (baseEMC > 0) {
                    Item item = definition.getItem();
                    if (item != null) {
                        registry.registerItemEMC(item, baseEMC);
                    }
                }
            }
        } catch (Exception e) {
            GTCEuEMC.LOGGER.warn("Failed to generate machine EMC: {}", e.getMessage());
        }
    }

    private static long calculateTieredMachineEMC(int tier) {
        if (tier < 0 || tier >= GTValues.TIER_COUNT) {
            return 1024L;
        }
        return 1024L * (1L << tier);
    }
}