package com.headfishindustries.lengua.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class RayTraceUtils {

	public static Vec3d toTarget(BlockPos start, BlockPos target){
		return new Vec3d(start.getX() - target.getX(), start.getY() - target.getY(), start.getZ() - target.getZ());	
	}
	
	public static Vec3d toTarget(BlockPos start, Vec3d target){
		return new Vec3d(start.getX() - target.x, start.getY() - target.y, start.getZ() - target.z);	
	}
	
	/** Probably shouldn't use this with really small intervals  **/
	public static List<Vec3d> toVecList(Vec3d in, BlockPos start, double interval, double yOff){
		Vec3d v = in.normalize();
		List<Vec3d> bL = new ArrayList<Vec3d>();
		for (double d = 0; d<= in.lengthVector(); d+=interval){
			bL.add(new Vec3d(start.getX() + v.x * d, start.getY() + v.y * d + yOff, start.getZ() + v.z * d));
		}
		return bL;
	}
	
	public static List<Vec3d> toVecList(Vec3d in, double interval){
		return toVecList(in, new BlockPos(0,0,0), interval, 0);
	}

}
