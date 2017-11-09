package com.headfishindustries.lengua.api.spell;

import com.headfishindustries.lengua.api.energy.Energy;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class SpellControlBase extends SpellPartBase{
	
	public abstract EnumActionResult onCast(BlockPos target, World world, Energy modifiers, Spell spell, EntityLiving caster);;
}
