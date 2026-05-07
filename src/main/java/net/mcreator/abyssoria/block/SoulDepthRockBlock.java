
package net.mcreator.abyssoria.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class SoulDepthRockBlock extends Block {
	public SoulDepthRockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SOUL_SOIL).strength(1.15f, 10.5f).requiresCorrectToolForDrops().speedFactor(0.8f).jumpFactor(0.9f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}
