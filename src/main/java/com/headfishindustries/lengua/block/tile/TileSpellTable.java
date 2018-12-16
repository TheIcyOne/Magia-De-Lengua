package com.headfishindustries.lengua.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileSpellTable extends TileEntity implements IInventory{
	
	private ItemStack[] invent = {ItemStack.EMPTY, new ItemStack(Items.PAPER, 1)};
	
	private String spellName = "";
	private String spellText = "";
	

	public TileSpellTable() {
		
	}

	@Override
	public String getName() {
		return "Spell Table";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public boolean isEmpty() {
		return invent[0].isEmpty() || invent[1].isEmpty() || invent[2].isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if  (index > getSizeInventory() - 1) return ItemStack.EMPTY;
		return invent[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if  (index > getSizeInventory() - 1) return ItemStack.EMPTY;
		
		return invent[index].splitStack(Math.min(count, invent[index].getCount()));
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if  (index > getSizeInventory() - 1) return ItemStack.EMPTY;
		
		return invent[index].splitStack(invent[index].getCount());
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if  (index > getSizeInventory() - 1) return;
		invent[index] = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 1;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			//Make spell.
			break;
		case 1:
			//Reset spell.
			break;
		case 2:
			//Randomly name spell.
			break;
		}
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public String getSpellName() {
		return this.spellName;
	}
	
	public void setSpellName(String s) {
		this.spellName = s;
	}
	
	public String getSpellText() {
		return this.spellText;
	}
	
	public void setSpellText(String s) {
		this.spellText = s;
	}

}
