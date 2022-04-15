package net.ninjaworks.chroniclesofthelightside.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;

public class ModSounds {
        public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
                DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, chroniclesofthelightside.MOD_ID);

        public static RegistryObject<SoundEvent> TITANIUM_BLOCK_BREAK = registerSoundEvent("titanium_block_break");
        public static RegistryObject<SoundEvent> TITANIUM_BLOCK_STEP = registerSoundEvent("titanium_block_step");
        public static RegistryObject<SoundEvent> TITANIUM_BLOCK_PLACE = registerSoundEvent("titanium_block_place");
        public static RegistryObject<SoundEvent> TITANIUM_BLOCK_HIT = registerSoundEvent("titanium_block_hit");
        public static RegistryObject<SoundEvent> TITANIUM_BLOCK_FALL = registerSoundEvent("titanium_block_fall");


        public static final ForgeSoundType TITANIUM_BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
                ModSounds.TITANIUM_BLOCK_BREAK, ModSounds.TITANIUM_BLOCK_STEP, ModSounds.TITANIUM_BLOCK_PLACE,
                ModSounds.TITANIUM_BLOCK_HIT, ModSounds.TITANIUM_BLOCK_FALL);


        private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
            return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(chroniclesofthelightside.MOD_ID, name)));
        }

        public static void register(IEventBus eventBus) {
            SOUND_EVENTS.register(eventBus);
        }
    }
