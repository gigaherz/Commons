package gigaherz.common.client;

import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.pipeline.LightUtil;
import org.lwjgl.opengl.GL11;

public class StackRenderingHelper
{
    public static void renderItemStack(ItemModelMesher mesher, TextureManager renderEngine, int xPos, int yPos, ItemStack stack, int color)
    {
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);

        GlStateManager.pushMatrix();

        IBakedModel model = mesher.getItemModel(stack);
        setupGuiTransform(xPos, yPos, model.isGui3d());
        model = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GUI, false);

        GlStateManager.translate(-0.5F, -0.5F, -0.5F);

        renderItem(model, color);

        GlStateManager.popMatrix();

        renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();

        RenderHelper.disableStandardItemLighting();
    }

    private static void renderItem(IBakedModel model, int color)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);

        for (BakedQuad bakedquad : model.getQuads(null, null, 0))
        {
            LightUtil.renderQuadColor(worldrenderer, bakedquad, color);
        }

        tessellator.draw();
    }

    private static void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d)
    {
        GlStateManager.translate(xPosition, yPosition, 150);
        GlStateManager.translate(8.0F, 8.0F, 0.0F);
        GlStateManager.scale(1.0F, -1.0F, 1.0F);
        GlStateManager.scale(16.0F, 16.0F, 16.0F);

        if (isGui3d)
        {
            GlStateManager.enableLighting();
        }
        else
        {
            GlStateManager.disableLighting();
        }
    }
}
