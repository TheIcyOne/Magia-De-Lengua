package com.headfishindustries.lengua.api.energy;

import javax.annotation.Nullable;

public class Energy {
	public float air;
	public float water;
	public float fire;
	public float earth;
	public float light;
	public float dark;
	
	public static Energy AIR = new Energy(1, 0, 0, 0, 0, 0);
	public static Energy WATER = new Energy(0, 1, 0, 0, 0, 0);
	public static Energy FIRE = new Energy(0, 0, 1, 0, 0, 0);
	public static Energy EARTH = new Energy(0, 0, 0, 1, 0, 0);
	public static Energy LIGHT = new Energy(0, 0, 0, 0, 1, 0);
	public static Energy DARK = new Energy(0, 0, 0, 0, 0, 1);
	
	public Energy(float par1Air, float par2Water, float par3Fire, float par4Earth, float par5Light, float par6Dark){
		this.air = par1Air;
		this.water = par2Water;
		this.fire = par3Fire;
		this.earth = par4Earth;
		this.light = par5Light;
		this.dark = par6Dark;
	}
	
	public Energy(){
		this.air = this.water = this.fire = this.earth = this.light = this.dark = 0;
	}
	
	public Energy(float allValues){
		this.air = this.water = this.fire = this.earth = this.light = this.dark = allValues;
	}
	
	public Energy add(float toAdd){
		this.air += toAdd;
		this.water += toAdd;
		this.fire += toAdd;
		this.earth += toAdd;
		this.light += toAdd;
		this.dark += toAdd;
		return this;
	}
	
	public Energy add(Energy toAdd){
		this.air += toAdd.air;
		this.water += toAdd.water;
		this.fire += toAdd.fire;
		this.earth += toAdd.earth;
		this.light += toAdd.light;
		this.dark += toAdd.dark;
		return this;
	}
	
	public Energy subtract(Energy toSubtract){
		this.air -= toSubtract.air;
		this.water -= toSubtract.water;
		this.fire -= toSubtract.fire;
		this.earth -= toSubtract.earth;
		this.light -= toSubtract.light;
		this.dark -= toSubtract.dark;
		return this;
	}
	
	/** Use to multiply each index in the Energy by the corresponding one in the input.**/
	public Energy multiply(Energy toMultiply){
		this.air *= toMultiply.air;
		this.water *= toMultiply.water;
		this.fire *= toMultiply.fire;
		this.earth *= toMultiply.earth;
		this.light *= toMultiply.light;
		this.dark *= toMultiply.dark;
		return this;
	}
	
	public Energy multiply(int mult) {
		this.air *= mult;
		this.water *= mult;
		this.fire *= mult;
		this.earth *= mult;
		this.light *= mult;
		this.dark *= mult;
		return this;
	}
	
	/** Clamps energy values to within the given range, sets value for all to max if max < min**/
	
	public Energy clamp(@Nullable Float min, @Nullable Float max){
		if (!(min == null)){
			this.air = Math.max(this.air, min);
			this.water = Math.max(this.water, min);
			this.fire = Math.max(this.fire, min);
			this.earth = Math.max(this.earth, min);
			this.light = Math.max(this.light, min);
			this.dark = Math.max(this.dark, min);
		}
		
		if (!(max == null)){
			this.air = Math.min(this.air, min);
			this.water = Math.min(this.water, min);
			this.fire = Math.min(this.fire, min);
			this.earth = Math.min(this.earth, min);
			this.light = Math.min(this.light, min);
			this.dark = Math.min(this.dark, min);
		}
		return this;
	}
		
	
	@Override
	public String toString(){
		return "Energy types: " + "air: " + this.air + " water: " + this.water + " fire: " + this.fire + " earth: " + this.earth + " light: " + this.light + " dark: " + this.dark;
	}
	
	/** Sometimes I think there might be a better way to do this. This function tells me that if there were, I wouldn't want it. **/
	@Override
	public boolean equals(Object in){
		if (!(in instanceof Energy)) return false;
		Energy energy = (Energy) in;
		return (this.air == energy.air && this.water == energy.water && this.fire == energy.fire && this.earth == energy.earth && this.light == energy.light && this.dark == energy.dark);
	}
	
	
}
