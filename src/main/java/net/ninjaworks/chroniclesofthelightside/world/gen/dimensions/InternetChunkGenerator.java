package net.ninjaworks.chroniclesofthelightside.world.gen.dimensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.ninjaworks.chroniclesofthelightside.block.ModBlocks;
import net.ninjaworks.chroniclesofthelightside.setup.Registrations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class InternetChunkGenerator extends ChunkGenerator {

    private static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("base").forGetter(Settings::baseHeight),
                    Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::verticalVariance),
                    Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::horizontalVariance)
            ).apply(instance, Settings::new));

    public static final Codec<InternetChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter(InternetChunkGenerator::getStructureSetRegistry),
                    RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter(InternetChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(InternetChunkGenerator::getCOTLSSettings)
            ).apply(instance, InternetChunkGenerator::new));

    private final Settings settings;

    public InternetChunkGenerator(Registry<StructureSet> structureSetRegistry, Registry<Biome> registry, Settings settings) {
        super(structureSetRegistry, getSet(structureSetRegistry), new InternetBiomeProvider(registry));
        this.settings = settings;
    }

    private static Optional<HolderSet<StructureSet>> getSet(Registry<StructureSet> structureSetRegistry) {
        HolderSet.Named<StructureSet> structureSet = structureSetRegistry.getOrCreateTag(TagKey.create(Registry.STRUCTURE_SET_REGISTRY,
                Registrations.RL_INTERNET_DIMENSION_SET));
        return Optional.of(structureSet);
    }

    public Settings getCOTLSSettings() {
        return settings;
    }

    public Registry<Biome> getBiomeRegistry() {
        return ((InternetBiomeProvider)biomeSource).getBiomeRegistry();
    }

    public Registry<StructureSet> getStructureSetRegistry() {
        return structureSets;
    }

    @Override
    public void buildSurface(WorldGenRegion region, StructureFeatureManager featureManager, ChunkAccess chunk) {
        BlockState air = Blocks.AIR.defaultBlockState();
        BlockState interblock = ModBlocks.INTERBLOCK.get().defaultBlockState();
        ChunkPos chunkpos = chunk.getPos();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int x;
        int z;

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                chunk.setBlockState(pos.set(x, 0, z), air, false);
            }
        }

        int baseHeight = settings.baseHeight();
        float verticalVariance = settings.verticalVariance();
        float horizontalVariance = settings.horizontalVariance();
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                int realx = chunkpos.x * 16 + x;
                int realz = chunkpos.z * 16 + z;
                int height = getHeightAt(baseHeight, verticalVariance, horizontalVariance, realx, realz);
                for (int y = 1 ; y < height ; y++) {
                    chunk.setBlockState(pos.set(x, y, z), air, false);
                }
            }
        }
    }

    private int getHeightAt(int baseHeight, float verticalVariance, float horizontalVariance, int x, int z) {
        return (int) (baseHeight + Math.sin(x / horizontalVariance) * verticalVariance + Math.cos(z / horizontalVariance) * verticalVariance);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new InternetChunkGenerator(getStructureSetRegistry(), getBiomeRegistry(), settings);
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager featureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.completedFuture(chunkAccess);
    }

    // Please implement correctly, used by structure and other feature spawners
    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor) {
        int baseHeight = settings.baseHeight();
        float verticalVariance = settings.verticalVariance();
        float horizontalVariance = settings.horizontalVariance();
        return getHeightAt(baseHeight, verticalVariance, horizontalVariance, x, z);
    }

    // Please implement correctly, used by structure and other feature spawners
    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor) {
        int y = getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor);
        BlockState stone = ModBlocks.INTERBLOCK.get().defaultBlockState();
        BlockState[] states = new BlockState[y];
        states[0] = Blocks.AIR.defaultBlockState();
        for (int i = 1 ; i < y ; i++) {
            states[i] = stone;
        }
        return new NoiseColumn(levelHeightAccessor.getMinBuildHeight(), states);
    }

    @Override
    public void applyCarvers(WorldGenRegion level, long seed, BiomeManager biomeManager,
                             StructureFeatureManager featureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {
    }

    @Override
    public Climate.Sampler climateSampler() {
        return new Climate.Sampler(DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), Collections.emptyList());
    }

    // Needed because of implementation mechanics, works out passive mod spawning, empty for now (ie no mobs)
    @Override
    public void spawnOriginalMobs(WorldGenRegion level) {

    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getGenDepth() {
        return 10;
    }

    @Override
    public int getSeaLevel() {
        return 63;
    }

    private record Settings(int baseHeight, float verticalVariance, float horizontalVariance) { }

    @Override
    public void addDebugScreenInfo(List<String> list, BlockPos pos) {
    }
}