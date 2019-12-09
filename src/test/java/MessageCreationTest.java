import Websockets.messageCreator.MessageCreator;
import Websockets.messageCreator.iCheckStartPositionMessage;
import Websockets.messageCreator.iDisconnectMessage;
import Websockets.messageCreator.iFoundGameMessage;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageCreationTest {

    @Test
    public void checkStartPositionMessageTrueTest() {
        iCheckStartPositionMessage messageCreator = new MessageCreator();
        JSONObject json = messageCreator.checkStartPositionMessage(true);
        try {
            assertEquals(json.getString("task"), "startposition");
            assertTrue(json.getBoolean("equal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkStartPositionMessageFalseTest() {
        iCheckStartPositionMessage messageCreator = new MessageCreator();
        JSONObject json = messageCreator.checkStartPositionMessage(false);
        try {
            assertEquals(json.getString("task"), "startposition");
            assertFalse(json.getBoolean("equal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void disconnectMessageTest() {
        iDisconnectMessage messageCreator = new MessageCreator();
        JSONObject json = messageCreator.disconnectMessage();
        try {
            assertEquals(json.getString("task"), "disconnect");
            assertTrue(json.getBoolean("win"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void foundGameMessageTrueTest() {
        iFoundGameMessage messageCreator = new MessageCreator();
        JSONObject json = messageCreator.foundGameMessage(true);
        try {
            assertEquals(json.getString("task"), "found");
            assertTrue(json.getBoolean("first"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void foundGameMessageFalseTest() {
        iFoundGameMessage messageCreator = new MessageCreator();
        JSONObject json = messageCreator.foundGameMessage(false);
        try {
            assertEquals(json.getString("task"), "found");
            assertFalse(json.getBoolean("first"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
