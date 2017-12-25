package com.headfishindustries.lengua.spell.part.control;

import java.util.List;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.AbstractPartControl;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.util.RayTraceUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ControlPartTouch extends AbstractPartControl{

	/** Show me on the doll... **/
	public ControlPartTouch() {
		
	}

	@Override
	public EnumActionResult onCast(BlockPos target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		RayTraceResult bl = caster.rayTrace(2.5f, 1.0f);
		double radius = 0.25d;
		List<Vec3d> l = RayTraceUtils.toVecList(caster.getLookVec().scale(2.5f), caster.getPosition(), radius, caster.getYOffset());
		for (Vec3d pos : l){
		for (Entity e : world.getEntitiesWithinAABBExcludingEntity(caster, new AxisAlignedBB(pos.x - radius * 4, pos.y - radius * 4, pos.z - radius * 4, pos.x + radius * 4, pos.y + radius * 4, pos.z + radius * 4))){
				spell.applyEffectEntity(e, world, modifiers, caster);
				return EnumActionResult.SUCCESS;
			}
		}
		
		
		if (!(world.getBlockState(bl.getBlockPos()).equals(Blocks.AIR.getDefaultState()))){
			spell.applyEffectBlock(bl.getBlockPos(), world, modifiers, caster);
			return EnumActionResult.SUCCESS;
		}
		
		return EnumActionResult.FAIL;
	}

	@Override
	public Energy getEnergyRequirements() {
		return new Energy(0, 0, 0, 15, 0, 0);
	}

	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		return null;
	}

	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		spell.applyEffectBlock(target, world, modifiers,caster);
		return EnumActionResult.PASS;
	}

	@Override
	public float getFlatManaCost() {
		return 10;
	}
	
	@Override
	public float getManaCostMultiplier(){
		return 0.9f;
	}

	@Override
	public ItemStack[] getReagents() {
		return null;
	}
	
	@Override
	public String getPartWord() {
		return "Touch";
	}

}
