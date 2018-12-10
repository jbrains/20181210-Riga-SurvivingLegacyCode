package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class RollDieTest {
    @Test
    public void happyPath() throws Exception {
        final SinglePlayerGame game = new SinglePlayerGame(0);

        game.roll(3);

        Assert.assertEquals(3, game.thePlaceOfTheOnlyPlayer());
    }

    private static class SinglePlayerGame extends Game {
        public SinglePlayerGame(int theStartingPlaceOfTheOnlyPlayer) {
            add("::the only player's name::");
            places[0] = theStartingPlaceOfTheOnlyPlayer;
        }

        public int thePlaceOfTheOnlyPlayer() {
            return places[0];
        }
    }
}
