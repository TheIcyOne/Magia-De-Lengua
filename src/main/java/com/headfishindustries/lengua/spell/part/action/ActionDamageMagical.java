package com.headfishindustries.lengua.spell.part.action;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.AbstractPartAction;
import com.headfishindustries.lengua.api.spell.Spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ActionDamageMagical extends AbstractPartAction{

	public ActionDamageMagical() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Energy getEnergyRequirements() {
	}

	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
			if (target instanceof EntityLiving){
				return EnumActionResult.SUCCESS;
			}
		return EnumActionResult.PASS;
	}

	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		// TODO Auto-generated method stub
		return EnumActionResult.FAIL;
	}

	@Override
	public float getFlatManaCost() {
	}

	@Override
	public float getManaCostMultiplier() {
	}

	@Override
	public ItemStack[] getReagents() {
		return null;
	}

	@Override
	public String getPartWord() {
		return "damage_magical";
	}


}
