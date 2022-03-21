package net.ninjaworks.chroniclesofthelightside.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ninjaworks.chroniclesofthelightside.block.custom.ModFlammableRotatedPillarBlock;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;
import net.ninjaworks.chroniclesofthelightside.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, chroniclesofthelightside.MOD_ID);

    public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();


    public static final RegistryObject<Block> TITANIUM_ORE_OVERWORLD = BLOCKS.register("titanium_ore_overworld", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = BLOCKS.register("titanium_ore_deepslate", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Block> TITANIUM_ORE_NETHER = BLOCKS.register("titanium_ore_nether", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Block> TITANIUM_ORE_END = BLOCKS.register("titanium_ore_end", () -> new Block(BLOCK_PROPERTIES));
    public static final RegistryObject<Block> BULLETPROOF_GLASS = BLOCKS.register("bulletproof_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));
    public static final RegistryObject<Block> REDWOOD_SAPLING = BLOCKS.register("redwood_sapling", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> REDWOOD_LOG = BLOCKS.register("redwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_LOG)));
    public static final RegistryObject<Block> REDWOOD_WOOD = BLOCKS.register("redwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG = BLOCKS.register("stripped_redwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STRIPPED_DARK_OAK_LOG)));public static final RegistryObject<Block> STRIPPED_REDWOOD_WOOD = BLOCKS.register("stripped_redwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> REDWOOD_PLANKS = BLOCKS.register("redwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> REDWOOD_LEAVES = BLOCKS.register("redwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }
            });

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {

        });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}