import Websockets.game.Game;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void setGameSessions()
    {
        Game game = new Game(null);
        assertNull(game.getSession1());
        assertNull(game.getSession2());
        assertFalse(game.full());
    }
    @Test
    public void beginPositionCheck()
    {
        Game game = new Game(null);
        JSONObject json = new JSONObject();
        try {
            json.put("x1", 5);
            json.put("x2", 15);
            json.put("y1", 5);
            json.put("y2", 5);
            game.setStartposition1(json);
            assertEquals(game.getStartposition1().getInt("x1"), 5);
            assertEquals(game.getStartposition1().getInt("x2"), 15);
            assertEquals(game.getStartposition1().getInt("y1"), 5);
            assertEquals(game.getStartposition1().getInt("y2"), 5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
