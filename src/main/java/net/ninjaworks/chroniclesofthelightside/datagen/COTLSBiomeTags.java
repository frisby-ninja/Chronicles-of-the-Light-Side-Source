package net.ninjaworks.chroniclesofthelightside.datagen;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;

public class COTLSBiomeTags extends TagsProvider<Biome> {

    public COTLSBiomeTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, chroniclesofthelightside.MOD_ID, helper, TagManager.getTagDir(BuiltinRegistries.BIOME.key()).substring(5));
    }

    @Override
    protected void addTags() {
        ForgeRegistries.BIOMES.getValues().forEach(biome -> {

        });
    }

    @Override
    public String getName() {
        return "COTLS Tags";
    }
}
