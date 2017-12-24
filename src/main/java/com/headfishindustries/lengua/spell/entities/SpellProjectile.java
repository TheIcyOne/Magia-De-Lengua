package com.headfishindustries.lengua.spell.entities;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.Spell;
import com.headfishindustries.lengua.spell.SpellEntity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class SpellProjectile extends SpellEntity{
	
	protected double baseSpeed;
	protected double modifiedSpeed;
	
	public SpellProjectile(World world){
		super(world);
	}
	
	public SpellProjectile(World world, Energy energies, EntityLivingBase c, Spell s) {
		super(world);
		this.baseSpeed = 1.5f;
		this.energy = energies;
		this.spell = s;
		this.caster = c;
		this.modifiedSpeed = this.getSpeedMod();
		this.setRotation(c.rotationYaw, c.rotationPitch);
		this.setVelocity(this.modifiedSpeed * Math.sin(Math.toRadians(-this.rotationYaw)) * Math.cos(Math.toRadians(this.rotationPitch)), this.modifiedSpeed * Math.sin(Math.toRadians(-this.rotationPitch)), this.modifiedSpeed * Math.cos(Math.toRadians(-this.rotationYaw)) * Math.cos(Math.toRadians(this.rotationPitch)));
		this.setSize(0.5f, 0.5f);

	}
	
	@Override
	public double getSpeedMod(){
		double speed = this.baseSpeed;
		double mod = 1.2 * (1 + this.energy.air - this.energy.earth);
		speed *= Math.max(mod, 1);
		return speed;
	}


	@Override
	public void setCaster(EntityLiving caster) {
		this.caster = caster;
	}

	@Override
	public EntityLivingBase getCaster() {
		return this.caster;
	}
	
	@Override
	public String toString(){
		return("SpellProjectile: " + this.getPosition());
	}

}
