package Websockets.handlers;

import Websockets.game.Game;
import org.json.JSONObject;

import javax.websocket.Session;

public interface Handler {
    void handleMessage(JSONObject json, Game game, Session session);
}
