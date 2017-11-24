package com.naari3.exampleMod.item;

import com.naari3.exampleMod.client.ModelRegisterCallback;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRubySword extends ItemSword implements ModelRegisterCallback {
    public static ToolMaterial ruby_sword = EnumHelper.addToolMaterial("ruby_sword", 1, 1000, 5, 7, 1);

    public ItemRubySword() {
        super(ruby_sword);
        setUnlocalizedName("ruby_sword");
//        setRegistryName("ruby_sword");
    }
}
