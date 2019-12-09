package Websockets.websocketAddons;

import Websockets.game.Game;
import org.json.JSONObject;

import javax.websocket.Session;
import java.io.IOException;

public class MessageBroadcaster {

    public static void broadcast(JSONObject json, Session session)
    {
        try {
            session.getBasicRemote().sendText(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void broadcast(JSONObject json, Game game)
    {
        try {
            game.getSession1().getBasicRemote().sendText(json.toString());
            game.getSession2().getBasicRemote().sendText(json.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
