package Websockets;

import com.google.gson.Gson;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashSet;

@ServerEndpoint(value = "/alien/")
public class WebsocketS {
    static HashSet<Session> sessions = new HashSet<>();
    static ArrayList<Game> games = new ArrayList<>();
    private messageHandler handler;
    private Gson gson;

    public WebsocketS() {
        handler = new messageHandler(this);
        gson = new Gson();
    }

    @OnOpen
    public void onConnect(Session session) {
        boolean gameAvailable = false;
        System.out.println("[Connected] SessionID:" + session.getId());
        sessions.add(session);
        for (Game game : games) {
            if (!game.full()) {
                game.join(session);
                gameAvailable = true;
                JSONObject json = new JSONObject();
                try {
                    json.put("task", "found");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                broadcast(json, game);
                break;
            }
        }
        if (!gameAvailable) {
            Game game = new Game(session);
            games.add(game);
            System.out.println("new game made");
        }
        System.out.println("[#sessions]: " + sessions.size());

    }

    @OnMessage
    public void onText(String Message, Session session) throws IllegalAccessException {
        System.out.println(Message);
        JSONObject json = null;
        Game game = null;
        try {
            json = new JSONObject(Message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Game allgame : games) {
            if (allgame.isPlayerHere(session)) {
                game = allgame;
            }
        }
        if (game == null) {
            throw new IllegalAccessException("user not found in a game");
        }
        handler.handleMessage(json, game, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
        for (Game game : games) {
            if (session.getId() == game.getSession1().getId() || session.getId() == game.getSession2().getId()) {
                games.remove(game);
            }
        }
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    public void broadcast(JSONObject json, Game game, Session session) {
        try {
            if(json.get("task").equals("bullet"))
            {
                if(game.getSession1().getId() == session.getId())
                {
                    game.getSession2().getBasicRemote().sendText(json.toString());
                }
                else
                {
                    game.getSession1().getBasicRemote().sendText(json.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[End of Broadcast]");
    }

    public void broadcast(JSONObject json, Game game) {
        try {
            if (json.get("task").equals("found")) {
                JSONObject jsonfirst = json;
                JSONObject jsonsecond = json;

                jsonfirst.put("first", true);
                game.getSession1().getBasicRemote().sendText(jsonfirst.toString());

                jsonsecond.put("first", false);
                game.getSession2().getBasicRemote().sendText(jsonsecond.toString());

            }
            else if(json.get("task").equals("bullet"))
            {
                game.getSession1().getBasicRemote().sendText(json.toString());
                game.getSession2().getBasicRemote().sendText(json.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[End of Broadcast]");
    }
}
