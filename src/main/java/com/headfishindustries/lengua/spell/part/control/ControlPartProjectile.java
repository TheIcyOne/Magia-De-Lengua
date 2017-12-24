package com.headfishindustries.lengua.spell.part.control;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.api.spell.AbstractPartControl;
import com.headfishindustries.lengua.spell.entities.SpellProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlPartProjectile extends AbstractPartControl{

	public ControlPartProjectile() {
	}
	
	
	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell, EntityLivingBase caster) {
		SpellProjectile proj = new SpellProjectile(world, modifiers, caster, spell);
		proj.setPositionAndRotation(target.posX, target.posY + target.getEyeHeight() - 0.10000000149011612D, target.posZ, target.rotationYaw, target.rotationPitch);
		world.spawnEntity(proj);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell, EntityLivingBase caster) {
		SpellProjectile proj = new SpellProjectile(world, modifiers, caster, spell);
		proj.setPositionAndRotation((double)target.getX() + (world.rand.nextFloat() - 1) * 2,(double)target.getY() + (world.rand.nextFloat() - 1) * 2, (double)target.getZ() + (world.rand.nextFloat() - 1) * 2, (float)((world.rand.nextFloat() - 1) * 2 * Math.PI), (float)((world.rand.nextFloat() - 1) * 2 * Math.PI));
		
		world.spawnEntity(proj);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public EnumActionResult onCast(BlockPos target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		return applyEffectEntity(caster, world, modifiers, spell, caster);
	}

	@Override
	public Energy getEnergyRequirements() {
		return new Energy(25, 0, 0, 15, 5, 5);
	}

	@Override
	public float getFlatManaCost() {
		return 10;
	}

	@Override
	public float getManaCostMultiplier() {
		
		return 1.5f;
	}

	@Override
	public ItemStack[] getReagents() {
		
		return null;
	}


	@Override
	public String getPartWord() {
		return "Projectile";
	}

}
