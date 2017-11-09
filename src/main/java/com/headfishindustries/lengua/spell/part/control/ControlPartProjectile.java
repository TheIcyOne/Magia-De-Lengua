package com.headfishindustries.lengua.spell.part.control;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.api.spell.SpellControlBase;
import com.headfishindustries.lengua.spell.entities.SpellProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlPartProjectile extends SpellControlBase{

	public ControlPartProjectile() {
		this.setRegistryName("ControlPartProjectile");
	}
	
	
	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell, EntityLiving caster) {
		SpellProjectile proj = new SpellProjectile(world, modifiers, caster, spell);
		proj.setPositionAndRotation(target.posX + Math.sin(target.rotationYaw) * 2, target.posY + target.getYOffset() + Math.cos(target.rotationPitch) * 2, target.posZ + Math.cos(target.rotationYaw) * 2, target.rotationYaw, target.rotationPitch);
		world.spawnEntity(proj);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell, EntityLiving caster) {
		SpellProjectile proj = new SpellProjectile(world, modifiers, caster, spell);
		proj.setPositionAndRotation((double)target.getX() + (world.rand.nextFloat() - 1) * 2,(double)target.getY() + (world.rand.nextFloat() - 1) * 2, (double)target.getZ() + (world.rand.nextFloat() - 1) * 2, (float)((world.rand.nextFloat() - 1) * 2 * Math.PI), (float)((world.rand.nextFloat() - 1) * 2 * Math.PI));
		
		world.spawnEntity(proj);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumActionResult onCast(BlockPos target, World world, Energy modifiers, Spell spell,
			EntityLiving caster) {
		return applyEffectEntity(caster, world, modifiers, spell, caster);
	}

	@Override
	public Energy getEnergyRequirements() {
		return new Energy(0, 0, 0, 0, 0, 0);
	}

	@Override
	public float getFlatManaCost() {
		return 10;
	}

	@Override
	public float getManaCostMultiplier() {
		
		return 1;
	}

	@Override
	public ItemStack[] getReagents() {
		
		return null;
	}

}
