package net.ninjaworks.chroniclesofthelightside.datagen;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;
import net.ninjaworks.chroniclesofthelightside.setup.Registrations;

@Mod.EventBusSubscriber(modid = chroniclesofthelightside.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new COTLSBiomeTags(generator, event.getExistingFileHelper()));
            generator.addProvider(new COTLSStructureSetTags(generator, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {

        }
    }
}
