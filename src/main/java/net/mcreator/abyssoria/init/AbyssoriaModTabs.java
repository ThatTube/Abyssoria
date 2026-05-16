
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.abyssoria.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.abyssoria.AbyssoriaMod;

public class AbyssoriaModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AbyssoriaMod.MODID);
	public static final RegistryObject<CreativeModeTab> ABYSSORI = REGISTRY.register("abyssori",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.abyssoria.abyssori")).icon(() -> new ItemStack(AbyssoriaModItems.BLACK_SEA_DEVIL_SPAWN_EGG.get())).displayItems((parameters, tabData) -> {
				tabData.accept(AbyssoriaModItems.BLACK_SEA_DEVIL_SPAWN_EGG.get());
				tabData.accept(AbyssoriaModBlocks.DEPTH_ROCK.get().asItem());
				tabData.accept(AbyssoriaModItems.RAW_FISH.get());
				tabData.accept(AbyssoriaModItems.BAKED_FISH.get());
				tabData.accept(AbyssoriaModBlocks.GLOWING_KELPED_DEPTH_ROCK.get().asItem());
				tabData.accept(AbyssoriaModBlocks.ABYSSAL_KELP.get().asItem());
				tabData.accept(AbyssoriaModItems.SHARP_TEETH.get());
				tabData.accept(AbyssoriaModItems.DEVIL_LIGHT_STEM.get());
				tabData.accept(AbyssoriaModBlocks.DENSE_KELP.get().asItem());
				tabData.accept(AbyssoriaModBlocks.DENSE_KELP_BLOCK.get().asItem());
				tabData.accept(AbyssoriaModBlocks.KELDP_DEPTH_ROCK.get().asItem());
				tabData.accept(AbyssoriaModBlocks.SOUL_DEPTH_ROCK.get().asItem());
				tabData.accept(AbyssoriaModItems.HYDROTHERMAL_STONE.get());
				tabData.accept(AbyssoriaModItems.HADAL_PEARL.get());
				tabData.accept(AbyssoriaModItems.MOLTEN_ROCK.get());
				tabData.accept(AbyssoriaModItems.CORAL_SHARD.get());
			}).withSearchBar().build());
}
