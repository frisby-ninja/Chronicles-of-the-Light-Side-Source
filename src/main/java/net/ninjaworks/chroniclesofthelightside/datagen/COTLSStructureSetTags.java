package net.ninjaworks.chroniclesofthelightside.datagen;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;
import net.ninjaworks.chroniclesofthelightside.setup.Registrations;

public class COTLSStructureSetTags extends TagsProvider<StructureSet> {

    public COTLSStructureSetTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.STRUCTURE_SETS, chroniclesofthelightside.MOD_ID, helper, TagManager.getTagDir(BuiltinRegistries.STRUCTURE_SETS.key()).substring(5));
    }

    @Override
    protected void addTags() {
        tag(Registrations.INTERNET_DIMENSION_STRUCTURE_SET)
                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(chroniclesofthelightside.MOD_ID, "portal")))
        ;
    }

    @Override
    public String getName() {
        return "COTLS Tags";
    }
}
