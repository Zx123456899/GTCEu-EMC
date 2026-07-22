package com.gregtechceu.gtceuemc;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(GTCEuEMC.MOD_ID)
public class GTCEuEMC {
    
    public static final String MOD_ID = "gtceuemc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public GTCEuEMC() {
        CommonProxy.init();
    }
}