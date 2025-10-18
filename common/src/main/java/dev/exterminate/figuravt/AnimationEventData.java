package dev.exterminate.figuravt;

import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.figuramc.figura.lua.docs.LuaTypeDoc;

@LuaWhitelist
@LuaTypeDoc(name = "AnimationEventData", value = "vts_animation_event")
public class AnimationEventData {

    public String animationName;

    public AnimationEventData(String animationName) {
        this.animationName = animationName;
    }

    @LuaWhitelist
    @LuaMethodDoc("vts_animation_event.get_name")
    public String get_name() {
        return animationName;
    }
}
