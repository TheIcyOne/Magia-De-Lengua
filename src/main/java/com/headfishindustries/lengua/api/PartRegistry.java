package com.headfishindustries.lengua.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.headfishindustries.lengua.api.spell.SpellPartBase;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class PartRegistry implements IForgeRegistry<SpellPartBase>{
	
	public static PartRegistry instance = new PartRegistry();

	@Override
	public Iterator<SpellPartBase> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<SpellPartBase> getRegistrySuperType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(SpellPartBase value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerAll(SpellPartBase... values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(ResourceLocation key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(SpellPartBase value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SpellPartBase getValue(ResourceLocation key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocation getKey(SpellPartBase value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ResourceLocation> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpellPartBase> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<ResourceLocation, SpellPartBase>> getEntries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getSlaveMap(ResourceLocation slaveMapName, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	public SpellPartBase getValue(String string) {
		return getValue(new ResourceLocation(string));
	}


}
