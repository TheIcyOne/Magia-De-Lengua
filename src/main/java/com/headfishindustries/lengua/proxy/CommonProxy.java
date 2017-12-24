package com.headfishindustries.lengua.proxy;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.defs.ContentDefs;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e){
		ContentDefs.initEntities();
	}
	
	public void init(FMLInitializationEvent e){
		Lengua.LOGGER.info("Initializing..");
		ContentDefs.INSTANCE.registerParts();
	}
	
	public void postInit(FMLPostInitializationEvent e){
		
	}
}
