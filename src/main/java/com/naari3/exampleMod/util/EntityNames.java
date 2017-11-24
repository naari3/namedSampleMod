package com.naari3.exampleMod.util;

import com.naari3.exampleMod.ExampleMod;
import net.minecraft.util.ResourceLocation;

public class EntityNames {
    public static final ResourceLocation DEATH_BLOCK = prefix("death_block");

    private static ResourceLocation prefix(String path) {
        return new ResourceLocation(ExampleMod.MODID, path);
    }
}
