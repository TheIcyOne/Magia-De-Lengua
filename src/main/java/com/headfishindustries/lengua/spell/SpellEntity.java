package com.headfishindustries.lengua.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class SpellEntity extends Entity{

	public SpellEntity(World world) {
		super(world);
	}
	
	public abstract void setCaster(EntityLiving caster);
	
	public abstract EntityLiving getCaster();
	
	@Override
	public void onEntityUpdate(){
		super.onEntityUpdate();
		
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}

}
