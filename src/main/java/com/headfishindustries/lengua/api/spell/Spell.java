package com.headfishindustries.lengua.api.spell;

import java.util.ArrayList;
import java.util.List;

public class Spell {
	
	private List<SpellPartBase> parts;
	private List<Spell> subSpells;

	public Spell() {
		this.parts = new ArrayList<SpellPartBase>();
		this.subSpells = new ArrayList<Spell>();		
	}
	
	public Spell addPart(SpellPartBase part){
		this.parts.add(part);
		return this;
	}
	
	public Spell addSubSpell(Spell ss){
		this.subSpells.add(ss);
		return this;
	}
	
	/** Input ordered list where each element is a part id.**/
	public static Spell fromString(List<String> in){
		return null;
		
	}

	public static String toString(Spell in){
		return null;
		
	}
}
