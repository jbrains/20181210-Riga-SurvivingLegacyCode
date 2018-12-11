package ca.jbrains.trivia.test;

import com.adaptionsoft.games.uglytrivia.Game;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
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

    // QUESTION Is this behavior important, or an accident? It would be more convenient
    // to make this behavior undefined.
    @Property
    public boolean everyOtherPlaceIsRock(@ForAll int place) {
        if (place >= 0 && place < 12) return true;
        return "Rock".equalsIgnoreCase(Game.category(place));
    }
}
