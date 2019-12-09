package Websockets.game;

import Websockets.messageCreator.iJsonCreator;
import Websockets.messageCreator.MessageCreator;
import Websockets.websocketAddons.MessageBroadcaster;

import javax.websocket.Session;
import java.util.ArrayList;

public class GameManager {
    private static ArrayList<Game> games = new ArrayList<>();
    private iJsonCreator messageCreator;

    public GameManager()
    {
        messageCreator = new MessageCreator();
    }

    public void matchMaking(Session session)
    {
        boolean gameAvailable = false;
        for (Game game : games) {
            if (!game.full()) {
                game.join(session);
                gameAvailable = true;
                MessageBroadcaster.broadcast(messageCreator.foundGameMessage(true), game.getSession1());
                MessageBroadcaster.broadcast(messageCreator.foundGameMessage(false), game.getSession2());
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
        try {
            if (session.getId() == toRemove.getSession1().getId()) {
                toRemove.getSession2().getBasicRemote().sendText(messageCreator.disconnectMessage().toString());
            }
            else if (session.getId() == toRemove.getSession2().getId())
            {
                toRemove.getSession1().getBasicRemote().sendText(messageCreator.disconnectMessage().toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        games.remove(toRemove);
    }
}
