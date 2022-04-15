package net.ninjaworks.chroniclesofthelightside.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.ninjaworks.chroniclesofthelightside.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> REDWOOD_PLACED = PlacementUtils.register("redwood_placed",
            ModConfiguredFeatures.REDWOOD_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2)));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TITANIUM_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE_OVERWORLD.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.TITANIUM_ORE_DEEPSLATE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TITANIUM_ORE_PLACED = FeatureUtils.register("chroniclesofthelightside:titanium_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TITANIUM_ORES, 9));
    }
