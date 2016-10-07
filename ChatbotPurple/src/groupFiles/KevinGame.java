package groupFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * Kevin Zheng's Rock Paper Scissor Chatbot
 * 
 * Features:
 * - Increasingly excited/aggravated responses based on wins/loses/ties
 * - Recognizes repeated moves by player (choosing rock several times in a roll) and calls out player for predictability
 * - Provides score tracking on exit
 * 
 * Triggers: rps, play
 * 
 * ========
 * |RUBRIC|
 * ========
 * Variety:     The bot responds in different manners to the user's moves. 
 * Originality: The bot plays rock paper and scissor with the user :)
 * Finish:      Complete
 * 
 */

public class KevinGame implements Topic {
	private static final String INTRO_MESSAGE = "Hello %s, we are playing rock paper and scissors. Decide your move.",
			REVEAL_MESSAGE = "I choose %s";

	private static final String[] RESULTS_NARRATIONS = { "You lost the game", "You tied the game.",
			"You won the game." };

	private static final String[] INVALID_MOVE_ERRORS = { "Sorry that is not a move. Please try again",
			"What are you saying? Choose a move.",
			"You are speaking gibberish. Seriously? It's either rock, paper, or scissor",
			"Stop speaking in baby language. You obviously don't want to play, so let's just stop." };

	private static final String[] ONE_TRICK_PONY = { "You seem to be choosing %s a lot...",
			"I'm starting to catch onto your trick...", "Stop choosing %s so much!", "Once again %s really?" };

	private static final String[] WINNING_MESSAGES = { "This was an inevitable fate.",
			"Haha better luck next time fool.", "Didn't even break a sweat.", "GET DESTROYED PEASANT",
			"DON'T EVER COME BACK HERE AGAIN SCRUB" };
	private static final String[] LOSING_MESSAGES = { "Eh luck was on your side this time.", "What? How could this be?",
			"Heh... looking rigged...", "STOP CHEATING!" };
	private static final String[] TIE_MESSAGES = { "Seems like we tied.", "Seems like we tied... again...",
			"WHY DO WE KEEP TYING?", "Seriously stop copying me. We tied again?" };
	private static final String[][] MESSAGES = { WINNING_MESSAGES, TIE_MESSAGES, LOSING_MESSAGES };

	private static final List<String> moves = new ArrayList<String>(Arrays.asList("paper", "scissor", "rock"));

	private int[] log;
	private int invalidAngerLevel;
	private int lastMove, moveStreak;

	public KevinGame() {
		reset();
	}

	// wrapper of BillyMain.print
	private static void kevSay(String s) {
		BillyMain.print("KevBot: " + s);
	}

	public void reset() {
		this.log = new int[3];
		
		this.lastMove = -1;
		this.moveStreak = 0;

		this.invalidAngerLevel = 0;
	}

	@Override
	public void talk() {
		kevSay(String.format(INTRO_MESSAGE, BillyMain.user));

		while (true) {
			String input = BillyMain.getInput();
			if (BillyMain.findKeyword(input, "exit", 0) != -1 || BillyMain.findKeyword(input, "quit", 0) != -1) {
				
			    kevSay("Cya later alligator. You took the L.");
			    BillyMain.print("You won " + log[2] + " times, lost " + log[0] + "times, and tied " + log[1] + " times.");
				break;
			}

			if (input.equals("scissors")) input = "scissor"; // possibility user wants more than one scissor?
			int move = convertMoveToInt(input.toLowerCase());

			// check and handle invalid move
			if (move == -1) {
				// select error msg based on anger level from repeated incoherency
				String error = INVALID_MOVE_ERRORS[invalidAngerLevel];
				kevSay(error);

				if (invalidAngerLevel == 3) {
					break;
				}

				invalidAngerLevel++;
				continue;
			}
			
			// simulate a match
			int randMov = chooseRandomMove();
			int result = compare(move, randMov);

			// print result
			String reveal = String.format(REVEAL_MESSAGE, convertIntToMove(randMov));
			kevSay(reveal);
			BillyMain.print(RESULTS_NARRATIONS[result]);

			// print kevbot opinion
			String response = generateResponse(result, input);
			kevSay(response);
			
			// log result
			log[result]++;
			
			// log move
			this.moveStreak = this.lastMove == move ? this.moveStreak + 1 : 0;
			this.lastMove = move;
		}
	
		// reset the bot's memory and go back to main
		reset();
		BillyMain.talkForever();
	}

	// generate response based on the match result and user's move
	private String generateResponse(int result, String userMove) {
		String message = null;
		
		// chance for KevBot to call out user for being predictable from using the same move consecutively
		int rand = new Random().nextInt(4);
		if (convertMoveToInt(userMove) == lastMove && moveStreak > 1 && moveStreak < 6 && rand != 3) {
			message = String.format(ONE_TRICK_PONY[moveStreak - 2], userMove);
		} else {
			// select message based on match result and aggravation/excitement level
			int excitementLevel = log[result] % MESSAGES[result].length;
			message = MESSAGES[result][excitementLevel];
		}

		return message;
	}

	@Override
	public boolean isTriggered(String userInput) {
		return BillyMain.findKeyword(userInput, "play", 0) != -1 || BillyMain.findKeyword(userInput, "rps", 0) != -1;
	}

	/*
	 * compare i and j as rock, paper scissor moves
	 * 
	 * returns:
	 *  0 if i beats j
	 *  1 if i and j tie 
	 *  2 if j beats i
	 */
	private int compare(int i, int j) {
		if ((i - 1 == j) || (i == 0 && j == 2)) {
			return 2;
		} else if ((j - 1 == i) || (j == 0 && i == 2)) {
			return 0;
		}

		return 1;
	}

	/*
	 * move to int mappings 
	 * 0 - paper
	 * 1 - scissor
	 * 2 - rock
	 */
	private int chooseRandomMove() {
		return (int) (Math.random() * 3);
	}

	private int convertMoveToInt(String move) {
		return moves.indexOf(move);
	}

	private String convertIntToMove(int move) {
		return moves.get(move);
	}
}
