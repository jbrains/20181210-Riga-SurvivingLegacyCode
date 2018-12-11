package com.adaptionsoft.games.trivia;

public class Board {

    public static final String[] repeatingCategoryNames = new String[]{"Pop", "Science", "Sports", "Rock"};

    public static String category(int place) {
        if (place >= 0 && place < 12)
            return repeatingCategoryNames[place % repeatingCategoryNames.length];
        else
            return "Rock";
    }
}
