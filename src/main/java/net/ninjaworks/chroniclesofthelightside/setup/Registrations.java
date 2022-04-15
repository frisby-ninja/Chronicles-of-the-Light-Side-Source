package net.ninjaworks.chroniclesofthelightside.setup;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ninjaworks.chroniclesofthelightside.world.gen.structures.PortalStructure;

import static net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside.MOD_ID;

public class Registrations {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);
    private static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MOD_ID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(bus);
        STRUCTURES.register(bus);
    }

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> PORTAL = STRUCTURES.register("portal", PortalStructure::new);
    public static final ResourceLocation RL_INTERNET_DIMENSION_SET = new ResourceLocation(MOD_ID, "internet_dimension_structure_set");

    public static final TagKey<StructureSet> INTERNET_DIMENSION_STRUCTURE_SET = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, RL_INTERNET_DIMENSION_SET);
    public static final TagKey<Biome> HAS_PORTAL = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MOD_ID, "has_structure/portal"));


}