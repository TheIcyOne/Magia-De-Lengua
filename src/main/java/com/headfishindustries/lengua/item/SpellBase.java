package com.headfishindustries.lengua.item;

import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.defs.DataDefs;
import com.headfishindustries.lengua.util.SpellUtils;

import net.minecraft.entity.EntityLiving;
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
		String tag = stack.getTagCompound().getString(DataDefs.SPELL_TAG_ID);
		if (tag.length() == 0) return; //WE GOT NO MAJICK, HALP
		Spell spell = SpellUtils.instance.getSpell(tag);
		spell.onCast(entityLiving.getPosition(), worldIn, spell.getEnergy(),(EntityLiving)entityLiving);
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return 480000;
	}
}
