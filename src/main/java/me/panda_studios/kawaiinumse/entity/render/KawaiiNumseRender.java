package me.panda_studios.kawaiinumse.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.panda_studios.kawaiinumse.entity.KawaiiNumseEntity;
import me.panda_studios.kawaiinumse.entity.model.KawaiiNumseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Random;

public class KawaiiNumseRender extends GeoEntityRenderer<KawaiiNumseEntity> {
    Random random = new Random();

    float r;
    float g;
    float b;

    public KawaiiNumseRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KawaiiNumseModel());

        int min = 50;
        int max = 200;
        this.r = this.random.nextInt(max-min) + min;
        this.g = this.random.nextInt(max-min) + min;
        this.b = this.random.nextInt(max-min) + min;
    }

    @Override
    public void render(GeoModel model, KawaiiNumseEntity animatable, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer,
                       @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                animatable.getColor().x(), animatable.getColor().y(), animatable.getColor().z(), alpha);
    }
}
