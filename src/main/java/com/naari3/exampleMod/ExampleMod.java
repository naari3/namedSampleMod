package com.naari3.exampleMod;

import com.naari3.exampleMod.entity.Entities;
import com.naari3.exampleMod.entity.EntityDeathBlock;
import com.naari3.exampleMod.proxy.CommonProxy;
import com.naari3.exampleMod.render.RenderDeathBlock;
import com.naari3.exampleMod.util.EntityNames;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.MOD_NAME, version = ExampleMod.MOD_VERSION)
public class ExampleMod {
    public static final @Nonnull
    String MODID = "namedexamplemod";
    public static final @Nonnull
    String DOMAIN = MODID;
    public static final @Nonnull
    String MOD_NAME = "Named Example Mod";
    public static final @Nonnull
    String MOD_VERSION = "0.0.1";

    // プロキシ（読み込み処理）のパッケージ階層
    public static final String CLIENT_PROXY = "com.naari3.exampleMod.proxy.ClientProxy";
    public static final String SERVER_PROXY = "com.naari3.exampleMod.proxy.ServerProxy";

    public static GuiHandler guiHandler = new GuiHandler();

    @Mod.Instance(MODID)
    public static ExampleMod instance;

    // サーバー、クライアントを識別しインスタンスを保持します
    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerCreatures();
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
    }

    /***
     * initの後に呼ばれます。つまりMod本体の中では最後に呼ばれます。<br>
     * リソースの開放や不要なデータの削除などお片付け的なことをします。
     *
     * @param event
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    private void registerCreatures() {
        int id = 0;
        Entities.registerEntity(EntityNames.DEATH_BLOCK, com.naari3.exampleMod.entity.EntityDeathBlock.class, id++, 80, 1, true);
    }
}
