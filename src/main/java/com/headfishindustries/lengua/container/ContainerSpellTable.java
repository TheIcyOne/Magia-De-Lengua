package com.headfishindustries.lengua.container;

import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.container.slot.SlotSpell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSpellTable extends Container{

	public ContainerSpellTable(TileSpellTable table) {
		super();
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
