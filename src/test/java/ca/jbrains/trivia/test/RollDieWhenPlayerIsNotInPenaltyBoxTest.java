package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class RollDieWhenPlayerIsNotInPenaltyBoxTest {

    @Test
    public void simpleHappyPaths() throws Exception {
        Assert.assertEquals(3, movePlayer(0, 3));
        Assert.assertEquals(6, movePlayer(0, 6));
        Assert.assertEquals(11, movePlayer(5, 6));
    }

    @Test
    public void movingPastTheEdgeOfTheBoard() throws Exception {
        Assert.assertEquals(0, movePlayer(6, 6));
        Assert.assertEquals(0, movePlayer(11, 1));
        Assert.assertEquals(1, movePlayer(11, 2));
        Assert.assertEquals(5, movePlayer(11, 6));
    }

    @Test
    public void theBoardAppearsBrokenIfWeGoFarEnough() throws Exception {
        // I infer from the code that the game board _should_ be circular,
        // but currently the board does not behave as a perfect circle.
        // In production, this "should never be a problem", but it's not clear
        // to me whether we can safely "fix" the board to behave like a circle.

        // If we could make the board into a circle, then that would simplify
        // the code significantly!

        Assert.assertEquals("Is the board circular now?", 23 + 1 - 12, movePlayer(23, 1));
        Assert.assertEquals("Is the board circular now?", 11 + 28 - 12, movePlayer(11, 28));
    }

    private int movePlayer(final int fromPlace, final int byRolling) {
        final SinglePlayerGame game = new SinglePlayerGameBuilder().withPlayerInPenaltyBox(false).withPlayerStartingAtPlace(fromPlace).build();

        game.roll(byRolling);

        return game.thePlaceOfTheOnlyPlayer();
    }

    private static class SinglePlayerGameBuilder {
        private boolean inPenaltyBox;
        private int startingPlace;

        public SinglePlayerGameBuilder withPlayerInPenaltyBox(boolean inPenaltyBox) {
            this.inPenaltyBox = inPenaltyBox;
            return this;
        }

        public SinglePlayerGameBuilder withPlayerStartingAtPlace(int place) {
            this.startingPlace = place;
            return this;
        }

        public SinglePlayerGame build() {
            return new SinglePlayerGame(startingPlace, inPenaltyBox);
        }
    }
    private static class SinglePlayerGame extends Game {
        public SinglePlayerGame(int theStartingPlaceOfTheOnlyPlayer, boolean isTheOnlyPlayerInThePenaltyBox) {
            super(message -> {});
            add("::the only player's name::");
            places[0] = theStartingPlaceOfTheOnlyPlayer;
            inPenaltyBox[0] = isTheOnlyPlayerInThePenaltyBox;
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
