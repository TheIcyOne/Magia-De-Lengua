package com.headfishindustries.lengua.api.spell;

import java.util.ArrayList;
import java.util.List;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.PartRegistry;
import com.headfishindustries.lengua.api.energy.Energy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Spell {
	
	private List<Object> parts;
	private List<SpellPartBase> effects;
	private List<Spell> subSpells;
	

	public Spell() {
		this.parts = new ArrayList<Object>();
		this.effects = new ArrayList<SpellPartBase>();
		this.subSpells = new ArrayList<Spell>();
		
	}
	
	public Spell addPart(SpellPartBase part){
		this.parts.add(part);
		this.effects.add(part);
		return this;
	}	
	/** This is the one you use. It takes a list of Spell Part ids. **/
	public static Spell fromString(List<String> in){
		Spell spell = fromString(in, 0);
		return spell;
	}
	
	private static Spell fromString(List<String> in, int start){
		Spell spell = new Spell();
		
		for (int i = start; i<= (in.size()-1); i++){
			SpellPartBase part = PartRegistry.instance.getValue(in.get(i));
			
			if (part == null){
				//OH NO WHAT HAVE YOU DONE WRONG
				Lengua.LOGGER.error("String '" + in.get(i) + "' does not refer to a registered spell part. The spell produced will not include this part, and will probably not work.");
				continue;
			}if (part instanceof SpellControlBase){
				spell.parts.add(fromString(in, i, (SpellControlBase) part));
				spell.subSpells.add(fromString(in, i, (SpellControlBase) part));
				break;
			}
				spell.addPart(part);
		}
		
		return spell;
	}
	
	private static Spell fromString(List<String> in, int start, SpellControlBase control){
		Spell spell = new Spell();
		spell.addPart(control);
		
		for (int i = start + 1; i<= (in.size()-1); i++){
			SpellPartBase part = PartRegistry.instance.getValue(in.get(i));
			
			if (part == null){
				//OH NO WHAT HAVE YOU DONE WRONG
				//IT'S NOT ME IT'S YOU
				Lengua.LOGGER.error("String '" + in.get(i) + "' does not refer to a registered spell part. The spell produced will not include this part, and will probably not work. Maybe I should just cancel the craft at this point, but bugs are more fun.");
				continue;
			}if (part instanceof SpellControlBase){
				spell.parts.add(fromString(in, i+1, (SpellControlBase)part));
				break;
			}
				spell.addPart(part);
		}
		return spell;
	}

	public String toString(Spell in){
		return null;
		
	}

	public void applyEffectEntity(Entity target, World world, Energy modifiers, EntityLiving caster){
		for(SpellPartBase part : this.effects){
			part.applyEffectEntity(target, world, modifiers, this, caster);
		}
		for (Spell spell : this.subSpells){
			spell.applyEffectEntity(target, world, modifiers, caster);
		}
	}
	
	public void applyEffectBlock(BlockPos target, World world, Energy modifiers, EntityLiving caster){
		for(SpellPartBase part : this.effects){
			part.applyEffectBlock(target, world, modifiers, this, caster);
		}
		for (Spell spell : this.subSpells){
			spell.applyEffectBlock(target, world, modifiers, caster);
		}
	}
}
