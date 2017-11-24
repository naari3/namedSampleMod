package com.naari3.exampleMod.render;

import com.naari3.exampleMod.entity.EntityDeathBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RenderDeathBlock extends Render<EntityDeathBlock> {
    public RenderDeathBlock(RenderManager renderManager) {
        super(renderManager);
        this.shadowSize = 0.0F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityDeathBlock entity, double x, double y, double z, float yaw, float partialTicks)
    {
        if (entity.getBlock() != null)
        {
            IBlockState iblockstate = entity.getBlock();
            iblockstate = Blocks.SAND.getDefaultState();
            System.out.println(iblockstate);

            if (iblockstate.getRenderType() == EnumBlockRenderType.MODEL) {
                World world = entity.world;

                if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
                    this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder vertexbuffer = tessellator.getBuffer();

                    if (this.renderOutlines) {
                        GlStateManager.enableColorMaterial();
                        GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                    }

                    vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
                    BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);
                    GlStateManager.translate((float) (x - (double) blockpos.getX() - 0.5D), (float) (y - (double) blockpos.getY()), (float) (z - (double) blockpos.getZ() - 0.5D));
					/*// spin FIXME
					if (iblockstate.getValue(BlockRotatedPillar.AXIS) == EnumFacing.Axis.Y) {
						GlStateManager.rotate((entity.ticksExisted + partialTicks) * 60F, 0, 1, 0);
					} else if (iblockstate.getValue(BlockRotatedPillar.AXIS) == EnumFacing.Axis.X) {
						GlStateManager.rotate((entity.ticksExisted + partialTicks) * 60F, 1, 0, 0);
					} else if (iblockstate.getValue(BlockRotatedPillar.AXIS) == EnumFacing.Axis.Z) {
						GlStateManager.rotate((entity.ticksExisted + partialTicks) * 60F, 0, 0, 1);
					}*/
                    BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
                    blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, vertexbuffer, false, 0);
                    tessellator.draw();

                    if (this.renderOutlines) {
                        GlStateManager.disableOutlineMode();
                        GlStateManager.disableColorMaterial();
                    }

                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                    super.doRender(entity, x, y, z, yaw, partialTicks);
                }
            }
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityDeathBlock p_110775_1_) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
