package com.adaptionsoft.games.trivia;

public class Board {
    public static String category(int place) {
        final String[] rotatingCategoryNames = {"Pop", "Science", "Sports", "Rock"};

        if (place >= 0 && place < 12)
            return rotatingCategoryNames[place % 4];
        else
            return "Rock";
    }
}
