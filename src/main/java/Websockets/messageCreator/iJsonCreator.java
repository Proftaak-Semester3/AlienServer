package Websockets.messageCreator;

import Websockets.game.Game;
import org.json.JSONObject;

public interface iJsonCreator extends iDisconnectMessage, iBulletMessage,
        iCheckStartPositionMessage, iFoundGameMessage {

    JSONObject disconnectMessage();
    JSONObject bulletMessage(JSONObject json);
    JSONObject checkStartPositionMessage(boolean thesame);
    JSONObject foundGameMessage(boolean first);
}
