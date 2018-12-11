package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Assert;
import org.junit.Test;

public class ChooseCategoryByPlaceTest {
    @Test
    public void placesOnTheBoard() throws Exception {
        final String[] expectedCategoriesByPlaceOnTheBoard = {"Pop", "Science", "Sports", "Rock", "Pop", "Science", "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

        for (int place = 0; place < 12; place++) {
            Assert.assertEquals(expectedCategoriesByPlaceOnTheBoard[place], Game.category(place));
        }
    }
}
