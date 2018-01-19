package com.headfishindustries.lengua.gui;

import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

//Just had to get clever with the name. You'll regret it later when you need to find this again, you idiot.
public class LenGui implements IGuiHandler{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case DataDefs.GUI_SPELL_WRITING:
			return new GuiSpellWriting(((TileSpellTable)world.getTileEntity(new BlockPos(x, y, z))));
		}
		return null;
	}

}
