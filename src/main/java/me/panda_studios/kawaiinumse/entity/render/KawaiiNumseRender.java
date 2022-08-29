package me.panda_studios.kawaiinumse.entity.render;

import me.panda_studios.kawaiinumse.entity.KawaiiNumseEntity;
import me.panda_studios.kawaiinumse.entity.model.KawaiiNumseModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KawaiiNumseRender extends GeoEntityRenderer<KawaiiNumseEntity> {
    public KawaiiNumseRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KawaiiNumseModel());
    }
}
