package com.adaptionsoft.games.trivia;

import io.vavr.collection.HashMap;
import io.vavr.collection.Iterator;
import io.vavr.collection.Stream;

public class QuestionDeck {
    private final HashMap<String, Iterator<String>> iteratorOverQuestionsByCategoryName;

    public QuestionDeck(HashMap<String, Iterator<String>> iteratorOverQuestionsByCategoryName) {
        this.iteratorOverQuestionsByCategoryName = iteratorOverQuestionsByCategoryName;
    }

    public static QuestionDeck fromQuestions(final HashMap<String, Stream<String>> questionsByCategoryName) {
        return new QuestionDeck(questionsByCategoryName.mapValues(questions -> questions.cycle().iterator()));
    }

    public String nextQuestionInCategory(String categoryName) {
        return iteratorOverQuestionsByCategoryName.get(categoryName).get().next();
    }
}
