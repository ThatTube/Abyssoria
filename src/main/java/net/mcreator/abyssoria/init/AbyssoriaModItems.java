
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.abyssoria.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.abyssoria.item.SharpTeethItem;
import net.mcreator.abyssoria.item.RawFishItem;
import net.mcreator.abyssoria.item.DevilLightStemItem;
import net.mcreator.abyssoria.item.BakedFishItem;
import net.mcreator.abyssoria.AbyssoriaMod;

public class AbyssoriaModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, AbyssoriaMod.MODID);
	public static final RegistryObject<Item> BLACK_SEA_DEVIL_SPAWN_EGG = REGISTRY.register("black_sea_devil_spawn_egg", () -> new ForgeSpawnEggItem(AbyssoriaModEntities.BLACK_SEA_DEVIL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> DEPTH_ROCK = block(AbyssoriaModBlocks.DEPTH_ROCK);
	public static final RegistryObject<Item> RAW_FISH = REGISTRY.register("raw_fish", () -> new RawFishItem());
	public static final RegistryObject<Item> BAKED_FISH = REGISTRY.register("baked_fish", () -> new BakedFishItem());
	public static final RegistryObject<Item> GLOWING_KELPED_DEPTH_ROCK = block(AbyssoriaModBlocks.GLOWING_KELPED_DEPTH_ROCK);
	public static final RegistryObject<Item> ABYSSAL_KELP = block(AbyssoriaModBlocks.ABYSSAL_KELP);
	public static final RegistryObject<Item> SHARP_TEETH = REGISTRY.register("sharp_teeth", () -> new SharpTeethItem());
	public static final RegistryObject<Item> DEVIL_LIGHT_STEM = REGISTRY.register("devil_light_stem", () -> new DevilLightStemItem());
	public static final RegistryObject<Item> DENSE_KELP = block(AbyssoriaModBlocks.DENSE_KELP);
	public static final RegistryObject<Item> DENSE_KELP_BLOCK = block(AbyssoriaModBlocks.DENSE_KELP_BLOCK);
	public static final RegistryObject<Item> KELDP_DEPTH_ROCK = block(AbyssoriaModBlocks.KELDP_DEPTH_ROCK);
	public static final RegistryObject<Item> SOUL_DEPTH_ROCK = block(AbyssoriaModBlocks.SOUL_DEPTH_ROCK);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
