package com.headfishindustries.lengua.api.spell;

import com.headfishindustries.lengua.api.energy.Energy;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class SpellTypeBase extends SpellActionBase implements IForgeRegistryEntry<SpellPartBase>{
	
	//Types generally shouldn't be applying effects directly, rather modifying existing effects. Still possible to override if needed.
	
	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, NBTTagCompound spell){return EnumActionResult.PASS;}
	
	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, NBTTagCompound spell){return EnumActionResult.PASS;}

}
