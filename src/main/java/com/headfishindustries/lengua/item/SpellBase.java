package com.headfishindustries.lengua.item;

import java.util.ArrayList;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.defs.DataDefs;
import com.headfishindustries.lengua.util.ItemNBTTexed;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SpellBase extends ItemNBTTexed{
	
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
		if (!stack.hasTagCompound()) return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
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

	@Override
	public ArrayList<ModelResourceLocation> getModels() {
		ArrayList<ModelResourceLocation> res = new ArrayList<ModelResourceLocation>();
		for (Integer i = 0; i<10; i++) {
			res.add(new ModelResourceLocation(DataDefs.MODID, "spell/" + i.toString()));
		}
		
		return res;
	}

	@Override
	public ItemMeshDefinition getMeshDef() {
		return new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				NBTTagCompound t = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
				int n = t.getInteger("SpellModelID");
				return getModels().get(n);
			}
			
		};
	}
}
