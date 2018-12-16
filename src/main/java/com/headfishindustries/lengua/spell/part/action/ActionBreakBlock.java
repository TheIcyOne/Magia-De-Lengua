package com.headfishindustries.lengua.spell.part.action;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.AbstractPartAction;
import com.headfishindustries.lengua.api.spell.Spell;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ActionBreakBlock extends AbstractPartAction{

	@Override
	public Energy getEnergyRequirements() {
		return Energy.EARTH.multiply(10);
	}

	@Override
	public EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		return EnumActionResult.PASS;
	}

	@Override
	public EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell,
			EntityLivingBase caster) {
		IBlockState s = world.getBlockState(target);
		float l = s.getBlock().getHarvestLevel(s);
		if(l <= 1 || l <=2 && modifiers.earth >= 1 || l <=3 && modifiers.earth >=2 || modifiers.earth >=3) {
			if (world.isRemote) return EnumActionResult.SUCCESS;
			NonNullList<ItemStack> drops = NonNullList.create();
			s.getBlock().getDrops(drops, world, target, s, (int) Math.floor(modifiers.water));
			world.setBlockToAir(target);
			world.playEvent(2001, target, Block.getStateId(s));
			if (modifiers.light >= 1) {
				world.spawnEntity(new EntityXPOrb(world, target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5, Math.round(modifiers.light)));
			}
			for (ItemStack stack : drops) {
				if (modifiers.fire >= 1){
					ItemStack smelted = FurnaceRecipes.instance().getSmeltingResult(stack);
						if (smelted != null) {
							stack = smelted;
						}
				}
				world.spawnEntity(new EntityItem(world, target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5, stack));
			}
		return EnumActionResult.SUCCESS;}
		return EnumActionResult.FAIL;
	}

	@Override
	public float getFlatManaCost() {
		return 20;
	}

	@Override
	public ItemStack[] getReagents() {
		// TODO Auto-generated method stub
		return null;
	}



}
