package groupFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KevinGame implements Topic {
	private static final String INTRO_MESSAGE = "Hello %s, we are playing rock paper and scissors. Decide your move.",
			REVEAL_MESSAGE = "I choose %s";

	private static final String[] RESULTS_NARRATIONS = { "You lost the game", "You tied the game.",
			"You won the game." };

	private static final String[] INVALID_MOVE_ERRORS = { "Sorry that is not a move. Please try again",
			"What are you saying? Choose a move.",
			"You are speaking gibberish. Seriously? It's either rock, paper, or scissor",
			"Stop speaking in baby language. You obviously don't want to play, so let's just stop." };

	private static final String[] TIE_MESSAGES = { "Seems like we tied.", "Seems like we tied... again...",
			"WHY DO WE KEEP TYING?", "Seriously stop copying me. We tied again?" };
	private static final String[] ONE_TRICK_PONY = { "You seem to be choosing %s a lot...",
			"I'm starting to catch onto your trick...", "Stop choosing %s so much!", "Once again %s really?" };

	private static final String[] WINNING_MESSAGES = { "This was an inevitable fate.",
			"Haha better luck next time fool.", "Didn't even break a sweat.", "GET DESTROYED PEASANT",
			"DON'T EVER COME BACK HERE AGAIN SCRUB" };
	private static final String[] LOSING_MESSAGES = { "Eh luck was on your side this time.", "What? How could this be?",
			"Heh... looking rigged...", "STOP CHEATING!" };

	private static final List<String> moves = new ArrayList<String>(Arrays.asList("paper", "scissor", "rock"));

	private int[] playerMovesTally;
	private int wins, loses, ties;

	private int invalidAngerLevel;

	public KevinGame() {
		this.playerMovesTally = new int[] { 0, 0, 0 };
		this.wins = this.loses = this.ties = 0;

		this.invalidAngerLevel = 0;
	}

	public static void kevSay(String s) {
		BillyMain.print("KevBot: " + s);
	}

	@Override
	public void talk() {
		kevSay(String.format(INTRO_MESSAGE, BillyMain.user));
		while (true) {
			String input = BillyMain.getInput();
			if (BillyMain.findKeyword(input, "exit", 0) != -1) {
				break;
			}

			int move = convertMoveToInt(input.toLowerCase());
			if (move == -1) {
				kevSay(INVALID_MOVE_ERRORS[invalidAngerLevel]);

				if (invalidAngerLevel == 3) {
					break;
				}

				invalidAngerLevel++;
				continue;
			}

			playerMovesTally[move]++;

			int randMov = chooseRandomMove();
			int result = compare(move, randMov);

			kevSay(String.format(REVEAL_MESSAGE, convertIntToMove(randMov)));
			BillyMain.print(RESULTS_NARRATIONS[result + 1]);
			if (result == 0) {
				kevSay(String.format(TIE_MESSAGES[ties % 4], input));
				ties++;
			} else if (result == 1) {
				kevSay(String.format(LOSING_MESSAGES[loses % 4], input));
				loses++;
			} else if (result == -1) {
				kevSay(String.format(WINNING_MESSAGES[wins % 4], input));
				wins++;
			}
		}

		BillyMain.talkForever();
	}

	@Override
	public boolean isTriggered(String userInput) {
		return BillyMain.findKeyword(userInput, "play", 0) != -1;
	}

	public int chooseRandomMove() {
		return (int) (Math.random() * 3);
	}

	/*
	 * Compare i and j as rock, paper scissor moves.
	 * 
	 * Returns:
	 *  -1 if i beats j
	 *   0 if i and j tie
	 *   1 if j beats i
	 */
	public int compare(int i, int j) {
		if ((i - 1 == j) || (i == 0 && j == 2)) {
			return 1;
		} else if ((j - 1 == i) || ( j == 0 && i == 2)) {
			return -1;
		}

		return 0;
	}

	public int convertMoveToInt(String move) {
		return moves.indexOf(move);
	}

	public String convertIntToMove(int move) {
		return moves.get(move);
	}
}
