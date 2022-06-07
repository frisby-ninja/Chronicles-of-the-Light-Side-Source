package net.ninjaworks.chroniclesofthelightside.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;
import net.ninjaworks.chroniclesofthelightside.world.gen.ores.ModOreGeneration;
import net.ninjaworks.chroniclesofthelightside.world.gen.ModTreeGeneration;

@Mod.EventBusSubscriber(modid = chroniclesofthelightside.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);

        ModTreeGeneration.generateTrees(event);
    }
}
