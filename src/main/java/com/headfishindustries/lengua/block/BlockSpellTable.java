package com.headfishindustries.lengua.block;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.block.tile.TileSpellTable;
import com.headfishindustries.lengua.defs.ContentDefs;
import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSpellTable extends Block implements ITileEntityProvider{

	public BlockSpellTable() {
		super(Material.ROCK);
		this.setUnlocalizedName("block.spell.writing_table");
	}

	public BlockSpellTable(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileSpellTable();
	}
	
	@Override
	 public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		playerIn.openGui(Lengua.instance, DataDefs.GUI_SPELL_WRITING, worldIn, pos.getX(), pos.getY(), pos.getZ());
		ItemStack stack = new ItemStack(ContentDefs.INSTANCE.itemSpellBase);
		NBTTagCompound nbt = (stack.getTagCompound() != null) ? stack.getTagCompound() : new NBTTagCompound();
		nbt.setString(DataDefs.SPELL_TAG_ID, ContentDefs.INSTANCE.partTouch.getRegistryName() + " " + ContentDefs.INSTANCE.damagePhysical.getRegistryName());
		stack.setTagCompound(nbt);
		playerIn.addItemStackToInventory(stack);
		return true;
		
	}

}
