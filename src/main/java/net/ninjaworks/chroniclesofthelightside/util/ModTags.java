package net.ninjaworks.chroniclesofthelightside.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;

public class ModTags {
    public static class Blocks {

        public static class Items {
            public static final TagKey<Item> TITANIUM_INGOT = forgeTag("ingots/titanium");

            private static TagKey<Item> tag(String name) {
                return ItemTags.create(new ResourceLocation(chroniclesofthelightside.MOD_ID, name));
            }

            private static TagKey<Item> forgeTag(String name) {
                return ItemTags.create(new ResourceLocation("forge", name));
            }
        }
    }
}
