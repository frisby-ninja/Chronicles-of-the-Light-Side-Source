package net.ninjaworks.chroniclesofthelightside.datagen;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;

public class COTLSStructureSetTags extends TagsProvider<StructureSet> {

    public COTLSStructureSetTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.STRUCTURE_SETS, chroniclesofthelightside.MOD_ID, helper, TagManager.getTagDir(BuiltinRegistries.STRUCTURE_SETS.key()).substring(5));
    }

    @Override
    protected void addTags() {

    }

    @Override
    public String getName() {
        return "COTLS Tags";
    }
}
