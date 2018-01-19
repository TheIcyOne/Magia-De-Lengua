package com.headfishindustries.lengua.container.slot;

import com.headfishindustries.lengua.defs.ContentDefs;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpell extends Slot{

	public SlotSpell(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if (stack == null || stack.getItem() == null || stack.getItem() != ContentDefs.INSTANCE.itemSpellBase) return false;
		return true;
	}

}
