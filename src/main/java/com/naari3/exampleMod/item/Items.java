package com.naari3.exampleMod.item;


import com.naari3.exampleMod.ExampleMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(ExampleMod.MODID)
public class Items {
    @GameRegistry.ObjectHolder("ruby")
    public static Item ruby;
    @GameRegistry.ObjectHolder("ruby_sword")
    public static Item rubySword;
}
