package com.naari3.exampleMod.block;


import com.naari3.exampleMod.ExampleMod;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(ExampleMod.MODID)
public class Blocks {
    @GameRegistry.ObjectHolder("ruby_block")
    public static Block rubyBlock;
}
