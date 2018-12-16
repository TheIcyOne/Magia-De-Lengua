package com.headfishindustries.lengua.util;

import java.util.ArrayList;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public abstract class ItemNBTTexed extends Item{
	
	public abstract ArrayList<ModelResourceLocation> getModels();
	public abstract ItemMeshDefinition getMeshDef();
	
	public void initModel() {
		
		ModelBakery.registerItemVariants(this, (ResourceLocation[]) this.getModels().toArray());
		
		ModelLoader.setCustomMeshDefinition(this, this.getMeshDef());
		
	}
	
}
