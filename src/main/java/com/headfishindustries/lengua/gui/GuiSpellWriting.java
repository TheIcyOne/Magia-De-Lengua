package com.headfishindustries.lengua.gui;

import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.container.ContainerSpellTable;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;


public class GuiSpellWriting extends GuiContainer{
	
	private GuiTextField searchBar;
	private GuiTextField nameBar;
	
	public GuiSpellWriting(TileSpellTable tileSpellTable){
		super(new ContainerSpellTable());

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		
		nameBar = new GuiTextField(0, fontRenderer, 10, 10, 120, 24);
		
		searchBar = new GuiTextField(1, fontRenderer, 10, 26, 120, 36);
		searchBar.setEnableBackgroundDrawing(true);
		
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
	}

}
