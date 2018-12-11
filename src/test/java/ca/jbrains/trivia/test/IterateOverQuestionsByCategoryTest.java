package ca.jbrains.trivia.test;

import io.vavr.collection.List;
import io.vavr.collection.Traversable;
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

    private Traversable<String> iterateOverQuestions(List<String> questions) {
        return questions.toStream().cycle();
    }
}
