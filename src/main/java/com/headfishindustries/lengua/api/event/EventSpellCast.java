package com.headfishindustries.lengua.api.event;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventSpellCast extends Event{
	private NBTTagCompound spell;
	private float manaCost;
	private EntityLiving caster;
	
	public EventSpellCast(NBTTagCompound spell, EntityLiving caster, float manaCost) {
		this.spell = spell;
		this.caster = caster;
		this.manaCost = manaCost;
	}
	
	public static class Pre extends EventSpellCast {
		
		public Pre(NBTTagCompound spell, EntityLiving caster, float manaCost) {
			super(spell, caster, manaCost);
		}
	}
	
	public static class Post extends EventSpellCast {

		public Post(NBTTagCompound spell, EntityLiving caster, float manaCost) {
			super(spell, caster, manaCost);
		}
	}
	
	//The wonders of automated get/setters. It's nice not to do the work myself.
	public EntityLiving getCaster() {
		return caster;
	}
	
	protected void setCaster(EntityLiving caster) {
		this.caster = caster;
	}
	
	public float getManaCost() {
		return manaCost;
	}
	
	protected void setManaCost(float manaCost) {
		this.manaCost = manaCost;
	}
	
	public NBTTagCompound getSpell() {
		return spell;
	}
	
	protected void setSpell(NBTTagCompound spell) {
		this.spell = spell;
	}
	
	
}
