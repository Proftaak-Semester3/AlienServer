package Websockets;

import org.json.JSONObject;

import javax.websocket.Session;

public class messageHandler {

    public void handleMessage(JSONObject json, Game game, Session session) {
        HandlerContext.processMessage(json, game, session);
    }
}
