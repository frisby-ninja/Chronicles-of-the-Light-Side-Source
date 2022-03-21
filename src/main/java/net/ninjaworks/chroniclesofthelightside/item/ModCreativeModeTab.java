package net.ninjaworks.chroniclesofthelightside.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.ninjaworks.chroniclesofthelightside.item.ModItems;

public class ModCreativeModeTab {
    public static final CreativeModeTab NINJA_GEAR = new CreativeModeTab("ninjaModTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BLOWCHIP.get());
        }
    };
}
