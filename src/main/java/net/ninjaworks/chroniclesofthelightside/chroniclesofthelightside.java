package net.ninjaworks.chroniclesofthelightside;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.ninjaworks.chroniclesofthelightside.block.ModBlocks;
import net.ninjaworks.chroniclesofthelightside.item.ModItems;
import net.ninjaworks.chroniclesofthelightside.util.ModItemProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(chroniclesofthelightside.MOD_ID)
public class chroniclesofthelightside {
    public static final String MOD_ID = "chroniclesofthelightside";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    // Add a comment
    public chroniclesofthelightside() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BULLETPROOF_GLASS.get(), RenderType.translucent());

        ModItemProperties.addCustomItemProperties();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }
}