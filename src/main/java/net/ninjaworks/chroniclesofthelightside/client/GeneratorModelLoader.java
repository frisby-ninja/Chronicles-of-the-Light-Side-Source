package net.ninjaworks.chroniclesofthelightside.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Matrix4f;
import com.mojang.math.Transformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.QuadTransformer;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;
import net.ninjaworks.chroniclesofthelightside.varia.ClientTools;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

import static java.lang.Boolean.TRUE;
import static net.ninjaworks.chroniclesofthelightside.varia.ClientTools.v;

public class GeneratorModelLoader implements IModelLoader<GeneratorModelLoader.GeneratorModelGeometry> {

    public static final ResourceLocation GENERATOR_LOADER = new ResourceLocation(chroniclesofthelightside.MOD_ID, "generatorloader");

    public static final ResourceLocation GENERATOR_FRONT_POWERED = new ResourceLocation(chroniclesofthelightside.MOD_ID, "block/generator_front_powered");
    public static final ResourceLocation GENERATOR_FRONT = new ResourceLocation(chroniclesofthelightside.MOD_ID, "block/generator_front");
    public static final ResourceLocation GENERATOR_SIDE = new ResourceLocation(chroniclesofthelightside.MOD_ID, "block/generator_side");
    public static final ResourceLocation GENERATOR_ON = new ResourceLocation(chroniclesofthelightside.MOD_ID, "block/generator_on");
    public static final ResourceLocation GENERATOR_OFF = new ResourceLocation(chroniclesofthelightside.MOD_ID, "block/generator_off");

    public static final Material MATERIAL_FRONT_POWERED = ForgeHooksClient.getBlockMaterial(GENERATOR_FRONT_POWERED);
    public static final Material MATERIAL_FRONT = ForgeHooksClient.getBlockMaterial(GENERATOR_FRONT);
    public static final Material MATERIAL_SIDE = ForgeHooksClient.getBlockMaterial(GENERATOR_SIDE);
    public static final Material MATERIAL_ON = ForgeHooksClient.getBlockMaterial(GENERATOR_ON);
    public static final Material MATERIAL_OFF = ForgeHooksClient.getBlockMaterial(GENERATOR_OFF);

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
    }

    @Override
    public GeneratorModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new GeneratorModelGeometry();
    }


    public static class GeneratorModelGeometry implements IModelGeometry<GeneratorModelGeometry> {

        @Override
        public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
            return new GeneratorBakedModel(modelTransform, spriteGetter, overrides, owner.getCameraTransforms());
        }

        @Override
        public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
            return List.of(MATERIAL_FRONT, MATERIAL_FRONT_POWERED, MATERIAL_SIDE, MATERIAL_ON, MATERIAL_OFF);
        }
    }
}