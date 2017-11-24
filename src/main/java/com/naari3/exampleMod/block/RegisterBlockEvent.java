package com.naari3.exampleMod.block;

import com.naari3.exampleMod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegisterBlockEvent {

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        BlockRegistryHelper blocks = new BlockRegistryHelper(event.getRegistry());

        blocks.register("ruby_block", new BlockRuby());
    }

    private static class BlockRegistryHelper {
        private final IForgeRegistry<Block> registry;

        BlockRegistryHelper(IForgeRegistry<Block> registry) {
            this.registry = registry;
        }

        private void register(String registryName, Block block) {
            block.setRegistryName(ExampleMod.MODID, registryName);
            block.setUnlocalizedName(registryName);
            registry.register(block);
            registry.getEntries();
        }
    }
}
