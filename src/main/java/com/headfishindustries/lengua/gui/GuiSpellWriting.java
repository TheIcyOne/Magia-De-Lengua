package com.headfishindustries.lengua.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.container.ContainerSpellTable;
import com.headfishindustries.lengua.util.maths.Vec2i;

<<<<<<< HEAD
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
=======
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSimpleScrolledSelectionListProxy;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
>>>>>>> origin/master


public class GuiSpellWriting extends GuiContainer{
	
<<<<<<< HEAD
	private GuiTextField nameBar;
	private GuiButton makeSpell;
	private ContainerSpellTable table;
	
	public GuiSpellWriting(ContainerSpellTable spellTable){
		super(spellTable);
		this.table = spellTable;
=======
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
>>>>>>> origin/master
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
<<<<<<< HEAD
		this.nameBar.drawTextBox();
=======
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
>>>>>>> origin/master
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		
<<<<<<< HEAD
		nameBar = new GuiTextField(0, fontRenderer, 10, 10, 120, 24);
		nameBar.setEnableBackgroundDrawing(true);
		nameBar.setEnabled(true);
		nameBar.setVisible(true);
		nameBar.drawTextBox();
		nameBar.setTextColor(-1);
		nameBar.setDisabledTextColour(-1);
		

		
		makeSpell = new GuiButton(1, 10, 36, 120, 20, "Make Spell");
		makeSpell.visible = true;
		this.buttonList.add(makeSpell);
		
		Keyboard.enableRepeatEvents(true);
=======
		nameBar = new GuiTextField(id++, fontRenderer, namePos.x, namePos.y, nameSize.x, nameSize.y);
		nameBar.setEnableBackgroundDrawing(true);
		
		searchBar = new GuiTextField(id++, fontRenderer, searchPos.x, searchPos.y, searchSize.x, searchSize.y);
		searchBar.setEnableBackgroundDrawing(true);
		
		String[] head = {"Value", "Name", "Type"};
		
		partDictionary = new GuiScrolledSelectionTable(id++, head, fontRenderer, partPos.x, partPos.y, partSize.x, partSize.y, 20);

		String[] test = {"testval", "testname", "testtype"};
		
		partDictionary.append(test);
>>>>>>> origin/master
	}
	

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents( false );
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
	}
	
	 protected void keyTyped(char typedChar, int keyCode) throws IOException
	    {
	        if (this.nameBar.textboxKeyTyped(typedChar, keyCode))
	        {
	            Lengua.LOGGER.info(nameBar.getText());
	        }
	        else
	        {
	            super.keyTyped(typedChar, keyCode);
	        }
	    }

}
