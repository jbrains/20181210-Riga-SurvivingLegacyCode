package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

public class RollDieTest {
    @Test
    public void happyPath() throws Exception {
        final Game game = new Game();
        game.roll(3);
    }
}
