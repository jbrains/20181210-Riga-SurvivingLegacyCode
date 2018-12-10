package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

public class RollDieTest {
    @Test
    public void happyPath() throws Exception {
        final Game game = new Game() {
            {
                add("::the only player's name::");
            }
        };
        
        game.roll(3);
    }
}
