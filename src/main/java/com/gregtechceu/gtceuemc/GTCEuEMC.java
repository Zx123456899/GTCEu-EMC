package com.gregtechceu.gtceuemc;

import net.minecraftforge.fml.common.Mod;

@Mod(GTCEuEMC.MOD_ID)
public class GTCEuEMC {
    
    public static final String MOD_ID = "gtceuemc";
    
    public GTCEuEMC() {
        CommonProxy.init();
    }
}
