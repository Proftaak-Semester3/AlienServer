package Websockets;

import org.json.JSONObject;

import javax.websocket.Session;
import java.util.ArrayList;

public class GameManager {
    private static ArrayList<Game> games = new ArrayList<>();

    public void matchMaking(Session session)
    {
        boolean gameAvailable = false;
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
                MessageBroadcaster.broadcast(json, game);
                break;
            }
        }
        if (!gameAvailable) {
            Game game = new Game(session);
            games.add(game);
            System.out.println("new game made");
        }
    }

    public Game getGame(Session session)
    {
        for (Game allgame : games) {
            if (allgame.isPlayerHere(session)) {
                return allgame;
            }
        }
        return null;
    }
    public void removeGame(Session session)
    {
        Game toRemove = null;
        for (Game game: games) {
            if(game.getSession2().getId() == session.getId() || game.getSession1().getId() == session.getId())
            {
                toRemove = game;
            }
        }
        games.remove(toRemove);
    }
}
