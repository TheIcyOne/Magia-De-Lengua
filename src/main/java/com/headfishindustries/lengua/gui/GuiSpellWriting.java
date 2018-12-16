package com.headfishindustries.lengua.gui;

import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.container.ContainerSpellTable;
import com.headfishindustries.lengua.util.maths.Vec2i;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSimpleScrolledSelectionListProxy;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;


public class GuiSpellWriting extends GuiContainer{
	
	private Vec2i partSize = new Vec2i(40, 80);
	private Vec2i partPos = new Vec2i(400, 26);
	
	private Vec2i nameSize = new Vec2i(120, 16);
	private Vec2i namePos = new Vec2i(10, 10);
	private Vec2i searchSize = new Vec2i(120, 16);
	private Vec2i searchPos = new Vec2i(10, 36);
	
	private int id = 0;
	
	private GuiTextField searchBar;
	private GuiTextField nameBar;
	private GuiScrolledSelectionTable partDictionary;
	
	private EntityPlayer using;
	
	public GuiSpellWriting(InventoryPlayer inventory, TileSpellTable tileSpellTable){
		super(new ContainerSpellTable(inventory, tileSpellTable));
		using = inventory.player;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.searchBar.drawTextBox();
		this.searchBar.drawString(Minecraft.getMinecraft().fontRenderer,
				this.searchBar.getText() != "" ? this.searchBar.getText() : "Part Search",
				this.searchBar.x + 4, this.searchBar.y + this.searchBar.height / 3,
				this.searchBar.getText() == "" ? 0xAAAAAA : 0xEEEEEE);
		this.nameBar.drawTextBox();
		this.nameBar.drawString(Minecraft.getMinecraft().fontRenderer,
				this.nameBar.getText() != "" ? this.nameBar.getText() : "Spell Name",
				this.nameBar.x + 4, this.nameBar.y + this.nameBar.height / 3,
				this.nameBar.getText() == "" ? 0xAAAAAA : 0xEEEEEE);
		this.partDictionary.draw();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		
		nameBar = new GuiTextField(id++, fontRenderer, namePos.x, namePos.y, nameSize.x, nameSize.y);
		nameBar.setEnableBackgroundDrawing(true);
		
		searchBar = new GuiTextField(id++, fontRenderer, searchPos.x, searchPos.y, searchSize.x, searchSize.y);
		searchBar.setEnableBackgroundDrawing(true);
		
		String[] head = {"Value", "Name", "Type"};
		
		partDictionary = new GuiScrolledSelectionTable(id++, head, fontRenderer, partPos.x, partPos.y, partSize.x, partSize.y, 20);

		String[] test = {"testval", "testname", "testtype"};
		
		partDictionary.append(test);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
	}

}
