package Websockets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

    public WebsocketS()
    {
        handler = new messageHandler();
        gson = new Gson();
    }

    @OnOpen
    public void onConnect(Session session) {
        boolean gameAvailable = false;
        System.out.println("[Connected] SessionID:"+session.getId());
        sessions.add(session);
        for (Game game: games) {
            if(!game.full())
            {
                game.join(session);
                gameAvailable = true;
                JSONObject json = new JSONObject();
                try {
                    json.put("task", "found");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                broadcast(json, game);
                break;
            }
        }
        if(!gameAvailable)
        {
            Game game = new Game(session);
            games.add(game);
            System.out.println("new game made");
        }
        System.out.println("[#sessions]: " + sessions.size());

    }
    @OnMessage
    public void onText(String Message, Session session) {
        JSONObject json = null;
        Game game = null;
        try {
            json = new JSONObject(Message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for (Game allgame: games) {
            if(allgame.isPlayerHere(session))
            {
                game = allgame;
            }
        }
        handler.handleMessage(json, game);
    }
    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[Socket Closed: " + reason);
        sessions.remove(session);
        for (Game game: games) {
            if(session.getId() == game.getSession1().getId() || session.getId() == game.getSession2().getId())
            {
                games.remove(game);
            }
        }
    }
    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }
    public void broadcast(JSONObject json) {
        System.out.println("[Broadcast] {  } to:");
        for(Session session : sessions) {
            session.getAsyncRemote().sendObject(json);
            System.out.println("\t\t >> Client associated with server side session ID: " + session.getId());
        }
        System.out.println("[End of Broadcast]");
    }
    public void broadcast(JSONObject json, Game game)
    {
        JSONObject jsonfirst = json;
        JSONObject jsonsecond = json;
        try {
            jsonfirst.put("first", true);
            game.getSession1().getBasicRemote().sendText(jsonfirst.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            jsonsecond.put("first", false);
            game.getSession2().getBasicRemote().sendText(jsonsecond.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("[End of Broadcast]");
    }
}
