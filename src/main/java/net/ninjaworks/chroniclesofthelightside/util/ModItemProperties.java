package net.ninjaworks.chroniclesofthelightside.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
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

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeBow(ModItems.FRISBY_DISC.get());
    }

    private static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
            if (p_174637_ == null) {
                return 0.0F;
            } else {
                return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float)(p_174635_.getUseDuration() -
                        p_174637_.getUseItemRemainingTicks()) / 0.1F;
            }
        });

        ItemProperties.register(item, new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
            class UseDisc extends Item {


                public UseDisc(Properties pProperties) {
                    super(pProperties);
                }

                @Override
                public InteractionResult useOn(UseOnContext pContext) {
                    if (!pContext.getLevel().isClientSide()) {
                        Level level = pContext.getLevel();
                        BlockPos positionClicked = pContext.getClickedPos();
                        Block blockClicked = level.getBlockState(positionClicked).getBlock();

                            pContext.getPlayer().sendMessage(new TextComponent("Executed command 'convertto (entity.arrow)'"),
                                    Util.NIL_UUID);
                        }
                    return InteractionResult.SUCCESS;
                }

            }

            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
        });
    }
}

