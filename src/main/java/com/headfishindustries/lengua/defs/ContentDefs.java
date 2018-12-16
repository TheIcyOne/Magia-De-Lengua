package com.headfishindustries.lengua.defs;

import com.headfishindustries.lengua.Lengua;
import com.headfishindustries.lengua.api.PartRegistry;
import com.headfishindustries.lengua.api.spell.AbstractPart;
import com.headfishindustries.lengua.block.BlockSpellTable;
import com.headfishindustries.lengua.item.SpellBase;
import com.headfishindustries.lengua.spell.entities.SpellProjectile;
import com.headfishindustries.lengua.spell.part.action.ActionBreakBlock;
import com.headfishindustries.lengua.spell.part.action.ActionDamageMagical;
import com.headfishindustries.lengua.spell.part.action.ActionDamagePhysical;
import com.headfishindustries.lengua.spell.part.control.ControlPartProjectile;
import com.headfishindustries.lengua.spell.part.control.ControlPartTouch;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ContentDefs {
	public static final ContentDefs INSTANCE = new ContentDefs();
	
	
	public SpellBase itemSpellBase;
	
	public BlockSpellTable blockSpellTable;
	public ItemBlock itemSpellTable;
	
	public ControlPartProjectile partProjectile;
	public ControlPartTouch partTouch;
	
	public ActionDamagePhysical damagePhysical;
	public ActionDamageMagical damageMagical;
	public ActionBreakBlock breakBlock;
	
	public EntityEntry projectileEnt;

	public ContentDefs() {
		this.itemSpellBase = new SpellBase();
		this.blockSpellTable = new BlockSpellTable();
		this.itemSpellTable = new ItemBlock(this.blockSpellTable);
		
		this.partProjectile = new ControlPartProjectile();
		this.partTouch = new ControlPartTouch();
		this.damagePhysical = new ActionDamagePhysical();
		this.damageMagical = new ActionDamageMagical();
		this.breakBlock = new ActionBreakBlock();
		
		this.projectileEnt = new EntityEntry(SpellProjectile.class, "spell_projectile");
	}
	
	@SubscribeEvent
	public void registerBlock(RegistryEvent.Register<Block> event) {
		Lengua.LOGGER.info("Registering blocks.");
		IForgeRegistry<Block> reg = event.getRegistry();
		
		//Spell crafting
		register(this.blockSpellTable, reg, "spell_writing_table");
				
	}
	
	private void register(Block block, IForgeRegistry<Block> reg, String name){
		reg.register(block.setRegistryName(DataDefs.MODID, name));
		Lengua.LOGGER.debug(name);
	}
	
	@SubscribeEvent
	public void registerItem(RegistryEvent.Register<Item> event) {
		Lengua.LOGGER.info("Registering items.");
		
		IForgeRegistry<Item> reg = event.getRegistry();
		
		//ItemBlocks
		registerIB(this.itemSpellTable, reg, "spell_writing_table");
		//Spells and stuff
		register(this.itemSpellBase, reg, "spell");
		
	}
	
	private void registerIB(ItemBlock block, IForgeRegistry<Item> reg, String name){
		reg.register(block.setRegistryName(name));
	}
	
	private void register(Item item, IForgeRegistry<Item> reg, String name){
		reg.register(item.setRegistryName(DataDefs.MODID, name));
		Lengua.LOGGER.debug(name);
	}
	
	public void registerParts(){
		Lengua.LOGGER.info("Registering spell parts.");
		register(this.partProjectile, "control_projectile");
		register(this.partTouch, "control_touch");
		register(this.damagePhysical, "action_damage_physical");
		register(this.damageMagical, "action_damage_magical");
		register(this.breakBlock, "action_break_block");
	}
	
	private void register(AbstractPart part, String name){
		PartRegistry.instance.register(part.setRegistryName(DataDefs.MODID, name));
		Lengua.LOGGER.debug(name);
	}
	
	public static void initEntities(){
		Lengua.LOGGER.info("Registering entities.");
		EntityRegistry.registerModEntity(new ResourceLocation("spell_projectile"), SpellProjectile.class, "spell_projectile", 423, Lengua.instance, 128, 1, true);
	}

}
