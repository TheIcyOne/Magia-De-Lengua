package com.headfishindustries.lengua;

import org.apache.http.config.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.headfishindustries.lengua.api.PartRegistry;
import com.headfishindustries.lengua.defs.DataDefs;
import com.headfishindustries.lengua.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DataDefs.MODID, name = DataDefs.NAME, version = DataDefs.VERSION, acceptedMinecraftVersions = "[1.12, 1.13]")
public class Lengua {
	@Instance(value=DataDefs.MODID)
	public static Lengua instance;
	public static final Logger LOGGER = LogManager.getLogger(DataDefs.MODID);
	
	@SidedProxy(clientSide="com.headfishindustries.lengua.proxy.ClientProxy", serverSide="com.headfishindustries.lengua.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		proxy.preInit(e);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		proxy.init(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		proxy.postInit(e);
	}

	
}