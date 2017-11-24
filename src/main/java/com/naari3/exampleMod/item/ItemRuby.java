package com.naari3.exampleMod.item;

import com.naari3.exampleMod.client.ModelRegisterCallback;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRuby extends Item implements ModelRegisterCallback {

    public ItemRuby() {
        setCreativeTab(CreativeTabs.MATERIALS);
        setUnlocalizedName("ruby");
//        setRegistryName("ruby");
        setMaxStackSize(64);
    }
}
