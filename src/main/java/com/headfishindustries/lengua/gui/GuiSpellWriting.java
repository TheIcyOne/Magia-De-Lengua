package com.headfishindustries.lengua.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.container.ContainerSpellTable;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;


public class GuiSpellWriting extends GuiContainer{
	
	private GuiTextField nameBar;
	private GuiButton makeSpell;
	private ContainerSpellTable table;
	
	public GuiSpellWriting(ContainerSpellTable spellTable){
		super(spellTable);
		this.table = spellTable;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.nameBar.drawTextBox();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		
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
