
package net.mcreator.abyssoria.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class KeldpDepthRockBlock extends Block {
	public KeldpDepthRockBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(1.2f, 11f).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}
