package com.naari3.exampleMod;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_ID_RUBY = 1;

    protected final Map<Integer, IGuiHandler> guiHandlers = new HashMap<Integer, IGuiHandler>();

    public void registerGuiHandler(int id, IGuiHandler handler) {
        guiHandlers.put(id, handler);
        System.out.println("ID: " + id);
        System.out.println("Handler: " + handler);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        IGuiHandler handler = guiHandlers.get(id);
        if(handler != null) {
            return handler.getServerGuiElement(id, player, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        IGuiHandler handler = guiHandlers.get(id);
        System.out.println("CalledID: " + id);
        if(handler != null) {
            return handler.getClientGuiElement(id, player, world, x, y, z);
        }
        return null;
    }
}
