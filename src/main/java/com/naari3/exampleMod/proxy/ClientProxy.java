package com.naari3.exampleMod.proxy;

import com.naari3.exampleMod.ExampleMod;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ExampleMod.logger.info("ClientProxy.preInit");
    }

    /***
     * ブロックやアイテムのモデル（テクスチャは何を使うとかテクスチャの向きとか定義したファイル）を読み込みます。<br>
     *
     * @param event
     */
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ExampleMod.logger.info("ClientProxy.registerModels");
    }
}
