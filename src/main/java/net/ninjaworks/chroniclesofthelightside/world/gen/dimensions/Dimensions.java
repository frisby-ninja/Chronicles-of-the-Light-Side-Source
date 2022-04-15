package net.ninjaworks.chroniclesofthelightside.world.gen.dimensions;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;

public class Dimensions {

    public static final ResourceKey<Level> INTERNET = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(chroniclesofthelightside.MOD_ID, "internet"));

    public static void register() {
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(chroniclesofthelightside.MOD_ID, "titanium_chunkgen"),
                InternetChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(chroniclesofthelightside.MOD_ID, "biomes"),
                InternetBiomeProvider.CODEC);
    }
}
