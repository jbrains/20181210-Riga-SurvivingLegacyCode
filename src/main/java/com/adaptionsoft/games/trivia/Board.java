package com.adaptionsoft.games.trivia;

public class Board {

    public static final String[] repeatingCategoryNames = new String[]{"Pop", "Science", "Sports", "Rock"};

    // SMELL The else case here looks like a historical accident and not intentional behavior.
    // Is it safe to change the behavior when place is "off the board"? If we could do that,
    // then we could remove the ugly special case here.
    public static String category(int place) {
        if (place >= 0 && place < 12)
            return repeatingCategoryNames[place % repeatingCategoryNames.length];
        else
            return "Rock";
    }
}
