package Websockets;

import org.json.JSONObject;

public class MessageBroadcaster {

    public static void broadcast(JSONObject json, Game game)
    {
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        try {
            json1.put("task", "found");
            json1.put("first", true);
            game.getSession1().getBasicRemote().sendText(json1.toString());
            json2.put("task", "found");
            json2.put("first", false);
            game.getSession2().getBasicRemote().sendText(json2.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
