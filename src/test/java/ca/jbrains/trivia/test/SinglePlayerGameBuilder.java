package ca.jbrains.trivia.test;

public class SinglePlayerGameBuilder {
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
