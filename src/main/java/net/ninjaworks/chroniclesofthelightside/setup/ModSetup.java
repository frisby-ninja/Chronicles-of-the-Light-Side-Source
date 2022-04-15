package net.ninjaworks.chroniclesofthelightside.setup;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;
import net.ninjaworks.chroniclesofthelightside.manasystem.data.ManaEvents;
import net.ninjaworks.chroniclesofthelightside.world.gen.dimensions.Dimensions;
import net.ninjaworks.chroniclesofthelightside.world.gen.ores.Ores;

@Mod.EventBusSubscriber(modid = chroniclesofthelightside.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(Ores::onBiomeLoadingEvent);
        bus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
        bus.addListener(ManaEvents::onPlayerCloned);
        bus.addListener(ManaEvents::onRegisterCapabilities);
        bus.addListener(ManaEvents::onWorldTick);
    }

    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ores.registerConfiguredFeatures();
            Dimensions.register();
        });
        Messages.register();
    }

}