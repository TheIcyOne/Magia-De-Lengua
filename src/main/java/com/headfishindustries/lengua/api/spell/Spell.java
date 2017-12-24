package com.headfishindustries.lengua.api.spell;

import java.util.ArrayList;
import java.util.List;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.PartRegistry;
import com.headfishindustries.lengua.api.energy.Energy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Spell {
	
	private List<AbstractPart> effects;
	private List<Spell> subSpells;
	private Energy energy;
	private int manaCost;
	private AbstractPartControl control;
	

	public Spell() {
//		this.parts = new ArrayList<Object>();
		this.effects = new ArrayList<AbstractPart>();
		this.subSpells = new ArrayList<Spell>();
		this.energy = new Energy();
		this.manaCost = 0;
	}
	
	public Spell addPart(AbstractPart part){
//		this.parts.add(part);
		this.effects.add(part);
		this.energy.add(part.getEnergyRequirements());
		this.manaCost += part.getFlatManaCost();
		this.manaCost *= part.getManaCostMultiplier();
		return this;
	}
	
	public void setControl(AbstractPartControl c){
		this.control = c;
	}
	
	public Energy getEnergy(){
		return this.energy;
	}
	
	/** This is the one you use. It takes a series of spell part ids, separated by spaces. Don't put spaces in your spell ids, guys.**/
	public static Spell fromString(String in){
		List<String> parts = new ArrayList<String>();
		for (String part : in.split(" ")){
			parts.add(part);
		}
		return fromStringList(parts);
	}
	
	
	public static Spell fromStringList(List<String> in){
		Spell spell = fromStringList(in, 0);
		return spell;
	}
	
	private static Spell fromStringList(List<String> in, int start){
		Spell spell = new Spell();
		
		for (int i = start; i <= (in.size()-1); i++){
			AbstractPart part = PartRegistry.instance.getValue(in.get(i));
			
			if (part == null){ //OH NO WHAT HAVE YOU DONE WRONG
				Lengua.LOGGER.error("String '" + in.get(i) + "' does not refer to a registered spell part. The spell produced will not include this part, and will probably not work.");
				continue;
			}
			
			spell.energy.add(part.getEnergyRequirements());
			
			if (part instanceof AbstractPartControl){
				if (i == 0){
					spell.setControl((AbstractPartControl)part);
				}else{				
				spell.subSpells.add(fromStringList(in, i, (AbstractPartControl) part));
				break;
				}
			}else{
				spell.addEffect(part);
			}
		}
		return spell;
	}
	
	private void addEffect(AbstractPart part) {
		this.effects.add(part);		
	}

	private static Spell fromStringList(List<String> in, int start, AbstractPartControl control){
		Spell spell = new Spell();
		spell.addPart(control);
		
		for (int i = start + 1; i<= (in.size()-1); i++){
			AbstractPart part = PartRegistry.instance.getValue(in.get(i));
			
			if (part == null){
				//OH NO WHAT HAVE YOU DONE WRONG
				//IT'S NOT ME IT'S YOU
				Lengua.LOGGER.error("String '" + in.get(i) + "' does not refer to a registered spell part. The spell produced will not include this part, and will probably not work. Maybe I should just cancel the craft at this point, but bugs are more fun.");
				continue;
			}if (part instanceof AbstractPartControl){
				spell.subSpells.add(fromStringList(in, i+1, (AbstractPartControl)part));
				break;
			}
				spell.addPart(part);
		}
		return spell;
	}
	
	public Spell subSpell(int index){	
		return Spell.fromStringList(this.toStringList(), index);
	}

	public List<String> toStringList(){
		List<String> out = new ArrayList<String>();
		for (AbstractPart part : this.effects){
			out.add(part.getRegistryName().toString());
		}
		for (Spell spell : this.subSpells){
			for (String beep : spell.toStringList()){ //Something something descriptive variable names
				out.add(beep);
			}
		}
		return out;
	}
	
	public String toString(List<String> lst){
		String out = "";
		for (String s : lst){
			out += " ";
			out += s;
		}
		return out.substring(1);
	}

	public void applyEffectEntity(Entity target, World world, Energy modifiers, EntityLivingBase caster){
		for(AbstractPart part : this.effects){
			part.applyEffectEntity(target, world, modifiers, this, caster);
		}
		for (Spell spell : this.subSpells){
			spell.applyEffectEntity(target, world, modifiers, caster);
		}
	}
	
	public void applyEffectBlock(BlockPos target, World world, Energy modifiers, EntityLiving caster){
		for(AbstractPart part : this.effects){
			part.applyEffectBlock(target, world, modifiers, this, caster);
		}
		for (Spell spell : this.subSpells){
			spell.applyEffectBlock(target, world, modifiers, caster);
		}
	}
	
	public void onCast(BlockPos castPos, World world, Energy modifiers, EntityLivingBase caster){
/*		int i = 0;
		for(AbstractPart part : this.effects){
			i++;
			if (part instanceof AbstractPartControl){
				((AbstractPartControl)part).onCast(castPos, world, modifiers, this.subSpell(i+1), caster);
			}	
		}
		
		for (Spell spell : this.subSpells){
			spell.onCast(castPos, world, modifiers, caster);
		}*/
		
		this.control.onCast(castPos, world, modifiers, this.subSpell(0), caster);
	}
	
	public int getManaCost(){
		return this.manaCost;
	}

}