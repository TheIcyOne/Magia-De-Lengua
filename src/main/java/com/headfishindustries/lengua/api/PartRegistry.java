package com.headfishindustries.lengua.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.spell.AbstractPart;
import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistry;

public class PartRegistry implements IForgeRegistry<AbstractPart>{
	
	public static PartRegistry instance = new PartRegistry();
	
	private BiMap<ResourceLocation, AbstractPart> partMap;
	private BiMap<ResourceLocation, String> names;
	private boolean isLocked = false;
	
	public void lock(){
		//Exists purely to piss people off.
		//Reflect this and I'll set Nut on you. Just register your stuff at the right time.
		if(Loader.instance().activeModContainer().getModId().equals(DataDefs.MODID)){
			this.isLocked = true;
		}else{
			//lolno
		}
	}

	@Override
	public Iterator<AbstractPart> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<AbstractPart> getRegistrySuperType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(AbstractPart value) {
		if (this.isLocked){
			Lengua.LOGGER.error(String.format("Modid %s has attempted to register a spell part, but the registry is locked. Poke its author.", Loader.instance().activeModContainer().getModId()));
		}
		if(this.containsKey(value.getRegistryName())){
			Lengua.LOGGER.warn(String.format("Duplicate spell part entry detected for key %s, the new part.", value.getRegistryName()));
			return;
		}else{ //Highly necessary. If I didn't have this, maybe I'd have to be more sarcastic about things.
			this.partMap.put(value.getRegistryName(), value);
			this.names.put(value.getRegistryName(), value.getPartWord());
		}
	}

	@Override
	public void registerAll(AbstractPart... values) {
		for (AbstractPart part : values){
			register(part);
		}
	}

	@Override
	public boolean containsKey(ResourceLocation key) {
		return partMap.containsKey(key);
	}

	@Override
	public boolean containsValue(AbstractPart value) {
		return partMap.containsValue(value);
	}

	@Override
	public AbstractPart getValue(ResourceLocation key) {
		return partMap.get(key);
	}

	@Override
	public ResourceLocation getKey(AbstractPart value) {
		return partMap.inverse().get(value);
	}

	@Override
	public Set<ResourceLocation> getKeys() {
		return partMap.keySet();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractPart> getValues() {
		return (List<AbstractPart>) partMap.values();
	}

	@Override
	public Set<Entry<ResourceLocation, AbstractPart>> getEntries() {
		return partMap.entrySet();
	}

	@Override
	public <T> T getSlaveMap(ResourceLocation slaveMapName, Class<T> type) {
		//Until such a time as I can find a use for this, I'm not going to deal with it. Feel free to tell me such a use, and I'll deal with it. While complaining about you.
		return null;
	}

	public AbstractPart getValue(String string) {
		return getValue(getRL(string));
	}
	
	public ResourceLocation getRL(String in){
		return this.names.inverse().get(in);
	}
	
	public String getPartName(ResourceLocation in){
		return this.names.get(in);
	}
	
	public String getPartName(AbstractPart in){
		return this.names.get(partMap.inverse().get(in));
	}


}
