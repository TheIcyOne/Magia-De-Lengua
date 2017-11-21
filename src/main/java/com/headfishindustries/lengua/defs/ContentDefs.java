package com.headfishindustries.lengua.defs;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.spell.AbstractPart;
import com.headfishindustries.lengua.block.BlockSpellTable;
import com.headfishindustries.lengua.item.SpellBase;
import com.headfishindustries.lengua.spell.part.action.ActionDamagePhysical;
import com.headfishindustries.lengua.spell.part.control.ControlPartProjectile;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ContentDefs {
	public static final ContentDefs INSTANCE = new ContentDefs();
	
	
	public SpellBase itemSpellBase;
	
	public BlockSpellTable blockSpellTable;
	
	public ControlPartProjectile partProjectile;
	public ActionDamagePhysical damagePhysical;

	public ContentDefs() {
		this.itemSpellBase = new SpellBase();
		this.blockSpellTable = new BlockSpellTable();
		
		this.partProjectile = new ControlPartProjectile();
		this.damagePhysical = new ActionDamagePhysical();
	}
	
	@SubscribeEvent
	public void registerBlock(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> reg = event.getRegistry();
		
		//Spell crafting
		register(this.blockSpellTable, reg, "spell_table");
				
	}
	
	private void register(Block block, IForgeRegistry<Block> reg, String name){
		reg.register(block.setRegistryName(DataDefs.MODID, name));
	}
	
	@SubscribeEvent
	public void registerItem(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> reg = event.getRegistry();
		
		//Spells and stuff
		register(this.itemSpellBase, reg, "spell");
		
	}
	
	private void register(Item item, IForgeRegistry<Item> reg, String name){
		reg.register(item.setRegistryName(DataDefs.MODID, name));
	}
	
	
	@SubscribeEvent
	public void registerParts(RegistryEvent.Register<AbstractPart> e){
		Lengua.LOGGER.debug("HEYO, we're registering some spell parts. All good with you guys?");
		IForgeRegistry<AbstractPart> reg = e.getRegistry();
		register(this.partProjectile, reg, "control_projectile");
		register(this.damagePhysical, reg, "action_damage_physical");
	}
	
	private void register(AbstractPart part, IForgeRegistry<AbstractPart> reg, String name){
		reg.register(part.setRegistryName(DataDefs.MODID, name));
	}

}
