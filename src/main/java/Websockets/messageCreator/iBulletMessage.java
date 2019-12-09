package Websockets.messageCreator;

import Websockets.game.Game;
import org.json.JSONObject;

public interface iBulletMessage {
    JSONObject bulletMessage(JSONObject json);
}
