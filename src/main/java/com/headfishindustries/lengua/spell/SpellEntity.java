package com.headfishindustries.lengua.spell;

import com.headfishindustries.lengua.api.energy.Energy;
import com.headfishindustries.lengua.api.spell.Spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class SpellEntity extends Entity{
	
	protected double baseSpeed = 0;
	protected double modifiedSpeed;
	protected Energy energy;
	protected EntityLivingBase caster;
	int duration = 200;
	protected Spell spell;

	public SpellEntity(World world) {
		super(world);
	}
	
	public SpellEntity(World world, Energy energies, EntityLivingBase caster2, Spell s) {
		super(world);
		this.spell = s;
		this.energy = energies;
		this.modifiedSpeed = this.getSpeedMod();
		this.setRotation(caster2.rotationYaw, caster2.rotationPitch);
		}

	
	public abstract void setCaster(EntityLiving caster);
	
	public abstract EntityLivingBase getCaster();
	
	@Override
	public void onEntityUpdate(){
		super.onEntityUpdate();
		this.checkCollisions();
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if (this.ticksExisted >= this.duration){
			this.setDead();
		}
	}
	
	public void checkCollisions(){
		for (Entity hit : this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox())){
			if (hit instanceof SpellEntity || hit.equals(this.caster)) continue;
			if (!(hit instanceof EntityLivingBase)) continue;
			this.spell.applyEffectEntity((EntityLivingBase)hit, this.world, this.energy, this.caster);
			this.setDead();
		}
		
	//	Vec3d position = new Vec3d(this.posX,this.posY, this.posZ);
	//	Vec3d motion = new Vec3d(this.motionX, this.motionY, this.motionZ);
		
		
	//	RayTraceResult r = this.world.rayTraceBlocks(position, position.add(motion));
	//	if (r != null) {
	//		this.spell.applyEffectBlock(r.getBlockPos(), this.world, this.energy, this.caster);
	//		this.setDead();
	//	}
		BlockPos curPos = new BlockPos(Math.round(this.posX), Math.round(this.posY), Math.round(this.posZ));
		BlockPos nextPos = new BlockPos(Math.round(this.posX + this.motionX), Math.round(this.posY + this.motionY), Math.round(this.posZ + this.motionZ));
		
		if (this.world.getBlockState(curPos).isSideSolid(world, pos, side))
	}
	
	
	public double getSpeedMod(){
		return 0f;
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
