package com.headfishindustries.lengua.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.spell.SpellPartBase;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class PartRegistry implements IForgeRegistry<SpellPartBase>{
	
	public static PartRegistry instance = new PartRegistry();
	
	private BiMap<ResourceLocation, SpellPartBase> partMap;
	private BiMap<ResourceLocation, String> names;

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
		if(this.containsKey(value.getRegistryName())){
			Lengua.LOGGER.warn(String.format("Duplicate spell part entry detected for key %s, the additional part will not be added.", value.getRegistryName()));
			return;
		}else{ //Highly necessary. If I didn't have this, maybe I'd have to be more sarcastic about things.
			this.partMap.put(value.getRegistryName(), value);
			this.names.put(value.getRegistryName(), value.getPartWord());
		}
	}

	@Override
	public void registerAll(SpellPartBase... values) {
		for (SpellPartBase part : values){
			register(part);
		}
	}

	@Override
	public boolean containsKey(ResourceLocation key) {
		return partMap.containsKey(key);
	}

	@Override
	public boolean containsValue(SpellPartBase value) {
		return partMap.containsValue(value);
	}

	@Override
	public SpellPartBase getValue(ResourceLocation key) {
		return partMap.get(key);
	}

	@Override
	public ResourceLocation getKey(SpellPartBase value) {
		return partMap.inverse().get(value);
	}

	@Override
	public Set<ResourceLocation> getKeys() {
		return partMap.keySet();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpellPartBase> getValues() {
		return (List<SpellPartBase>) partMap.values();
	}

	@Override
	public Set<Entry<ResourceLocation, SpellPartBase>> getEntries() {
		return partMap.entrySet();
	}

	@Override
	public <T> T getSlaveMap(ResourceLocation slaveMapName, Class<T> type) {
		//Until such a time as I can find a use for this, I'm not going to deal with it. Feel free to tell me such a use, and I'll deal with it. While complaining about you.
		return null;
	}

	public SpellPartBase getValue(String string) {
		return getValue(getRL(string));
	}
	
	public ResourceLocation getRL(String in){
		return this.names.inverse().get(in);
	}
	
	public String getPartName(ResourceLocation in){
		return this.names.get(in);
	}


}
