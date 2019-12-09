package Websockets;

import Websockets.handlers.BulletHandler;
import Websockets.handlers.Handler;
import org.json.JSONObject;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class HandlerContext {
    private static Map<String, Handler> handlers;

    static {
        handlers = new HashMap<>();
        handlers.put("bullet", new BulletHandler());
    }

    public static void processMessage(JSONObject json, Game game, Session session) {
        String handler = null;
        try {
            handler = json.getString("task");
        } catch (Exception e) {
            e.printStackTrace();
        }
        handlers.get(handler).handleMessage(json, game, session);
    }
}
