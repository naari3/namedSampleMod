package com.naari3.exampleMod.entity;

import com.naari3.exampleMod.ExampleMod;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Entities {
    public static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, int id, int backgroundEggColour, int foregroundEggColour) {
        registerEntity(registryName, entityClass, id, backgroundEggColour, foregroundEggColour, 80, 3, true);
    }

    public static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, int id, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(registryName, entityClass, registryName.getResourceDomain() + "." + registryName.getResourcePath(), id, ExampleMod.instance, trackingRange, updateFrequency, sendsVelocityUpdates, backgroundEggColour, foregroundEggColour);
    }

    public static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, int id) {
        EntityRegistry.registerModEntity(registryName, entityClass, registryName.getResourceDomain() + "." + registryName.getResourcePath(), id, ExampleMod.instance, 80, 3, true);
    }

    public static void registerEntity(ResourceLocation registryName, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        EntityRegistry.registerModEntity(registryName, clazz, registryName.getResourceDomain() + "." + registryName.getResourcePath(), id, ExampleMod.instance, trackingRange, updateFrequency, sendVelocityUpdates);
    }
}
