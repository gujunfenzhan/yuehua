package com.mhxks.sentence.client.render.entity;

import com.mhxks.sentence.entity.EntityFunnyDrive;
import com.mhxks.sentence.specialattack.FunnyDrive;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFunnyDrive
extends Render {
    private static double[][] dVec = {{     0,     1,  -0.5},  // 頂点0
            {     0,  0.75,     0},  // 頂点1
            {   0.1,   0.6, -0.15},  // 頂点2
            {     0,   0.5, -0.25},  // 頂点3
            {  -0.1,   0.6, -0.15},  // 頂点4
            {     0,     0,  0.25},  // 頂点5
            {  0.25,     0,     0},  // 頂点6
            {     0,     0, -0.25},  // 頂点7
            { -0.25,     0,     0},  // 頂点8
            {     0, -0.75,     0},  // 頂点9
            {   0.1,  -0.6, -0.15},  // 頂点10
            {     0,  -0.5, -0.25},  // 頂点11
            {  -0.1,  -0.6, -0.15},  // 頂点12
            {     0,    -1,  -0.5}}; // 頂点13

    private static int[][] nVecPos = {{ 0,  1,  2,  3},  //面1(頂点 0, 1, 2, 3)
            { 0,  3,  4,  1},  //面2
            { 1,  5,  6,  2},  //面3
            { 3,  2,  6,  7},  //面4
            { 3,  7,  8,  4},  //面5
            { 1,  4,  8,  5},  //面6
            { 6,  5,  9, 10},  //面7
            { 6, 10, 11,  7},  //面8
            { 8,  7, 11, 12},  //面9
            { 8, 12,  9,  5},  //面10
            {10,  9, 13, 11},  //面11
            {12, 11, 13,  9}}; //面12

    public RenderFunnyDrive(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
    {
        if (entity instanceof EntityFunnyDrive)
        {
            doDriveRender((EntityFunnyDrive) entity, d0, d1, d2, f, f1);
        }
    }

    @Override
    protected ResourceLocationRaw getEntityTexture(Entity var1) {
        return null;
    }

    private void doDriveRender(EntityFunnyDrive entityDrive, double dX, double dY, double dZ, float f, float f1)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        //GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glPushMatrix();

        GL11.glTranslatef((float)dX, (float)dY+0.5f, (float)dZ);
        GL11.glRotatef(entityDrive.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-entityDrive.rotationPitch, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(entityDrive.getRoll(),0,0,1);
        //GL11.glRotatef(fRot, 0.0F, 1.0F, 0.0F);

        GL11.glScalef(0.25f, 1, 1);

        //■スタート
        float lifetime = entityDrive.getLifeTime();
        float ticks = entityDrive.ticksExisted;
        BufferBuilder wr = tessellator.getBuffer();
        wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        float alpha = (float)Math.pow((lifetime - Math.min(lifetime,ticks)) / lifetime,2.0f);
        //◆頂点登録 開始
        double dScale = 1.0;
        for(int idx = 0; idx < nVecPos.length; idx++)
        {
            wr.pos(dVec[nVecPos[idx][0]][0] * dScale, dVec[nVecPos[idx][0]][1] * dScale, dVec[nVecPos[idx][0]][2] * dScale).color(1.0f,0.2f,0.2f,alpha).endVertex();
            wr.pos(dVec[nVecPos[idx][1]][0] * dScale, dVec[nVecPos[idx][1]][1] * dScale, dVec[nVecPos[idx][1]][2] * dScale).color(1.0f,0.2f,0.2f,alpha).endVertex();
            wr.pos(dVec[nVecPos[idx][2]][0] * dScale, dVec[nVecPos[idx][2]][1] * dScale, dVec[nVecPos[idx][2]][2] * dScale).color(1.0f,0.2f,0.2f,alpha).endVertex();
            wr.pos(dVec[nVecPos[idx][3]][0] * dScale, dVec[nVecPos[idx][3]][1] * dScale, dVec[nVecPos[idx][3]][2] * dScale).color(1.0f,0.2f,0.2f,alpha).endVertex();
        }

        //◆頂点登録 終了

        tessellator.draw();

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
