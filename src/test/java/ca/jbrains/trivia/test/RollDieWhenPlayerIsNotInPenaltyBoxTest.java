package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class RollDieWhenPlayerIsNotInPenaltyBoxTest {

    @Test
    public void happyPath() throws Exception {
        Assert.assertEquals(3, movePlayer(0, 3));
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
