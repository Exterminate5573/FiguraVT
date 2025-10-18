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

@LuaWhitelist
@FiguraEventPlugin
public class EventPlugin implements FiguraEvent {

    @LuaWhitelist
    @LuaFieldDoc("events.figuravt.vts_animation")
    public static LuaEvent VTSANIMATION = new LuaEvent();

    @Override
    public String getID() {
        return "figuravt";
    }

    @Override
    public Collection<Pair<String, LuaEvent>> getEvents() {
        FiguraVT.LOGGER.info("Registering VTS Animation Event");
        return Collections.singleton(new Pair<>("VTS_ANIMATION", VTSANIMATION));
    }
}