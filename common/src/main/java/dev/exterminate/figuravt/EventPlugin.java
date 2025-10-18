package dev.exterminate.figuravt;

import com.mojang.datafixers.util.Pair;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraEvent;
import org.figuramc.figura.entries.annotations.FiguraEventPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.figuramc.figura.lua.docs.LuaFieldDoc;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Example Event Plugin
 *  Annotation required for Forge to Locate and Load the Plugin
 *  Entrypoint in fabric.mod.json: figura_event
 */
@FiguraEventPlugin
public class EventPlugin implements FiguraEvent {

    @LuaWhitelist
    @LuaFieldDoc("events.vts_animation")
    public static LuaEvent VTSANIMATION = new LuaEvent();

    @Override
    public String getID() {
        return FiguraVT.PLUGIN_ID;
    }

    /**
     *  Available so that other mods can add in Events to Figura's Event API.
     *  Refer to ExampleMixin on how to call your events from a mixin, or for a more
     *  concrete example refer to Figura itself
     */
    @Override
    public Collection<Pair<String, LuaEvent>> getEvents() {
        return Collections.singleton(new Pair<>("VTSANIMATION", VTSANIMATION));
    }
}