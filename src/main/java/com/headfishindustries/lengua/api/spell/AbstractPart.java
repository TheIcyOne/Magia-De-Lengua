package com.headfishindustries.lengua.api.spell;


import com.headfishindustries.lengua.api.energy.Energy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractPart extends IForgeRegistryEntry.Impl<AbstractPart>{
	
	private String partWord;
	
	/** e.g. return new Energy(100, 100, 100, 100, 100, 100) **/
	public abstract Energy getEnergyRequirements();

	/** Either this or applyEffectBlock should be called on all parts in a spell by the end of execution,
	 *  provided that the spell is not cancelled at some point e.g. because nothing was hit.
	 **/
	public abstract EnumActionResult applyEffectEntity(Entity target, World world, Energy modifiers, Spell spell, EntityLiving caster);
	
	public abstract EnumActionResult applyEffectBlock(BlockPos target, World world, Energy modifiers, Spell spell, EntityLiving caster);
	
	/**
	 * Note that mana cost is calculated iteratively from each spell part in the spell, so a functionally identical spell
	 * will have different costs depending on the way it is written. While other things described in this mod as features may well be bugs,
	 * this one actually is a feature. Possibly.
	 **/
	
	/** Flat cost added to spell. **/
	public abstract float getFlatManaCost();
	
	/** Multiplier applied to current spell cost. **/
	public float getManaCostMultiplier(){
		return 1f;
	}
	
	/** Returns the word which is used on the player end when adding the part to a spell. Primarily used in {@link} SpellUtils.parseSpell() **/
	public String getPartWord(){
		return this.partWord;
	}
	
	//TODO: Maybe energy affinities? Definitely not yet.
	
	//TODO: Reagents
	public abstract ItemStack[] getReagents();
}
