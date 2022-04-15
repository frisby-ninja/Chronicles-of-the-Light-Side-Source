package net.ninjaworks.chroniclesofthelightside.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TargetBlock;
import net.ninjaworks.chroniclesofthelightside.block.ModBlocks;
import net.ninjaworks.chroniclesofthelightside.item.ModItems;

import java.util.Map;

public class AtomiserItem extends Item {
    private static final Map<Block, Item> ATOMISER_ITEM_CRAFT =
            new ImmutableMap.Builder<Block, Item>()
                    .put(ModBlocks.TITANIUM_ORE_OVERWORLD.get(), ModItems.ATOM.get())
                            .build();

    public AtomiserItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();

            if(canAtomise(blockClicked)) {
                ItemEntity entityItem = new ItemEntity(level,
                        positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                        new ItemStack(ATOMISER_ITEM_CRAFT.get(blockClicked), 1));

                level.destroyBlock(positionClicked, false);
                level.addFreshEntity(entityItem);
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), p -> {
                    p.broadcastBreakEvent(pContext.getHand());
                });
            } else {
                pContext.getPlayer().sendMessage(new TextComponent("Cannot atomise this!"),
                        Util.NIL_UUID);
            }

        }
        return InteractionResult.SUCCESS;
    }

    private boolean canAtomise(Block block) {
        return ATOMISER_ITEM_CRAFT.containsKey(block);
    }
}
