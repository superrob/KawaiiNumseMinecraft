package me.panda_studios.kawaiinumse.entity.block.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.panda_studios.kawaiinumse.entity.block.RainbowPTNTPrimed;
import me.panda_studios.kawaiinumse.setup.BlockSetup;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RainbowPTNTPrimedRender extends EntityRenderer<RainbowPTNTPrimed> {
    private final BlockRenderDispatcher blockRenderer;

    public RainbowPTNTPrimedRender(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(RainbowPTNTPrimed entity, float f2, float f3, PoseStack poseStack, MultiBufferSource multiBufferSource, int i1) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        int i = entity.getFuse();
        if ((float)i - f3 + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - f3 + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            poseStack.scale(f1, f1, f1);
        }

        poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5D, -0.5D, 0.5D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, BlockSetup.RAINBOW_POWERED_TNT.get().defaultBlockState(), poseStack, multiBufferSource, i1, i / 5 % 2 == 0);
        poseStack.popPose();
        super.render(entity, f2, f3, poseStack, multiBufferSource, i1);
    }

    @Override
    public ResourceLocation getTextureLocation(RainbowPTNTPrimed entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
