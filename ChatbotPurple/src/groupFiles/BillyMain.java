package groupFiles;

import java.util.Scanner;

public class BillyMain {

	static Scanner input;
	static String user;
	static boolean inLoop;
	static String response;
	static Topic fps;
	static Topic ariq;
	static Topic jiaMing;
	static Topic kev;

	public static void main(String[] args) {
		createTopics();
		promptName();
		talkForever();
	}

	public static void promptName() {
		print("Hello, human! I am a GameBOT. What is your name?");
		user = input.nextLine();
	}

	public static void talkForever() {
		inLoop = true;
		while (inLoop) {
			print("Greetings, " + user + ". What genre of games do you like?");
			response = getInput();
			if (kev.isTriggered(response)) {
				inLoop = false;
				kev.talk();
			} else if (ariq.isTriggered(response)) {
				inLoop = false;
				ariq.talk();
			} else if (jiaMing.isTriggered(response)) {
				inLoop = false;
				jiaMing.talk();
			} else if (fps.isTriggered(response)) {
				inLoop = false;
				fps.talk();
			} else {
				print("I'm sorry I dont understand you.");
			}
		}
	}

	public static int findKeyword(String searchString, String key, int startIndex) {
		String phrase = searchString.trim();
		phrase = phrase.toLowerCase();
		key = key.toLowerCase();
		int psn = phrase.indexOf(key);
		while (psn >= 0) {
			String before = " ";
			String after = " ";
			if (psn + key.length() < phrase.length()) {
				after = phrase.substring(psn + key.length(), psn + key.length() + 1);
			}
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn);
			}
			if (before.compareTo("a") < 0 && after.compareTo("a") < 0) {
				if (noNegation(phrase, psn)) {
					return psn;
				}
				return -1;
			}
			psn = phrase.indexOf(key, psn + 1);
		}

		return -1;
	}

	private static boolean noNegation(String phrase, int index) {
		if (index - 3 >= 0 && phrase.substring(index - 3, index).equals("no ")) {
			return false;
		}
		if (index - 4 >= 0 && phrase.substring(index - 4, index).equals("not ")) {
			return false;
		}
		if (index - 6 >= 0 && phrase.substring(index - 6, index).equals("never ")) {
			return false;
		}
		if (index - 4 >= 0 && phrase.substring(index - 4, index).equals("n't ")) {
			return false;
		}
		return true;
	}

	public static String getInput() {
		return input.nextLine();
	}

	public static void print(String s) {
		String printString = "";
		int cutOff = 35;
		while (s.length() > 0) {
			String currentLine = "";
			String nextWord = "";
			while (currentLine.length() + nextWord.length() <= cutOff && s.length() > 0) {
				currentLine += nextWord;
				s = s.substring(nextWord.length());
				int endOfWord = s.indexOf(" ");
				if (endOfWord == -1) {
					endOfWord = s.length() - 1;
				}
				nextWord = s.substring(0, endOfWord + 1);
			}
			printString += currentLine + "\n";
		}
		System.out.println(printString);
	}

	public static void createTopics() {
		input = new Scanner(System.in);
		fps = new BillyFpsType();
		ariq = new AriqMultiplayer();
		jiaMing = new JiaMingMobileGame();
		kev = new KevinGame();
	}

}
