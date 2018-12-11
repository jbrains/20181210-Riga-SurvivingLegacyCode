package com.adaptionsoft.games.trivia;

public class Board {
    public static String category(int place) {
        final String[] rotatingCategoryNames = {"Pop", "Science", "Sports", "Rock"};

        if (place == 0) return rotatingCategoryNames[place % 4];
        if (place == 4) return rotatingCategoryNames[place % 4];
        if (place == 8) return rotatingCategoryNames[place % 4];
        if (place == 1) return rotatingCategoryNames[place % 4];
        if (place == 5) return rotatingCategoryNames[place % 4];
        if (place == 9) return rotatingCategoryNames[place % 4];
        if (place == 2) return rotatingCategoryNames[place % 4];
        if (place == 6) return rotatingCategoryNames[place % 4];
        if (place == 10) return rotatingCategoryNames[place % 4];
        if (place == 3) return rotatingCategoryNames[place % 4];
        if (place == 7) return rotatingCategoryNames[place % 4];
        if (place == 11) return rotatingCategoryNames[place % 4];

        return "Rock";
    }
}
