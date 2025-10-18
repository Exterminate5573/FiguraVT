package dev.exterminate.figuravt;

import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import ru.alexander.api.VTubeStudioAPI;
import ru.alexander.api.listeners.ResponseListener;
import ru.alexander.api.responses.ErrorResponse;
import ru.alexander.calls.events.AnimationEventCall;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

public class VTSAPI {

    public static VTubeStudioAPI api = new VTubeStudioAPI();
    private static final File tokenFile = ExampleExpectPlatform.getConfigDirectory().resolve("token.txt").toFile();

    private static final AnimationEventCall animationEventCall = new AnimationEventCall(false, false,
            null,
            event -> {
                FiguraVT.LOGGER.debug("Animation Event: {}", event.getAnimationName());

                Avatar localPlayer = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
                if (localPlayer == null || localPlayer.luaRuntime == null)
                    return;

                AnimationEventData localEventData = new AnimationEventData(event.getAnimationName());
                localPlayer.run("FIGURAVT.VTS_ANIMATION", localPlayer.render, localEventData);
            });

    public static void auth() {
        while (!api.isConnected()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ResponseListener<LinkedHashMap<String, Object>> auth = new ResponseListener<>() {
            @Override
            public void onSuccess(LinkedHashMap<String, Object> map) {
                FiguraVT.LOGGER.info("Authenticated with VTube Studio");
                api.call(animationEventCall);
            }

            @Override
            public void onFailure(ErrorResponse errorResponse) {
                System.err.println(errorResponse);
                try {
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException ignored) {}
            }
        };

        if (tokenFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(tokenFile);
                api.auth(
                        FiguraVT.PLUGIN_ID,
                        "Exterminate",
                        new String(fis.readAllBytes()),
                        auth
                );
                fis.close();
            } catch (IOException e) {
                System.err.println("Token read error! Requesting...");
                requestToken(auth);
            }

        }
        else requestToken(auth);
    }

    private static void requestToken(ResponseListener<LinkedHashMap<String, Object>> auth) {
        ResponseListener<LinkedHashMap<String, Object>> request = new ResponseListener<>() {
            @Override
            public void onSuccess(LinkedHashMap<String, Object> map) {
                FiguraVT.LOGGER.info("Token Requested");
                String token = (String) map.get("authenticationToken");
                try {
                    FileOutputStream fos = new FileOutputStream(tokenFile);
                    fos.write(token.getBytes());
                    fos.close();
                } catch (IOException e) {
                    System.err.println(e.getLocalizedMessage());

                    try {
                        Thread.sleep(1000);
                        System.exit(0);
                    } catch (InterruptedException ignored) {}
                }
                api.auth(
                        FiguraVT.PLUGIN_ID,
                        "Exterminate",
                        token,
                        auth
                );
            }

            @Override
            public void onFailure(ErrorResponse errorResponse) {
                System.err.println(errorResponse);

                try {
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException ignored) {}
            }
        };
        try {
            api.requestToken(
                    FiguraVT.PLUGIN_ID,
                    "Exterminate",
                    null, //TODO
                    request
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
