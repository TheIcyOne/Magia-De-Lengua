package com.headfishindustries.lengua.spell;

import java.util.Arrays;

import com.headfishindustries.lengua.api.MalformedSpellException;
import com.headfishindustries.lengua.api.PartRegistry;
import com.headfishindustries.lengua.api.spell.SpellControlBase;
import com.headfishindustries.lengua.api.spell.SpellPartBase;
import com.headfishindustries.lengua.api.spell.SpellTypeBase;
import com.headfishindustries.lengua.defs.DataDefs;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpellUtils {
	
	public void startSpellCast(NBTTagCompound spell, EntityLiving caster, World world){
		
	}
	
	/** Used more to change user-written spells into code-readable ones.
	 * @throws MalformedSpellException **/
	public NBTTagCompound parseSpellString(String toParse) throws MalformedSpellException{
		NBTTagCompound spell = new NBTTagCompound();
		String[] parts = toParse.split("-", 0);
		spell = parseRecursor(parts);
		return spell;
	}
	
	private NBTTagCompound parseRecursor(String[] parts) throws MalformedSpellException{
		NBTTagCompound beepBoop = new NBTTagCompound(); //Descriptive variable names are overrated.
		 for (int i = 0; i <= parts.length; i++){
			 SpellPartBase part = PartRegistry.instance.getValue(DataDefs.MODID + ":" + parts[i]);
			 if (part instanceof SpellControlBase){
				 if (i == parts.length) throw new MalformedSpellException("Spells cannot end with a control part."); //Have you ever tried teaching from the back of the class? Can't control anything from there.
				 String[] subParts = Arrays.copyOfRange(parts, i+1, parts.length);
				 beepBoop.setTag(("part_" + i), parseRecursor(subParts));
			 }else if(part instanceof SpellTypeBase){
				 beepBoop.setString("type_" + i, parts[i]);
			 }else{
				 beepBoop.setString("part_" + i, parts[i]);
			 }
		 }
		return beepBoop;
	}

}
