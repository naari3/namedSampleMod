package com.naari3.exampleMod.proxy;

import com.naari3.exampleMod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ExampleMod.logger.info("CommonProxy.preInit");
    }

    public void init(FMLInitializationEvent event) {
        ExampleMod.logger.info("CommonProxy.init");
    }

    public void postInit(FMLPostInitializationEvent event) {
        ExampleMod.logger.info("CommonProxy.postInit");
    }

    /***
     * Modで追加したいアイテムを読み込みます。<br>
     * 勿論アイテムのクラスは自分で定義する必要があります。
     *
     * @param event
     */
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ExampleMod.logger.info("CommonProxy.registerItems");
    }
}
