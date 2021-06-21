package com.mhxks.sentence;

import com.mhxks.sentence.common.CommonProxy;
import com.mhxks.sentence.init.ModEntityLoader;
import com.mhxks.sentence.named.Funny;
import com.mhxks.sentence.specialattack.FunnyDrive;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SentenceMain.MODID,name = SentenceMain.MODNAME,version = SentenceMain.MODVERSION,dependencies = "required-after:flammpfeil.slashblade")
public class SentenceMain {
    @SidedProxy(serverSide = "com.mhxks.sentence.common.CommonProxy",clientSide = "com.mhxks.sentence.client.ClientProxy")
    public static CommonProxy PROXY;
    public static final String MODID = "sentence";
    public static final String MODNAME = "sentence";
    public static final String MODVERSION = "1.0.0";
    @Mod.Instance
    public static SentenceMain INSTANCE;
    public static int specialattack = 35592;
    @Mod.EventHandler
    public void pre(FMLPreInitializationEvent event){
        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(PROXY);
        SlashBlade.InitEventBus.register(new Funny());
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        new ModEntityLoader();
    }
    @Mod.EventHandler
    public void post(FMLPostInitializationEvent event){
       int size = ItemSlashBlade.specialAttacks.size();
       ItemSlashBlade.specialAttacks.put(35592,new FunnyDrive(0.6f,50,true, ItemSlashBlade.ComboSequence.Noutou));
    }
}
