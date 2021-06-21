package com.mhxks.sentence.init;

import com.mhxks.sentence.SentenceMain;
import com.mhxks.sentence.item.ItemFunnySlashBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.Item;

public class ModItemLoader {
    public static final Item FUNNY_SLASH_BLADE = new ItemFunnySlashBlade(Item.ToolMaterial.IRON,4.0f).setMaxDamage(60).setUnlocalizedName(SentenceMain.MODID+".funny").setRegistryName("funny");
}
