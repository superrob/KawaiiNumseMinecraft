package me.panda_studios.kawaiinumse.entity.model;

import me.panda_studios.kawaiinumse.Kawaiinumse;
import me.panda_studios.kawaiinumse.entity.KawaiiNumseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class KawaiiNumseModel extends AnimatedGeoModel<KawaiiNumseEntity> {

    @Override
    public ResourceLocation getModelResource(KawaiiNumseEntity object) {
        return new ResourceLocation(Kawaiinumse.MODID, "geo/entities/kawaiinumse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KawaiiNumseEntity object) {
        return new ResourceLocation(Kawaiinumse.MODID, "textures/entities/kawaiinumse.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KawaiiNumseEntity animatable) {
        return new ResourceLocation(Kawaiinumse.MODID, "animations/kawaiinumse.animation.json");
    }

    @Override
    public void setLivingAnimations(KawaiiNumseEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
