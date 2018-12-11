package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;

public class SinglePlayerGame extends Game {
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
