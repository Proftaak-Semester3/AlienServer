package Websockets.game;

import org.json.JSONObject;

import javax.websocket.Session;

public class Game {
    private Session session1;
    private Session session2;
    private int startposition = 0;
    private JSONObject startposition1;

    public Game(Session session1) {
        this.session1 = session1;
    }

    public boolean full() {
        if (session1 != null && session2 != null) {
            return true;
        }
        return false;
    }

    public void join(Session session) {
        if (session1 == null) {
            session1 = session;
        } else {
            session2 = session;
        }
    }

    public boolean isPlayerHere(Session session) {
        if (session1.getId() == session.getId() || session2.getId() == session.getId()) {
            return true;
        }
        return false;
    }
    public Session getSession1() {
        return session1;
    }
    public Session getSession2() {
        return session2;
    }
    public JSONObject getStartposition1() { return startposition1; }
    public void setStartposition1(JSONObject startposition1) { this.startposition1 = startposition1; startposition++; }
    public boolean startPositionBothHere() {
        if (startposition == 2) {
            return true;
        }
        return false;
    }
}
