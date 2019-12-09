package Websockets.messageCreator;

import Websockets.game.Game;
import org.json.JSONObject;

public class MessageCreator implements iJsonCreator {
    @Override
    public JSONObject disconnectMessage() {
        JSONObject json = new JSONObject();
        try {
            json.put("task", "disconnect");
            json.put("win", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public JSONObject bulletMessage(JSONObject json) {
        return null;
    }

    @Override
    public JSONObject checkStartPositionMessage(boolean thesame) {
        JSONObject json = new JSONObject();
        try {
            json.put("task", "startposition");
            json.put("equal", thesame);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public JSONObject foundGameMessage(boolean first) {
        JSONObject json = new JSONObject();
        try {
            json.put("task", "found");
            json.put("first", first);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
