package dev.exterminate.figuravt.forge;

import dev.exterminate.figuravt.FiguraVT;
import net.minecraftforge.event.GameShuttingDownEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * A mod class is needed for Forge to load the Plugin
 */
@Mod(FiguraVT.PLUGIN_ID)
public class ExampleModForge {
    public ExampleModForge() {
        FiguraVT.init();
    }

    @SubscribeEvent
    public void onShutdown(GameShuttingDownEvent event) {
        FiguraVT.close();
    }
}
