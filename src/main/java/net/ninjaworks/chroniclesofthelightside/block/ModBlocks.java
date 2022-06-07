package net.ninjaworks.chroniclesofthelightside.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ninjaworks.chroniclesofthelightside.block.custom.ModFlammableRotatedPillarBlock;
import net.ninjaworks.chroniclesofthelightside.item.ModCreativeModeTab;
import net.ninjaworks.chroniclesofthelightside.item.ModItems;
import net.ninjaworks.chroniclesofthelightside.sound.ModSounds;
import net.ninjaworks.chroniclesofthelightside.world.feature.tree.RedwoodTreeGrower;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

import static net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside.MOD_ID;
 
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);


    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MOD_ID);


    private static final DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);

    }

    public static final BlockBehaviour.Properties BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();



    public static final TagKey<Block> TITANIUM_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MOD_ID, "titanium_ore"));

    public static final RegistryObject<Block> TITANIUM_ORE_OVERWORLD = registerBlock("titanium_ore_overworld", () -> new OreBlock(BLOCK_PROPERTIES, UniformInt.of(3, 7)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> TITANIUM_ORE_NETHER = registerBlock("titanium_ore_nether", () -> new OreBlock(BLOCK_PROPERTIES, UniformInt.of(4, 7)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> TITANIUM_ORE_END = registerBlock("titanium_ore_end", () -> new OreBlock(BLOCK_PROPERTIES, UniformInt.of(5, 7)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BLOCK_PROPERTIES.sound(SoundType.ANVIL).strength(5f,100f)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> NENDER_BLOCK = registerBlock("nender_block", () -> new Block(BLOCK_PROPERTIES.strength(4f,100f).sound(SoundType.STONE)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> BULLETPROOF_GLASS = registerBlock("bulletproof_glass",
            () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(4f, 100f).noOcclusion()), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate", () -> new Block(BLOCK_PROPERTIES), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> REDWOOD_LOG = registerBlock("redwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_LOG)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> REDWOOD_WOOD = registerBlock("redwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.OAK_WOOD)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG = registerBlock("stripped_redwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STRIPPED_DARK_OAK_LOG)), ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> STRIPPED_REDWOOD_WOOD = registerBlock("stripped_redwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STRIPPED_OAK_WOOD)), ModCreativeModeTab.NINJA_GEAR);

            public static final RegistryObject<Block> REDWOOD_PLANKS = registerBlock("redwood_planks",
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
            }, ModCreativeModeTab.NINJA_GEAR);

    public static final RegistryObject<Block> REDWOOD_LEAVES = registerBlock("redwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
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
            }, ModCreativeModeTab.NINJA_GEAR);


    public static final RegistryObject<Block> REDWOOD_SAPLING = registerBlock("redwood_sapling",
            () -> new SaplingBlock(new RedwoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), ModCreativeModeTab.NINJA_GEAR);


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
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(new TranslatableComponent(tooltipKey));
            }
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
