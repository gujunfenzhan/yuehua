package com.mhxks.sentence.client;

import com.mhxks.sentence.client.render.entity.RenderFunnyDrive;
import com.mhxks.sentence.client.render.entity.RenderSuperLightning;
import com.mhxks.sentence.common.CommonProxy;
import com.mhxks.sentence.entity.EntityFunnyDrive;
import com.mhxks.sentence.entity.EntitySuperLightning;
import com.mhxks.sentence.init.ModItemLoader;
import mods.flammpfeil.slashblade.event.ModelRegister;
import mods.flammpfeil.slashblade.util.ReflectionAccessHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ClientProxy
extends CommonProxy {

    @SubscribeEvent
    public void onModelRegister(ModelRegistryEvent event){
        Class clazz = ModelRegister.class;
        ModelResourceLocation modelResourceLocation = (ModelResourceLocation)ReflectionHelper.getPrivateValue(clazz,new ModelRegister(),"modelLoc");
        ModelLoader.setCustomModelResourceLocation(ModItemLoader.FUNNY_SLASH_BLADE,0,modelResourceLocation);

       RenderingRegistry.registerEntityRenderingHandler(EntityFunnyDrive.class, new IRenderFactory<EntityFunnyDrive>() {
           @Override
           public Render<? super EntityFunnyDrive> createRenderFor(RenderManager manager) {
               return new RenderFunnyDrive(manager);
           }
       });
       RenderingRegistry.registerEntityRenderingHandler(EntitySuperLightning.class, new IRenderFactory<EntitySuperLightning>() {
           @Override
           public Render<? super EntitySuperLightning> createRenderFor(RenderManager manager) {

               return new RenderSuperLightning(manager);
           }
       });
    }
}
