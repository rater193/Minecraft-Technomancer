package net.rater193.technomancer.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class BlockClickLight extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public BlockClickLight(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {

        //Make the light block toggle its light
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            level.setBlock(pos, state.cycle(LIT), 3);
        }

        return super.use(state, level, pos, player, hand, result);
    }
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(LIT, Boolean.valueOf(blockPlaceContext.getLevel().hasNeighborSignal(blockPlaceContext.getClickedPos())));
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block neighborBlock, BlockPos neighborBlockPos, boolean b) {
        if(!(neighborBlock instanceof BlockClickLight)) {
            if (!level.isClientSide) {
                boolean flag = blockState.getValue(LIT);
                if (flag != level.hasNeighborSignal(blockPos)) {
                    if (flag) {
                        level.scheduleTick(blockPos, this, 4);
                    } else {
                        level.setBlock(blockPos, blockState.cycle(LIT), 2);
                    }
                }

            }
        }

        super.neighborChanged(blockState, level, blockPos, neighborBlock, neighborBlockPos, b);
    }


    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT) && !serverLevel.hasNeighborSignal(blockPos)) {
            serverLevel.setBlock(blockPos, blockState.cycle(LIT), 2);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
