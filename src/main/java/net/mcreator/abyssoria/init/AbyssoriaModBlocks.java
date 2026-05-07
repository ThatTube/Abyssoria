
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.abyssoria.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.abyssoria.block.SoulDepthRockBlock;
import net.mcreator.abyssoria.block.KeldpDepthRockBlock;
import net.mcreator.abyssoria.block.GlowingKelpedDepthRockBlock;
import net.mcreator.abyssoria.block.DepthRockBlock;
import net.mcreator.abyssoria.block.DenseKelpBlockBlock;
import net.mcreator.abyssoria.block.DenseKelpBlock;
import net.mcreator.abyssoria.block.AbyssalKelpBlock;
import net.mcreator.abyssoria.AbyssoriaMod;

public class AbyssoriaModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, AbyssoriaMod.MODID);
	public static final RegistryObject<Block> DEPTH_ROCK = REGISTRY.register("depth_rock", () -> new DepthRockBlock());
	public static final RegistryObject<Block> GLOWING_KELPED_DEPTH_ROCK = REGISTRY.register("glowing_kelped_depth_rock", () -> new GlowingKelpedDepthRockBlock());
	public static final RegistryObject<Block> ABYSSAL_KELP = REGISTRY.register("abyssal_kelp", () -> new AbyssalKelpBlock());
	public static final RegistryObject<Block> DENSE_KELP = REGISTRY.register("dense_kelp", () -> new DenseKelpBlock());
	public static final RegistryObject<Block> DENSE_KELP_BLOCK = REGISTRY.register("dense_kelp_block", () -> new DenseKelpBlockBlock());
	public static final RegistryObject<Block> KELDP_DEPTH_ROCK = REGISTRY.register("keldp_depth_rock", () -> new KeldpDepthRockBlock());
	public static final RegistryObject<Block> SOUL_DEPTH_ROCK = REGISTRY.register("soul_depth_rock", () -> new SoulDepthRockBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
