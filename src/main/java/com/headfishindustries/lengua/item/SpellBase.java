package com.headfishindustries.lengua.item;

import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpellBase extends Item{
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
		NBTTagCompound cmp = stack.getSubCompound(DataDefs.SPELL_TAG_ID);
		if (cmp == null) return; //WE GOT NO MAJICK, HALP
		//castSpell(worldIn, entityLiving, cmp);
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return 480000;
	}
}
