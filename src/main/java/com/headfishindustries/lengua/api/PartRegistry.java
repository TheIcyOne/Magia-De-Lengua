package com.headfishindustries.lengua.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.spell.AbstractPart;
import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistry;

public class PartRegistry implements IForgeRegistry<AbstractPart>{
	
	public static PartRegistry instance = new PartRegistry();
	
	private BiMap<ResourceLocation, AbstractPart> partMap = HashBiMap.create();
	private boolean isLocked = false;
	
	public List<ResourceLocation> getRLs(){
		List<ResourceLocation> parts = new ArrayList<ResourceLocation>();
		
		for (ResourceLocation rl : partMap.keySet()) parts.add(rl);
		
		return parts;
	}

	
	public void lock(){
		//Exists purely to piss people off.
		//Reflect this and I'll set Nut on you. Just register your stuff at the right time.
		if(Loader.instance().activeModContainer().getModId().equals(DataDefs.MODID)){
			Lengua.LOGGER.debug("Closing part registry. Who knows, this might actually be useful one day.");
			this.isLocked = true;
		}else{
			//lolno
			Lengua.LOGGER.error("The mod '" + Loader.instance().activeModContainer().getModId() + "' tried to lock a registry early. Bad mod, no cookies.");
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
		if (value == null){
			Lengua.LOGGER.error("Null value passed to registry. You monster");
			return;
		} else if (value.getRegistryName() == null){
			Lengua.LOGGER.error(value.getPartWord() + " has no registry name.");
			return;
		} else if (value.getRegistryName().toString().contains(" ")){
			Lengua.LOGGER.error("The registry name for part '" + value.getPartWord() + "' contains a space. This is not allowed, naughty mod.");
		}

		if (this.isLocked){//Highly necessary. If I didn't have this, maybe I'd have to be more sarcastic about things.
			Lengua.LOGGER.error(String.format("Modid %s has attempted to register a spell part, but the registry is locked. Poke its author so they hurry up next time.", Loader.instance().activeModContainer().getModId()));
		}
		if(this.containsKey(value.getRegistryName())){
			Lengua.LOGGER.warn(String.format("Duplicate spell part entry detected for key %s, the new part.", value.getRegistryName()));
			return;
		}else{
			this.partMap.put(
					value.getRegistryName(), value);
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
		return getValue(new ResourceLocation(string));
	}

}
