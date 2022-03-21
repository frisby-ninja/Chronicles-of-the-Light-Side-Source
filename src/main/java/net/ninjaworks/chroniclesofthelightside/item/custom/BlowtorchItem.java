package net.ninjaworks.chroniclesofthelightside.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.ninjaworks.chroniclesofthelightside.block.ModBlocks;
import net.ninjaworks.chroniclesofthelightside.item.ModItems;

import java.util.Map;

public class BlowtorchItem extends Item {
    private static final Map<Block, Item> BLOW_TORCH_ITEM_CRAFT =
            new ImmutableMap.Builder<Block, Item>()
                    .put(ModBlocks.TITANIUM_ORE_OVERWORLD.get(), ModItems.TITANIUM_NUGGET.get())
                    .put(Blocks.SAND, Blocks.GLASS.asItem())
                    .put(Blocks.IRON_ORE, Items.IRON_NUGGET.asItem())
                    .put(Blocks.GOLD_ORE, Items.GOLD_NUGGET.asItem())
                    .put(Blocks.COAL_ORE, Items.COAL.asItem())
                    .put(Blocks.DIAMOND_ORE, Items.DIAMOND.asItem())
                    .put(Blocks.COPPER_ORE, Items.COPPER_INGOT.asItem())
                    .put(Blocks.ACACIA_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.STRIPPED_ACACIA_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.OAK_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.STRIPPED_OAK_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.BIRCH_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.STRIPPED_BIRCH_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.JUNGLE_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.STRIPPED_JUNGLE_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.STRIPPED_DARK_OAK_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.DARK_OAK_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.SPRUCE_LOG, Items.CHARCOAL.asItem())
                    .put(Blocks.STRIPPED_SPRUCE_LOG, Items.CHARCOAL.asItem())
                            .build();

    public BlowtorchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();

            if(canBlowTorch(blockClicked)) {
                ItemEntity entityItem = new ItemEntity(level,
                        positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                        new ItemStack(BLOW_TORCH_ITEM_CRAFT.get(blockClicked), 1));

                level.destroyBlock(positionClicked, false);
                level.addFreshEntity(entityItem);
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), p -> {
                    p.broadcastBreakEvent(pContext.getHand());
                });
            } else {
                pContext.getPlayer().sendMessage(new TextComponent("Cannot blowtorch this block!"),
                        Util.NIL_UUID);
            }

        }
        return InteractionResult.SUCCESS;
    }

    private boolean canBlowTorch(Block block) {
        return BLOW_TORCH_ITEM_CRAFT.containsKey(block);
    }
}
