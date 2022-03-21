package net.ninjaworks.chroniclesofthelightside.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {
    public static final ForgeTier TITANIUM = new ForgeTier(5, 1500, 5f,
            4f, 25, Tags.Blocks.NEEDS_GOLD_TOOL,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));
}
