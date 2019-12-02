package Websockets;

import org.json.JSONObject;

import javax.websocket.Session;

public class messageHandler {

    private WebsocketS socket;
    public messageHandler(WebsocketS socket) {
        this.socket = socket;
    }

    public void handleMessage(JSONObject json, Game game, Session session) {
        try {
            if (json.getString("task").equals("bullet")) {
                if(isBulletValid(json))
                {
                    socket.broadcast(json, game, session);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isBulletValid(JSONObject json) {
        int horizontal = 0;
        int vertical = 0;

        try {
            horizontal = json.getInt("horizontal");
            vertical = json.getInt("vertical");
        } catch (Exception e) {
            e.printStackTrace();
        }
/*        if (horizontal > 550 || horizontal < -550 || vertical > 330) {
            return false;
        } else {
            return true;
        }*/
        return true;
    }
}
