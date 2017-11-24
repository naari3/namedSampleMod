package com.naari3.exampleMod.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityDeathBlock extends Entity {
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.<BlockPos>createKey(EntityFallingBlock.class, DataSerializers.BLOCK_POS);
    public NBTTagCompound tileEntityData;
    private IBlockState deathTile;


    public EntityDeathBlock(World worldIn) {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.entityCollisionReduction = 1F;
        this.setSize(0.98F, 0.98F);
    }

    public EntityDeathBlock(World worldIn, double x, double y, double z, IBlockState deathBlockState) {
        super(worldIn);
        this.deathTile = deathBlockState;
        this.preventEntitySpawning = true;
        this.entityCollisionReduction = 1F;
        this.setSize(0.98F, 0.98F);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;

        this.setOrigin(new BlockPos(this));
    }

    @SideOnly(Side.CLIENT)
    public BlockPos getOrigin() {
        return (BlockPos) this.dataManager.get(ORIGIN);
    }

    public void setOrigin(BlockPos p_184530_1_) {
        this.dataManager.set(ORIGIN, p_184530_1_);
    }

    protected void entityInit() {
        this.dataManager.register(ORIGIN, BlockPos.ORIGIN);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public void onUpdate() {
        if (this.deathTile == null || this.deathTile.getMaterial() == Material.AIR) {
            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound compound) {
        Block block = this.deathTile != null ? this.deathTile.getBlock() : Blocks.AIR;
        ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(block);
        compound.setString("Block", resourcelocation == null ? "" : resourcelocation.toString());
        compound.setByte("Data", (byte) block.getMetaFromState(this.deathTile));

        if (this.tileEntityData != null) {
            compound.setTag("TileEntityData", this.tileEntityData);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound compound) {
        int i = compound.getByte("Data") & 255;

        if (compound.hasKey("Block", 8)) {
            this.deathTile = Block.getBlockFromName(compound.getString("Block")).getStateFromMeta(i);
        } else if (compound.hasKey("TileID", 99)) {
            this.deathTile = Block.getBlockById(compound.getInteger("TileID")).getStateFromMeta(i);
        } else {
            this.deathTile = Block.getBlockById(compound.getByte("Tile") & 255).getStateFromMeta(i);
        }

        Block block = this.deathTile.getBlock();

        if (compound.hasKey("TileEntityData", 10)) {
            this.tileEntityData = compound.getCompoundTag("TileEntityData");
        }

        if (block == null || block.getDefaultState().getMaterial() == Material.AIR) {
            this.deathTile = Blocks.SAND.getDefaultState();
        }
    }

    @SideOnly(Side.CLIENT)
    public World getWorldObj() {
        return this.world;
    }

    @Nullable
    public IBlockState getBlock() {
        return this.deathTile;
    }
}
