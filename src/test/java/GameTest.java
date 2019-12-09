import Websockets.game.Game;
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
}
