package com.mhxks.sentence.item;

import com.mhxks.sentence.entity.EntitySuperLightning;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.invoke.MethodHandle;
import java.util.EnumSet;
import java.util.List;

public class ItemFunnySlashBlade
extends ItemSlashBlade {
    public ItemFunnySlashBlade(ToolMaterial par2EnumToolMaterial, float defaultBaseAttackModifier) {
        super(par2EnumToolMaterial, defaultBaseAttackModifier);
        this.setCreativeTab(null);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        super.onUsingTick(stack, player, count);

        int timeLeft = player.getItemInUseCount();
        NBTTagCompound tag = getItemTagCompound(stack);
        int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
        EnumSet<SwordType> swordType = getSwordType(stack);
        if(var6>RequiredChargeTick&&var6%5==0 && swordType.contains(SwordType.Enchanted) && !swordType.contains(SwordType.Broken)){
            // SilentUpdateItem.forceUpdate(stack, player);
            doSwingItem(stack, player);
            // SilentUpdateItem.forceUpdate(stack, player);
            doChargeAttack(stack,(EntityPlayer) player,true);
        }
    }


    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        NBTTagCompound tag = getItemTagCompound(par1ItemStack);

        updateKillCount(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);

        ComboSequence comboSec = getComboSequence(tag);

        setImpactEffect(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase, comboSec);

        //取消武器的破坏
        if((!comboSec.useScabbard && comboSec.mainHandCombo == null) || IsNoScabbard.get(tag)) {
            ItemSlashBlade.damageItem(par1ItemStack, 1, par3EntityLivingBase);
        }

        StylishRankManager.doAttack(par3EntityLivingBase);

        return true;
    }
    public boolean hitEntity2(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        NBTTagCompound tag = getItemTagCompound(par1ItemStack);

        updateKillCount(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);

        ComboSequence comboSec = getComboSequence(tag);

        setImpactEffect(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase, comboSec);
        /*
        //取消武器的破坏
        if((!comboSec.useScabbard && comboSec.mainHandCombo == null) || IsNoScabbard.get(tag)) {
            ItemSlashBlade.damageItem(par1ItemStack, 1, par3EntityLivingBase);
        }


*/

        StylishRankManager.doAttack(par3EntityLivingBase);
        World world = par2EntityLivingBase.getEntityWorld();
        world.createExplosion(par2EntityLivingBase,par2EntityLivingBase.posX,par2EntityLivingBase.posY,par2EntityLivingBase.posZ,5.0F,false);
        if(StylishRankManager.getStylishRank(par3EntityLivingBase)>=4){
            world.addWeatherEffect(new EntityLightningBolt(world,par2EntityLivingBase.posX,par2EntityLivingBase.posY,par2EntityLivingBase.posZ,true));
        }
        if(par3EntityLivingBase instanceof EntityPlayer&&par1ItemStack.getItem() instanceof ItemSlashBlade&&false) {
            EntitySuperLightning entitySuperLightning = new EntitySuperLightning(world, par2EntityLivingBase,(EntityPlayer)par3EntityLivingBase,par1ItemStack);
            entitySuperLightning.setPositionAndUpdate(par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ);
            world.spawnEntity(entitySuperLightning);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformationSwordClass(ItemStack par1ItemStack,
                                         EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

        EnumSet<SwordType> swordType = getSwordType(par1ItemStack);
        NBTTagCompound tag = getItemTagCompound(par1ItemStack);

        if(swordType.contains(SwordType.Enchanted)){
            if(swordType.contains(SwordType.Bewitched)){
                if(tag.hasUniqueId("Owner"))
                    par3List.add(String.format("§6%s", I18n.format("sentence.swaepon.info.bewitched")));
                else
                    par3List.add(String.format("§5%s", I18n.format("sentence.swaepon.info.bewitched")));
            }else{
                par3List.add(String.format("§3%s", I18n.format("flammpfeil.swaepon.info.magic")));
            }
        }else{
            par3List.add(String.format("§8%s", I18n.format("flammpfeil.swaepon.info.noname")));
        }
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack,
                               World world, List par3List, ITooltipFlag inFlag) {

        EntityPlayer par2EntityPlayer = Minecraft.getMinecraft().player;
        boolean par4 = inFlag.isAdvanced();


        if(par2EntityPlayer == null) return;

        addInformationOwner(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationSwordClass(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationKillCount(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationProudSoul(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationSpecialAttack(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationRepairCount(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationRangeAttack(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationSpecialEffec(par1ItemStack, par2EntityPlayer, par3List, par4);

        addInformationMaxAttack(par1ItemStack, par2EntityPlayer, par3List, par4);

        NBTTagCompound tag = getItemTagCompound(par1ItemStack);
        if(tag.hasKey(adjustXStr)){
            float ax = tag.getFloat(adjustXStr);
            float ay = tag.getFloat(adjustYStr);
            float az = tag.getFloat(adjustZStr);
           par3List.add(String.format("adjust x:%.1f y:%.1f z:%.1f", ax,ay,az));
        }


        addInformationEnergy(par1ItemStack, par2EntityPlayer, par3List, par4);

    }
}
