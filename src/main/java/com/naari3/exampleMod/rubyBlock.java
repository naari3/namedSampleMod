package com.naari3.exampleMod;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class rubyBlock extends Block implements ITileEntityProvider {

    public rubyBlock() {
        super(Material.ROCK);

        setUnlocalizedName("ruby_block");
        setRegistryName("ruby_block");
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        ExampleMod.guiHandler.registerGuiHandler(GuiHandler.GUI_ID_RUBY, new IGuiHandler() {
            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                return null;
            }

            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                BlockPos pos = new BlockPos(x, y, z);
                TileEntity te = world.getTileEntity(pos);
                if (te instanceof RubyContainerTileEntity) {
                    RubyContainerTileEntity containerTileEntity = (RubyContainerTileEntity) te;
                    return new RubyGuiContainer(containerTileEntity, new RubyContainer(player.inventory, containerTileEntity));
                }
                return null;
            }
        });
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new RubyContainerTileEntity();
    }

    @Override
    public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        p.openGui(ExampleMod.instance, GuiHandler.GUI_ID_RUBY, w, pos.getX(), pos.getY(), pos.getZ());
        if (w.isRemote) {
            return true;
        }
        TileEntity te = w.getTileEntity(pos);
        if (!(te instanceof RubyContainerTileEntity)) {
            return false;
        }
        return true;
    }
}
