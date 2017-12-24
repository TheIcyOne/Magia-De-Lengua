package com.headfishindustries.lengua.item;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.defs.DataDefs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SpellBase extends Item{
	
	public SpellBase(){
		super();
		this.setUnlocalizedName("spell_base");
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer caster, EnumHand handIn)
    {
		ItemStack stack = caster.getHeldItem(handIn);
		String tag = stack.getTagCompound().getString(DataDefs.SPELL_TAG_ID);
		if (tag.length() == 0)  return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack); //WE GOT NO MAJICK, HALP
		Lengua.LOGGER.debug(caster + " is casting spell:" + tag);
		Spell spell = Spell.fromString(tag);
		spell.onCast(caster.getPosition(), worldIn, spell.getEnergy(), (EntityLivingBase)caster);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return 480000;
	}
}
