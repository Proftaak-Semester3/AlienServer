package Websockets;

import javax.websocket.Session;

public class Game {
    private Session session1;
    private Session session2;

    public Game(Session session1)
    {
        this.session1 = session1;
        this.session2 = session2;
    }

    public boolean full()
    {
        if(session1 != null && session2 != null)
        {
            return true;
        }
        return false;
    }
    public void join(Session session)
    {
        if(session1 == null)
        {
            session1 = session;
        }
        else
        {
            session2 = session;
        }
    }
    public boolean isPlayerHere(Session session)
    {
        if(session1.getId() == session.getId() || session2.getId() == session.getId())
        {
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
}
