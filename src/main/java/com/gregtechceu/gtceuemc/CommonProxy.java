package com.gregtechceu.gtceuemc;

import com.gregtechceu.gtceuemc.emc.EMCRegistry;
import com.gregtechceu.gtceuemc.emc.MaterialEMCGenerator;
import com.gregtechceu.gtceuemc.emc.MachineEMCGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod.EventBusSubscriber(modid = GTCEuEMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(GTCEuEMC.MOD_ID);
    
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(CommonProxy::commonSetup);
        MinecraftForge.EVENT_BUS.register(new CommonProxy());
    }
    
    private static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (!isProjectELoaded()) {
                LOGGER.info("ProjectE not loaded, skipping EMC registration");
                return;
            }
            
            LOGGER.info("Initializing GTCEu EMC registry...");
            
            EMCRegistry registry = EMCRegistry.getInstance();
            registry.unfreeze();
            
            MaterialEMCGenerator materialGenerator = new MaterialEMCGenerator();
            materialGenerator.generateAndRegisterAll();
            
            MachineEMCGenerator machineGenerator = new MachineEMCGenerator();
            machineGenerator.generateAndRegisterAll();
            
            registry.freeze();
            
            LOGGER.info("Registering EMC values with ProjectE...");
            registry.applyToProjectE();
            
            long registeredCount = registry.getItemEMCMapping().size();
            LOGGER.info("Successfully registered EMC values for {} items", registeredCount);
        });
    }
    
    private static boolean isProjectELoaded() {
        return net.minecraftforge.fml.ModLoader.isModLoaded("projecte");
    }
}
