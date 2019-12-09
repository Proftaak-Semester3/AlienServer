package Websockets.handlers;

import Websockets.game.Game;
import org.json.JSONObject;

import javax.websocket.Session;

public class startPositionCheck implements Handler {
    @Override
    public void handleMessage(JSONObject json, Game game, Session session) {

    }
}
