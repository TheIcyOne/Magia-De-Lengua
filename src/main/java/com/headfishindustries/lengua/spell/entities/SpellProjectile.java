package com.headfishindustries.lengua.spell.entities;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.spell.SpellEntity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class SpellProjectile extends SpellEntity{
	
	private final double baseSpeed = 2;
	private double modifiedSpeed;
	private Energy energy;
	private EntityLiving caster;
	
	public SpellProjectile(World world, Energy energies, EntityLiving castEnt) {
		super(world);
		this.energy = energies;
		this.modifiedSpeed = this.getSpeedMod();
		this.caster = castEnt;
		this.setVelocity(this.modifiedSpeed * Math.sin(this.rotationYaw), this.modifiedSpeed * Math.sin(this.rotationPitch), this.modifiedSpeed * Math.cos(this.rotationYaw));
	}
	
	@Override
	public void onEntityUpdate(){
		super.onEntityUpdate();
		this.setRotation((float)Math.tan(this.motionX/this.motionZ), (float) Math.tan(this.motionY/Math.sqrt(this.motionX*this.motionX + this.motionZ* this.motionZ )));
	}
	
	
	
	private double getSpeedMod(){
		double speed = this.baseSpeed;
		speed *= Math.max( 1.2 * (this.energy.air - this.energy.earth), 1);
		return speed;
	}

}
