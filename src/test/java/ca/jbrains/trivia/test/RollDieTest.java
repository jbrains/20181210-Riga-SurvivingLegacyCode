package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.ReportMessagesToConsole;
import org.junit.Assert;
import org.junit.Test;

public class RollDieTest {
    @Test
    public void happyPath() throws Exception {
        final SinglePlayerGame game = SinglePlayerGame.withPlayerNotInPenaltyBoxStartingAtPlace(0);

        game.roll(3);

        Assert.assertEquals(3, game.thePlaceOfTheOnlyPlayer());
    }

    private static class SinglePlayerGame extends Game {
        public SinglePlayerGame(int theStartingPlaceOfTheOnlyPlayer, boolean isTheOnlyPlayerInThePenaltyBox) {
            super(new ReportMessagesToConsole() {
                @Override
                public void reportMessage(String message) {
                    // Intentionally do nothing
                }
            });
            add("::the only player's name::");
            places[0] = theStartingPlaceOfTheOnlyPlayer;
            inPenaltyBox[0] = isTheOnlyPlayerInThePenaltyBox;
        }

        // REFACTOR Test Data Builder pattern: http://defragdev.com/blog/?p=147
        public static SinglePlayerGame withPlayerNotInPenaltyBoxStartingAtPlace(final int theStartingPlaceOfTheOnlyPlayer) {
            return new SinglePlayerGame(
                    theStartingPlaceOfTheOnlyPlayer,
                    false );
        }

        public int thePlaceOfTheOnlyPlayer() {
            return places[0];
        }

        @Override
        protected void askQuestion() {
            // Intentionally do nothing
        }
    }
}
