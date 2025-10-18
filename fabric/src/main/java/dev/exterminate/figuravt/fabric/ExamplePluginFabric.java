package dev.exterminate.figuravt.fabric;

import dev.exterminate.figuravt.FiguraVT;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;


/**
 * A mod class is not technically needed for Fabric to load the Plugin, but it's still nice to have.
 */
public class ExamplePluginFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FiguraVT.init();

        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            FiguraVT.close();
        });
    }

}
