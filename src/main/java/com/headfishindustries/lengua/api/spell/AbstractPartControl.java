package com.headfishindustries.lengua.api.spell;

import com.headfishindustries.lengua.api.energy.Energy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractPartControl extends AbstractPart{
	
	public abstract EnumActionResult onCast(BlockPos target, World world, Energy modifiers, Spell spell, EntityLivingBase caster);;
}
