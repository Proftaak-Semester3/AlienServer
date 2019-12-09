package Websockets;

import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;

@ServerEndpoint(value = "/alien/")
public class WebsocketS {
    private static HashSet<Session> sessions = new HashSet<>();
    private static messageHandler handler;
    private static GameManager gameManager;

    public WebsocketS() {
        handler = new messageHandler();
        this.gameManager = new GameManager();
    }

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[Connected] SessionID:" + session.getId());
        sessions.add(session);
        gameManager.matchMaking(session);
        System.out.println("[#sessions]: " + sessions.size());

    }

    @OnMessage
    public void onText(String Message, Session session) {
        System.out.println(Message);
        JSONObject json = null;
        try {
            json = new JSONObject(Message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.handleMessage(json, gameManager.getGame(session), session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
        gameManager.removeGame(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }
}
