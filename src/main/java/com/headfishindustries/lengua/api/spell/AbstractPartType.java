package com.headfishindustries.lengua.api.spell;

import com.headfishindustries.lengua.api.energy.Energy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractPartType extends AbstractPart implements IForgeRegistryEntry<AbstractPart>{
	
	//Types generally shouldn't be applying effects directly, rather modifying existing effects. Still possible to override if needed, because why not?.
	
	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell, EntityLiving caster){return EnumActionResult.PASS;}
	
	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell, EntityLiving caster){return EnumActionResult.PASS;}

}
