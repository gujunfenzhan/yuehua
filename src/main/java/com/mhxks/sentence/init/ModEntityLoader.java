package com.mhxks.sentence.init;

import com.mhxks.sentence.SentenceMain;
import com.mhxks.sentence.entity.EntityFunnyDrive;
import com.mhxks.sentence.entity.EntitySuperLightning;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntityLoader {
    public int entityId = 0;
    public ModEntityLoader(){
        registerEntitys("FunnyDrive", EntityFunnyDrive.class);
        registerEntitys("SuperLightning", EntitySuperLightning.class);
    }
    public void registerEntitys(String entityNmae,Class clazz){
        EntityRegistry.registerModEntity(new ResourceLocation(SentenceMain.MODID,entityNmae),clazz,entityNmae,entityId++,SentenceMain.INSTANCE,250,10,true);
    }
}
