package com.mhxks.sentence.entity;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySuperLightning
extends Entity {
    public EntityLivingBase entityLiving;
    public EntityPlayer tar;
    public ItemStack itemStack;
    public EntitySuperLightning(World worldIn) {
        super(worldIn);
    }
    public EntitySuperLightning(World world, EntityLivingBase entityLiving, EntityPlayer entityPlayer, ItemStack itemStack){
        super(world);
        this.entityLiving = entityLiving;
        this.tar = entityPlayer;
        this.setSize(0.25f,0.25f);
        this.itemStack = itemStack;
    }

    @Override
    protected void entityInit() {

    }


    @Override
    public void onUpdate() {
        if(!world.isRemote) {

            if (entityLiving != null && !entityLiving.isDead&&tar!=null&&!tar.isDead) {
                if(entityLiving instanceof EntityDragon&&entityLiving.getHealth()<=1.0F){
                    this.setDead();
                }
                //this.setPosition(entityLiving.posX, entityLiving.posY+1, entityLiving.posZ);
                this.posX = entityLiving.posX;
                this.posY = entityLiving.posY+1;
                this.posZ = entityLiving.posZ;
                DamageSource ds = new EntityDamageSource("directMagic",tar).setIsThornsDamage().setDamageBypassesArmor().setMagicDamage();
                //entityLiving.setHealth(entityLiving.getHealth()-1.0F);
                NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(itemStack);
                int rank = StylishRankManager.getStylishRank(tar);
                int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemStack);
                entityLiving.hurtResistantTime = 0;
                if(5<=rank){
                    float magicDamage = ItemSlashBlade.AttackAmplifier.get(tag) * (0.5f + (level / 5.0f));
                    entityLiving.attackEntityFrom(ds,magicDamage);


                }else {
                    entityLiving.attackEntityFrom(ds, 1.0F);
                }
                StylishRankManager.addRankPoint(tar,5);

                entityLiving.motionY = 0.01F;
                entityLiving.motionX = 0.0F;
                entityLiving.motionZ = 0.0F;
            } else {
                this.setDead();
            }
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

    @Override
    public AxisAlignedBB getEntityBoundingBox() {
        return super.getEntityBoundingBox();
    }
}
