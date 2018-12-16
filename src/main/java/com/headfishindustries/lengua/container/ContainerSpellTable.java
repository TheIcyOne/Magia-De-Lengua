package com.headfishindustries.lengua.container;

import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.container.slot.SlotSpell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerSpellTable extends Container{
	private TileSpellTable table;

<<<<<<< HEAD
	public ContainerSpellTable(TileSpellTable table) {
		super();
=======
	public ContainerSpellTable(InventoryPlayer using, TileSpellTable table) {
		super();
		this.table = table;
		/** Spell **/
		addSlotToContainer(new SlotSpell(table, 0, 10, 10));
		/** Paper + Ink **/
		addSlotToContainer(new Slot(table, 1, 20, 20));
		/** Player Inventory **/
		for (int i = 0; i<3; i++){
			for (int j = 0; j<9; j++) {
				addSlotToContainer(new Slot(using, j + i * 9 + 9, 30 + j * 18, 170 + i * 18));
			}
		}
		for (int j = 0; j<9; j++) {
			addSlotToContainer(new Slot(using, j + 36, 30 + j * 18, 224));
		}
>>>>>>> origin/master
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	public String getSpellName() {
		return table.getSpellName();
	}
	
	public void setSpellName(String s) {
		table.setSpellName(s);
	}
	
	public String getSpellText() {
		return table.getSpellText();
	}
	
	public void setSpellText(String s) {
		table.setSpellText(s);
	}

}
