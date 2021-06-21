package com.mhxks.sentence.common;

import com.mhxks.sentence.SentenceMain;
import com.mhxks.sentence.entity.EntityFunnyDrive;
import com.mhxks.sentence.init.ModItemLoader;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;
import java.util.Random;

public class CommonProxy {
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> reg){
        reg.getRegistry().register(ModItemLoader.FUNNY_SLASH_BLADE);
    }

    @SubscribeEvent
    public void onToolpAdd(ItemTooltipEvent event){
        List<String> list = event.getToolTip();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).indexOf("[randomLore]")!=-1){
                Random random = new Random();
                char[] str = I18n.format("funny.desc.4").toCharArray();
                StringBuffer sb = new StringBuffer();
                for (int i1 = 0; i1 < 30; i1++) {
                    sb.append(str[random.nextInt(str.length)]);
                }
                list.set(i,list.get(i).replace("[randomLore]",sb.toString()));
            }
        }
    }
}
