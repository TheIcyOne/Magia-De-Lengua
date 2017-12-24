package com.headfishindustries.lengua.spell.part.action;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.api.spell.AbstractPartAction;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ActionDamagePhysical extends AbstractPartAction{

	public ActionDamagePhysical() {
	}

	@Override
	public Energy getEnergyRequirements() {
		return new Energy(0, 0, 0, 10, 0, 0);
	}

	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
			if (target instanceof EntityLiving){
				((EntityLiving)target).attackEntityFrom(DamageSource.GENERIC, 5);
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
		return 100;
	}

	@Override
	public float getManaCostMultiplier() {
		return 1f;
	}

	@Override
	public ItemStack[] getReagents() {
		return null;
	}

	@Override
	public String getPartWord() {
		// TODO Auto-generated method stub
		return "damage_physical";
	}

}
