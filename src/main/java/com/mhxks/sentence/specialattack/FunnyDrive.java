package com.mhxks.sentence.specialattack;

import com.mhxks.sentence.entity.EntityFunnyDrive;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class FunnyDrive
extends SpecialAttackBase {
    private final float speed;
    private final int lifetime;
    private final boolean multihit;
    private final ItemSlashBlade.ComboSequence setCombo;
    public FunnyDrive(float speed,int lifetime,boolean multihit, ItemSlashBlade.ComboSequence setCombo){
        this.speed = speed;
        this.lifetime = lifetime;
        this.multihit = multihit;
        this.setCombo = setCombo;
    }
    @Override
    public String toString() {
        return "superdrive";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.world;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

        if(!world.isRemote){
            /*
            final int cost = -10;

            if(!ItemSlashBlade.ProudSoul.tryAdd(tag, cost, false)){
                ItemSlashBlade.damageItem(stack, 5, player);
            }*/

            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            float baseModif = blade.getBaseAttackModifiers(tag);
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
            float magicDamage = baseModif;

            int rank = StylishRankManager.getStylishRank(player);
            if(5 <= rank)
                magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5f + (level / 5.0f));
            EntityFunnyDrive entityDrive = new EntityFunnyDrive(world, player, magicDamage,multihit,90.0f - setCombo.swingDirection);
            StylishRankManager.addRankPoint(player,10);
            if (entityDrive != null) {
                entityDrive.setInitialSpeed(speed);
                entityDrive.setLifeTime(lifetime);
                world.spawnEntity(entityDrive);

            }



        }

        player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
                0.8F, 0.01F);
        ItemSlashBlade.setComboSequence(tag, setCombo);
    }
}
