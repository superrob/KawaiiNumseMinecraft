package me.panda_studios.kawaiinumse.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.panda_studios.kawaiinumse.entity.KawaiiNumseEntity;
import me.panda_studios.kawaiinumse.entity.model.KawaiiNumseModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KawaiiNumseRender extends GeoEntityRenderer<KawaiiNumseEntity> {
    public KawaiiNumseRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KawaiiNumseModel());
    }

    @Override
    public RenderType getRenderType(KawaiiNumseEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
