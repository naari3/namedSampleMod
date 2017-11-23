package com.naari3.exampleMod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

@Mod(modid = ExampleMod.MOD_ID, name = ExampleMod.MOD_NAME, version = ExampleMod.MOD_VERSION)
public class ExampleMod {
    public static final @Nonnull String MOD_ID = "namedexamplemod";
    public static final @Nonnull String DOMAIN = MOD_ID;
    public static final @Nonnull String MOD_NAME = "Named Example Mod";
    public static final @Nonnull String MOD_VERSION = "0.0.1";

    public static GuiHandler guiHandler = new GuiHandler();

    @Mod.Instance(MOD_ID)
    public static ExampleMod instance;

    public static Item ruby;
    public static Item ruby_sword;
    public static Block ruby_block;
    public static ItemBlock ruby_block_item;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ruby = new Item()
                .setCreativeTab(CreativeTabs.MATERIALS)
                .setUnlocalizedName("ruby")
                .setRegistryName("ruby")
                .setMaxStackSize(64);

        ruby_sword = new AddSword(EnumHelper.addToolMaterial("ruby_sword", 1, 1000, 5, 7, 1))
                .setUnlocalizedName("ruby_sword")
                .setRegistryName("ruby_sword");

        ruby_block = new rubyBlock();
        ruby_block_item = new ItemBlock(ruby_block);
        ruby_block_item.setRegistryName(ruby_block.getRegistryName());

        ForgeRegistries.ITEMS.register(ruby);
        ForgeRegistries.ITEMS.register(ruby_sword);
        ForgeRegistries.ITEMS.register(ruby_block_item);
        ForgeRegistries.BLOCKS.register(ruby_block);

        if(event.getSide().isClient()) {
            ModelLoader.setCustomModelResourceLocation(ruby, 0, new ModelResourceLocation("namedexamplemod:ruby"));
            ModelLoader.setCustomModelResourceLocation(ruby_sword, 0, new ModelResourceLocation("namedexamplemod:ruby_sword"));
            ModelLoader.setCustomModelResourceLocation(ruby_block_item, 0, new ModelResourceLocation("namedexamplemod:ruby_block"));
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
    }
}
