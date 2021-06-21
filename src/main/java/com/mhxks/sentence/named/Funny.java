package com.mhxks.sentence.named;

import com.mhxks.sentence.SentenceMain;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Funny {
    public static final String funny = "sentence.named.funny";
    public static ItemStack customSlashBladeA;
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event){

        String name = funny;
        ItemStack customBlade = SlashBlade.findItemStack(SentenceMain.MODID,"funny",1);
        SlashBlade.wrapBlade.removeWrapItem(customBlade);

        customBlade.addEnchantment(Enchantments.KNOCKBACK,3);
        customBlade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS,3);
        customBlade.addEnchantment(Enchantments.UNBREAKING,3);
        customBlade.addEnchantment(Enchantments.LOOTING,5);
        customBlade.addEnchantment(Enchantments.FIRE_ASPECT,3);
        customBlade.addEnchantment(Enchantments.SHARPNESS,5);
        customBlade.addEnchantment(Enchantments.POWER,3);
        NBTTagCompound tag = customBlade.getTagCompound();
        ItemStack innerBlade = SlashBlade.findItemStack("minecraft", "wooden_sword", 1);
        SlashBlade.wrapBlade.setWrapItem(customBlade,innerBlade);
        ItemSlashBladeNamed.BaseAttackModifier.set(tag, 6.0f);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.TrueItemName.set(tag, name);
        String Texture = "named/funny/funny";
        ItemSlashBlade.TextureName.set(tag, Texture);
        //逆天道而行之!!!
        ItemSlashBlade.textureMap.put(Texture,new ResourceLocationRaw(SentenceMain.MODID,"model/"+Texture+".png"));
        //白狐模型
        String model = "named/funny/funny";
        ItemSlashBlade.ModelName.set(tag,model);
        //逆天道而行之!!!
        ItemSlashBlade.modelMap.put(model,new ResourceLocationRaw(SentenceMain.MODID,"model/"+model+".obj"));
        ItemSlashBlade.SpecialAttackType.set(tag, 35592); //0:次元斬
        ItemSlashBlade.StandbyRenderType.set(tag,3);
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);




        //NamedBladeManager.registerBladeSoul(tag , customBlade.getDisplayName());
        //SlashBlade.registerCustomItemStack(name, customBlade);
        customBlade = customBlade.copy();
        NBTTagCompound displayTag = new NBTTagCompound();
        customBlade.setTagInfo("display",displayTag);
        NBTTagList loreList = new NBTTagList();
        loreList.appendTag(new NBTTagString(I18n.format("funny.desc.1")));
        loreList.appendTag(new NBTTagString(I18n.format("funny.desc.2")));
        loreList.appendTag(new NBTTagString(I18n.format("funny.desc.3")));
        loreList.appendTag(new NBTTagString("[randomLore]"));
        loreList.appendTag(new NBTTagString("[randomLore]"));
        loreList.appendTag(new NBTTagString(I18n.format("funny.desc.5")));
        //loreList.appendTag(new NBTTagString("true performance : please crafting"));
        displayTag.setTag("Lore", loreList);
        String creativeStr = name+".creative";
        //SlashBlade.registerCustomItemStack(creativeStr, customBlade);  使用这个方法会默认使用SlashBlade的MODID
        SlashBlade.BladeRegistry.put(new ResourceLocationRaw(SentenceMain.MODID,creativeStr),customBlade);
        customSlashBladeA = customBlade;
        ItemSlashBladeNamed.NamedBlades.add(SentenceMain.MODID + ":" + creativeStr);

    }
    @SubscribeEvent
    public void post(LoadEvent.PostInitEvent event){

        //大太刀
        ItemStack custombladeReqired = new ItemStack(SlashBlade.weapon,1,0);
        NBTTagCompound tagReqired = new NBTTagCompound();
        custombladeReqired.setTagCompound(tagReqired);
        custombladeReqired.addEnchantment(Enchantments.LOOTING, 1);
        ItemSlashBlade.KillCount.set(tagReqired,1000);
        ItemSlashBlade.ProudSoul.set(tagReqired,10000);
        ItemSlashBlade.RepairCount.set(tagReqired,10);
        String nameReqired = funny+".reqired";
        SlashBlade.registerCustomItemStack(nameReqired, custombladeReqired);
        ItemSlashBladeNamed.NamedBlades.add(nameReqired);
        SlashBlade.addRecipe(funny,
                new RecipeAwakeBlade(new ResourceLocation(SentenceMain.MODID,"funny"),
                     customSlashBladeA,
                        custombladeReqired,
                        "ACA","DBD","ACA",
                        'A',SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1),
                        'B',custombladeReqired,
                        'C',new ItemStack(Items.NETHER_STAR),
                        'D',new ItemStack(Blocks.DIAMOND_BLOCK)));
    }
}
