package me.panda_studios.kawaiinumse.entity.render;

import me.panda_studios.kawaiinumse.entity.ThrownEnergyball;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ThrownEnergyballRender extends ThrownItemRenderer<ThrownEnergyball> {
    public ThrownEnergyballRender(EntityRendererProvider.Context context) {
        super(context, 1f, true);
    }
}
