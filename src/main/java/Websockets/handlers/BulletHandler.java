package Websockets.handlers;

import Websockets.game.Game;
import org.json.JSONObject;

import javax.websocket.Session;

public class BulletHandler implements Handler {
    @Override
    public void handleMessage(JSONObject json, Game game, Session session) {
        try {
            if (game.getSession1().getId() == session.getId()) {
                game.getSession2().getBasicRemote().sendText(json.toString());
            } else {
                game.getSession1().getBasicRemote().sendText(json.toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
