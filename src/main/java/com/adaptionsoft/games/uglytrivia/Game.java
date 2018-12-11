package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Board;
import com.adaptionsoft.games.trivia.QuestionDeck;
import io.vavr.collection.HashMap;
import io.vavr.collection.Stream;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	private final ReportMessages reportMessages;
	ArrayList players = new ArrayList();
    protected int[] places = new int[6];
    int[] purses  = new int[6];
    protected boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
	private QuestionDeck questionDeck;

	public  Game() {
		this(new ReportMessagesToConsole());
	}

	public  Game(final ReportMessages reportMessages){
		// REFACTOR Create QuestionDeck directly, then populate legacy fields from it.
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    	// We need deep copies here to avoid concurrent modification errors
		questionDeck = QuestionDeck.fromQuestions(HashMap.of(
				"Pop", Stream.ofAll(new ArrayList(popQuestions)),
				"Science", Stream.ofAll(new ArrayList(scienceQuestions)),
				"Sports", Stream.ofAll(new ArrayList(sportsQuestions)),
				"Rock", Stream.ofAll(new ArrayList(rockQuestions))
		));
		this.reportMessages = reportMessages;
	}

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

		reportMessages.reportMessage(playerName + " was added");
		reportMessages.reportMessage("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		reportMessages.reportMessage(players.get(currentPlayer) + " is the current player");
		reportMessages.reportMessage("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion(currentCategory());
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

			reportMessages.reportMessage(players.get(currentPlayer)
					+ "'s new location is "
					+ places[currentPlayer]);
			reportMessages.reportMessage("The category is " + currentCategory());
			askQuestion(currentCategory());
		}
		
	}

	protected void askQuestion(final String categoryName) {
        System.out.println(questionDeck.nextQuestionInCategory(categoryName));
        legacyAskQuestionInCategoryNamed(categoryName);
    }

    private void legacyAskQuestionInCategoryNamed(String currentCategoryName) {
        // Preserve the state of the legacy fields until we confirm that nobody uses them.
        if (currentCategoryName == "Pop") {
            popQuestions.removeFirst();
        }
        if (currentCategoryName == "Science") {
            scienceQuestions.removeFirst();
        }
        if (currentCategoryName == "Sports") {
            sportsQuestions.removeFirst();
        }
        if (currentCategoryName == "Rock") {
            rockQuestions.removeFirst();
        }
    }

    private String currentCategory() {
		return Board.category(places[currentPlayer]);
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
