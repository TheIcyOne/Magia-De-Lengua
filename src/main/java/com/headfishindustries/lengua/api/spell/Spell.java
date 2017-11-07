package com.headfishindustries.lengua.api.spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.MalformedSpellException;
import com.headfishindustries.lengua.api.PartRegistry;
import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.nbt.NBTTagCompound;

public class Spell {
	
	private List<Object> parts;
	

	public Spell() {
		this.parts = new ArrayList<Object>();		
	}
	
	public Spell addPart(SpellPartBase part){
		this.parts.add(part);
		return this;
	}	
	/** This is the one you use. It takes a list of Spell Part ids. **/
	public static Spell fromString(List<String> in, String modid){
		Spell spell = fromString(in, modid, 0);
		return spell;
	}
	
	private static Spell fromString(List<String> in, String modid, int start){
		Spell spell = new Spell();
		
		for (int i = start; i<= (in.size()-1); i++){
			SpellPartBase part = PartRegistry.instance.getValue(modid + ":" + in.get(i));
			
			if (part == null){
				//OH NO WHAT HAVE YOU DONE WRONG
				Lengua.LOGGER.error("String '" + in.get(i) + "' does not refer to a registered spell part. The spell produced will not include this part, and will probably not work.");
				continue;
			}if (part instanceof SpellControlBase){
				spell.parts.add(fromString(in, modid, i, (SpellControlBase) part));
				break;
			}
				spell.addPart(part);
		}
		
		return spell;
	}
	
	private static Spell fromString(List<String> in, String modid, int start, SpellControlBase control){
		Spell spell = new Spell();
		spell.addPart(control);
		
		for (int i = start + 1; i<= (in.size()-1); i++){
			SpellPartBase part = PartRegistry.instance.getValue(modid + ":" + in.get(i));
			
			if (part == null){
				//OH NO WHAT HAVE YOU DONE WRONG
				//IT'S NOT ME IT'S YOU
				Lengua.LOGGER.error("String '" + in.get(i) + "' does not refer to a registered spell part. The spell produced will not include this part, and will probably not work.");
				continue;
			}if (part instanceof SpellControlBase){
				spell.parts.add(fromString(in, modid, i+1));
				break;
			}
				spell.addPart(part);
		}
		return spell;
	}

	public String toString(Spell in){
		return null;
		
	}
}
