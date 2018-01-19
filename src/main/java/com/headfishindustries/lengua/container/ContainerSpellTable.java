package com.headfishindustries.lengua.container;

import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.container.slot.SlotSpell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSpellTable extends Container{

	public ContainerSpellTable(InventoryPlayer using, TileSpellTable table) {
		super()
		addSlotToContainer(new SlotSpell(null, 0, 10, 10));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
