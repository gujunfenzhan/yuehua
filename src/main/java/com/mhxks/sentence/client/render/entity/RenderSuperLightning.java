package com.mhxks.sentence.client.render.entity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderSuperLightning
extends Render {
    public static double[][] postion= new double[][]{{0.600000,0.775880,-0.600000
    },{0.600000,0.600000,-0.775880
    },{0.775880,0.600000,-0.600000
    },{0.600000,0.200000,-0.960788
    },{0.960788,0.200000,-0.600000
    },{0.600000,-0.200000,-0.960788
    },{0.960788,-0.200000,-0.600000
    },{1.000000,-0.200000,-0.530196
    },{1.000000,0.200000,-0.530196
    },{1.115109,-0.200000,-0.200000
    },{1.115109,0.200000,-0.200000
    },{1.000000,-0.530196,-0.200000
    },{1.000000,-0.530196,0.200000
    },{1.115109,-0.200000,0.200000
    },{1.000000,-0.200000,0.530196
    },{1.000000,0.200000,0.530196
    },{1.115109,0.200000,0.200000
    },{1.000000,0.530196,0.200000
    },{1.000000,0.530196,-0.200000
    },{0.960788,0.600000,0.200000
    },{0.960788,0.600000,-0.200000
    },{0.600000,0.960788,0.200000
    },{0.600000,0.960788,-0.200000
    },{0.530196,1.000000,0.200000
    },{0.530196,1.000000,-0.200000
    },{0.200000,1.115109,0.200000
    },{0.200000,1.115109,-0.200000
    },{0.200000,1.000000,0.530196
    },{-0.200000,1.115109,0.200000
    },{-0.200000,1.000000,0.530196
    },{-0.200000,0.960788,0.600000
    },{0.200000,0.960788,0.600000
    },{0.200000,0.600000,0.960788
    },{-0.200000,0.600000,0.960788
    },{-0.200000,0.530196,1.000000
    },{0.200000,0.530196,1.000000
    },{0.200000,0.200000,1.115109
    },{-0.200000,0.200000,1.115109
    },{-0.200000,-0.200000,1.115109
    },{0.200000,-0.200000,1.115109
    },{-0.200000,-0.530196,1.000000
    },{0.200000,-0.530196,1.000000
    },{0.200000,-0.600000,0.960788
    },{-0.200000,-0.600000,0.960788
    },{-0.200000,-0.960788,0.600000
    },{0.200000,-0.960788,0.600000
    },{0.200000,-1.000000,0.530196
    },{-0.200000,-1.000000,0.530196
    },{-0.200000,-1.115109,0.200000
    },{0.200000,-1.115109,0.200000
    },{0.200000,-1.115109,-0.200000
    },{-0.200000,-1.115109,-0.200000
    },{0.200000,-1.000000,-0.530196
    },{-0.200000,-1.000000,-0.530196
    },{-0.200000,-0.960788,-0.600000
    },{0.200000,-0.960788,-0.600000
    },{0.200000,-0.600000,-0.960788
    },{-0.200000,-0.600000,-0.960788
    },{-0.200000,-0.530196,-1.000000
    },{0.200000,-0.530196,-1.000000
    },{0.200000,-0.200000,-1.115109
    },{-0.200000,-0.200000,-1.115109
    },{0.200000,0.200000,-1.115109
    },{-0.200000,0.200000,-1.115109
    },{-0.200000,0.530196,-1.000000
    },{0.200000,0.530196,-1.000000
    },{0.200000,0.600000,-0.960788
    },{-0.200000,0.600000,-0.960788
    },{-0.200000,0.960788,-0.600000
    },{0.200000,0.960788,-0.600000
    },{0.200000,1.000000,-0.530196
    },{-0.200000,1.000000,-0.530196
    },{-0.200000,1.115109,-0.200000
    },{-0.530196,1.000000,-0.200000
    },{-0.530196,1.000000,0.200000
    },{-0.600000,0.960788,0.200000
    },{-0.600000,0.960788,-0.200000
    },{-0.960788,0.600000,0.200000
    },{-0.960788,0.600000,-0.200000
    },{-1.000000,0.530196,0.200000
    },{-1.000000,0.530196,-0.200000
    },{-1.000000,0.200000,-0.530196
    },{-0.960788,0.200000,-0.600000
    },{-0.775880,0.600000,-0.600000
    },{-1.000000,-0.200000,-0.530196
    },{-0.960788,-0.200000,-0.600000
    },{-1.000000,-0.530196,-0.200000
    },{-0.960788,-0.600000,-0.200000
    },{-0.775880,-0.600000,-0.600000
    },{-0.600000,-0.600000,-0.775880
    },{-0.600000,-0.200000,-0.960788
    },{-0.600000,-0.775880,-0.600000
    },{-1.000000,-0.530196,0.200000
    },{-0.960788,-0.600000,0.200000
    },{-0.600000,-0.960788,0.200000
    },{-0.600000,-0.960788,-0.200000
    },{-0.600000,-0.775880,0.600000
    },{-0.775880,-0.600000,0.600000
    },{-0.530196,-1.000000,0.200000
    },{-0.530196,-1.000000,-0.200000
    },{-1.000000,-0.200000,0.530196
    },{-0.960788,-0.200000,0.600000
    },{-1.000000,0.200000,0.530196
    },{-0.960788,0.200000,0.600000
    },{-0.600000,-0.200000,0.960788
    },{-0.600000,0.200000,0.960788
    },{-0.530196,0.200000,1.000000
    },{-0.530196,-0.200000,1.000000
    },{-1.115109,-0.200000,0.200000
    },{-1.115109,0.200000,0.200000
    },{-1.115109,-0.200000,-0.200000
    },{-1.115109,0.200000,-0.200000
    },{-0.600000,-0.600000,0.775880
    },{-0.600000,0.200000,-0.960788
    },{-0.530196,-0.200000,-1.000000
    },{-0.530196,0.200000,-1.000000
    },{-0.600000,0.600000,-0.775880
    },{-0.600000,0.775880,-0.600000
    },{-0.775880,0.600000,0.600000
    },{-0.600000,0.775880,0.600000
    },{-0.600000,0.600000,0.775880
    },{0.530196,-0.200000,-1.000000
    },{0.530196,0.200000,-1.000000
    },{0.600000,-0.775880,-0.600000
    },{0.600000,-0.600000,-0.775880
    },{0.775880,-0.600000,-0.600000
    },{0.600000,-0.960788,-0.200000
    },{0.960788,-0.600000,-0.200000
    },{0.600000,-0.960788,0.200000
    },{0.960788,-0.600000,0.200000
    },{0.600000,-0.775880,0.600000
    },{0.775880,-0.600000,0.600000
    },{0.600000,-0.600000,0.775880
    },{0.600000,-0.200000,0.960788
    },{0.960788,-0.200000,0.600000
    },{0.600000,0.200000,0.960788
    },{0.960788,0.200000,0.600000
    },{0.600000,0.600000,0.775880
    },{0.775880,0.600000,0.600000
    },{0.600000,0.775880,0.600000
    },{0.530196,-0.200000,1.000000
    },{0.530196,0.200000,1.000000
    },{0.530196,-1.000000,-0.200000
    },{0.530196,-1.000000,0.200000}};
    public RenderSuperLightning(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
       GlStateManager.disableCull();
        GlStateManager.enableBlend();
        Random random = new Random();
        List<Vector3d> po = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            if(i%2==1&&i>2&&random.nextInt(5)<4){
                Vector3d vector3d = new Vector3d();
                Vector3d p = po.get(i-1);
                vector3d.x = p.x;
                vector3d.y = p.y;
                vector3d.z = p.z;
                po.add(vector3d);
                continue;
            }
            double[] d = postion[random.nextInt(postion.length)];
            Vector3d vector3d = new Vector3d();
            vector3d.x = d[0];
            vector3d.y = d[1];
            vector3d.z = d[2];
            po.add(vector3d);
        }
        for (Vector3d vector3d : po) {
            if(random.nextInt(5)<2){
                Vector3d v = po.get(random.nextInt(po.size()));
                vector3d.x = v.x;
                vector3d.y = v.y;
                vector3d.z = v.z;
            }
        }

        double radius = 0.03f;
        float alpha = 0.5f;
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        //GlStateManager.translate(0,1.0,0);
        double height = 1.0F;
        for (int i = 0; i < po.size(); i = i+2) {
            Vector3d pos1 = po.get(i);
            Vector3d pos2 = po.get(i+1);
            bufferBuilder.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
            bufferBuilder.pos(pos1.x+x+radius,pos1.y+y+height,pos1.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos1.x+x-radius,pos1.y+y+height,pos1.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos2.x+x+radius,pos2.y+y+height,pos2.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos2.x+x-radius,pos2.y+y+height,pos2.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            tessellator.draw();
            bufferBuilder.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
            bufferBuilder.pos(pos1.x+x,pos1.y+y+radius+height,pos1.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos1.x+x,pos1.y+y-radius+height,pos1.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos2.x+x,pos2.y+y+radius+height,pos2.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos2.x+x,pos2.y+y-radius+height,pos2.z+z).color(0.0f,0.0f,0.8f,alpha).endVertex();
            tessellator.draw();
            bufferBuilder.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
            bufferBuilder.pos(pos1.x+x,pos1.y+y+height,pos1.z+z+radius).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos1.x+x,pos1.y+y+height,pos1.z+z-radius).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos2.x+x,pos2.y+y+height,pos2.z+z+radius).color(0.0f,0.0f,0.8f,alpha).endVertex();
            bufferBuilder.pos(pos2.x+x,pos2.y+y+height,pos2.z+z-radius).color(0.0f,0.0f,0.8f,alpha).endVertex();
            tessellator.draw();

        }

        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
    }
}
