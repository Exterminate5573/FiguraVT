package dev.exterminate.figuravt;

import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraAPI;
import org.figuramc.figura.entries.annotations.FiguraAPIPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alexander.api.VTubeStudioAPI;
import ru.alexander.api.listeners.ResponseListener;
import ru.alexander.api.responses.ErrorResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

@FiguraAPIPlugin
@LuaWhitelist
public class FiguraVT implements FiguraAPI {

    public static final String PLUGIN_ID = "figura-vt";
    public static final Logger LOGGER = LoggerFactory.getLogger(PLUGIN_ID);
    private Avatar avatar;

    public FiguraVT() {

    }

    public FiguraVT(Avatar avatar) {
        super();
        this.avatar = avatar;
    }

    /**
     * You can do common things on init here
     */
    public static void init() {
        VTSAPI.auth();
    }

    public static void close() {
        VTSAPI.api.close();
    }

    @Override
    public FiguraAPI build(Avatar avatar) {
        return new FiguraVT(avatar);
    }

    @Override
    public String getName() {
        return PLUGIN_ID;
    }

    /**
     * You must whitelist your classes for your Plugin to work correctly! This cannot be null
     */
    @Override
    public Collection<Class<?>> getWhitelistedClasses() {
        List<Class<?>> classesToRegister = new ArrayList<>();
        for (Class<?> aClass : FIGURAVT_PLUGIN_CLASSES) {
            if (aClass.isAnnotationPresent(LuaWhitelist.class)) {
                classesToRegister.add(aClass);
            }
        }
        return classesToRegister;
    }

    /**
     * This can be empty, but not null
     */
    @Override
    public Collection<Class<?>> getDocsClasses() {
        return List.of();
    }

    public static final Class<?>[] FIGURAVT_PLUGIN_CLASSES = new Class[] {
            FiguraVT.class,
            EventPlugin.class,
            AnimationEventData.class
    };

}