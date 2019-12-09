package Websockets.handlers;

import Websockets.game.Game;
import Websockets.messageCreator.MessageCreator;
import Websockets.messageCreator.iCheckStartPositionMessage;
import org.json.JSONObject;

import javax.websocket.Session;

public class startPositionHandler implements Handler {
    private iCheckStartPositionMessage messageCreator;
    @Override
    public void handleMessage(JSONObject json, Game game, Session session) {
        if(game.startPositionBothHere())
        {
            try {
                messageCreator = new MessageCreator();
                JSONObject json1 = new JSONObject(game.getStartposition1());
                if(json.getInt("x1") == json1.getInt("x1") && json.getInt("y1") == json1.getInt("y1") && json.getInt("x2") == json1.getInt("x2") && json.getInt("y2") == json1.getInt("y2"))
                {
                    game.getSession1().getBasicRemote().sendText(messageCreator.checkStartPositionMessage(true).toString());
                }
                else
                {
                    game.getSession1().getBasicRemote().sendText(messageCreator.checkStartPositionMessage(false).toString());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            game.setStartposition1(json);
        }
    }
}
