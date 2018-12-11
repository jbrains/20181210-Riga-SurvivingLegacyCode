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
                iterateOverQuestions(
                        List.of("::question 1::", "::question 2::", "::question 3::")
                ).take(9)
        );
    }

    @Test
    public void cycleThroughQuestionsInMultipleCategories() throws Exception {
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
                        HashMap.of(
                                "rock", generateQuestions("rock", 5).iterator(),
                                "science", generateQuestions("science", 5).iterator(),
                                "pop", generateQuestions("pop", 5).iterator(),
                                "sports", generateQuestions("sports", 5).iterator()
                        ),
                        Stream.of(
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

    private Traversable<String> iterateOverQuestionsByCategory(HashMap<String, Iterator<String>> questionsByCategoryName, Stream<String> categoryNames) {
        return categoryNames.map(categoryName -> getNextQuestion(questionsByCategoryName, categoryName));
    }

    private String getNextQuestion(HashMap<String, Iterator<String>> questionsByCategoryName, String categoryName) {
        return questionsByCategoryName.get(categoryName).get().next();
    }

    private Stream<String> generateQuestions(String categoryName, int howMany) {
        return Stream.rangeClosed(1, howMany).map(n -> String.format("::%s question %d::", categoryName, n));
    }

    private Traversable<String> iterateOverQuestions(List<String> questions) {
        return questions.toStream().cycle();
    }
}
