package ca.jbrains.trivia.test;

import io.vavr.collection.*;
import org.junit.Assert;
import org.junit.Test;

public class IterateOverQuestionsByCategoryTest {
    @Test
    public void cycleThroughQuestionsInOneCategory() throws Exception {
        Assert.assertEquals(
                List.of("::question 1::", "::question 2::", "::question 3::",
                        "::question 1::", "::question 2::", "::question 3::",
                        "::question 1::", "::question 2::", "::question 3::"
                ),
                Stream.of("::question 1::", "::question 2::", "::question 3::").cycle().iterator().take(9).toList()
        );
    }

    @Test
    public void iterateOverQuestionsInMultipleCategories() throws Exception {
        Assert.assertEquals(
                List.of(
                        "::rock question 1::",
                        "::rock question 2::",
                        "::science question 1::",
                        "::pop question 1::",
                        "::rock question 3::",
                        "::sports question 1::",
                        "::science question 2::",
                        "::sports question 2::",
                        "::sports question 3::",
                        "::sports question 4::",
                        "::science question 3::",
                        "::rock question 4::",
                        "::pop question 2::"
                ),
                iterateOverQuestionsByCategory(
                        QuestionDeck.fromQuestions(HashMap.of(
                                "rock", generateQuestions("rock", 5),
                                "science", generateQuestions("science", 5),
                                "pop", generateQuestions("pop", 5),
                                "sports", generateQuestions("sports", 5)
                        )), Stream.of(
                                "rock",
                                "rock",
                                "science",
                                "pop",
                                "rock",
                                "sports",
                                "science",
                                "sports",
                                "sports",
                                "sports",
                                "science",
                                "rock",
                                "pop"))
        );
    }

    @Test
    public void cycleThroughQuestionsInMultipleCategoriesEvenWhenRunningOutOfQuestions() throws Exception {
        Assert.assertEquals(
                List.of(
                        "::rock question 1::",
                        "::rock question 2::",
                        "::science question 1::",
                        "::pop question 1::",
                        "::rock question 1::",
                        "::sports question 1::",
                        "::science question 2::",
                        "::sports question 2::",
                        "::sports question 3::",
                        "::sports question 1::",
                        "::science question 3::",
                        "::rock question 2::",
                        "::pop question 1::"
                ),
                iterateOverQuestionsByCategory(
                        QuestionDeck.fromQuestions(HashMap.of(
                                "rock", generateQuestions("rock", 2),
                                "science", generateQuestions("science", 3),
                                "pop", generateQuestions("pop", 1),
                                "sports", generateQuestions("sports", 3)
                        )), Stream.of(
                                "rock",
                                "rock",
                                "science",
                                "pop",
                                "rock",
                                "sports",
                                "science",
                                "sports",
                                "sports",
                                "sports",
                                "science",
                                "rock",
                                "pop"))
        );
    }

    private Traversable<String> iterateOverQuestionsByCategory(final QuestionDeck questionDeck, Stream<String> categoryNames) {
        return categoryNames.map(categoryName -> questionDeck.nextQuestionInCategory(categoryName));
    }

    private Stream<String> generateQuestions(String categoryName, int howMany) {
        return Stream.rangeClosed(1, howMany).map(n -> String.format("::%s question %d::", categoryName, n));
    }

}
