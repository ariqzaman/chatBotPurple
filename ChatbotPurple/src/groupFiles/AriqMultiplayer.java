package groupFiles;

public class AriqMultiplayer implements Topic {

	private boolean inMultiplayerLoop;
	private String MultiplayerResponse;

	private boolean gameTrigger;

	private static String[] multi = { "Then why would you tell me you liked multiplayer games?" ,"Im done talking about multiplayer games." };
	private static String[] favMultiplayerGame = { "overwatch", "league of legends", "dota", "tera", "cs:go", "lol","world of warcraft","wow",
			"counter strike global offensive" };
	private static String[] deny = { "don't", "no", "not" };

	public void talk() {
		int ariqIndex = 0;
		inMultiplayerLoop = true;
		main: while (inMultiplayerLoop) {
			if (gameTrigger) {
				BillyMain.print("That's a cool multi-player game");
				MultiplayerResponse = BillyMain.getInput();
				gameTrigger = false;

				break;

			} else {
				BillyMain.print("You like multiplayer games too?");
				MultiplayerResponse = BillyMain.getInput();
				if (gameType()) {
					BillyMain.print("That's cool. What multiplayer games do you play?");
					MultiplayerResponse = BillyMain.getInput().toLowerCase();
					for (int i = 0; i < favMultiplayerGame.length; i++) {
						if (MultiplayerResponse.indexOf(favMultiplayerGame[i]) >= 0) {
							gameTrigger = true;
							continue main;
						}
					}

					BillyMain.print("Sorry I never played that game before. Do you play any other multiplayer games?");
					MultiplayerResponse = BillyMain.getInput();
					for (int k = 0; k < deny.length; k++) {
						if (MultiplayerResponse.indexOf(deny[k]) >= 0) {
							break main;

						}

					}

					BillyMain.print("I've heard of that game somewhere before...");
					break main;

				} else {
					ariqIndex = (int) (Math.random() * multi.length);
					BillyMain.print(multi[ariqIndex]);
					inMultiplayerLoop = false;
					BillyMain.talkForever();
				}
				MultiplayerResponse = BillyMain.getInput();

				BillyMain.print("What other thing about multiplayer games do you want to talk about?");
				MultiplayerResponse = BillyMain.getInput();
				for (int k = 0; k < deny.length; k++) {
					if (MultiplayerResponse.indexOf(deny[k]) >= 0) {
						inMultiplayerLoop = false;
						BillyMain.print("I guess you do not want to talk about multiplayer games anymore.");
						BillyMain.talkForever();
					}
				}
			}

		}

		BillyMain.talkForever();
	}

	private boolean gameType() {
		if (BillyMain.findKeyword(MultiplayerResponse, "yes", 0) >= 0) {
			return true;
		}
		return false;
	}

	public boolean isTriggered(String userInput) {
		for (int i = 0; i < favMultiplayerGame.length; i++) {
			if (userInput.indexOf(favMultiplayerGame[i]) >= 0) {
				gameTrigger = true;
				return true;
			}

		}
		if (BillyMain.findKeyword(userInput, "multiplayer", 0) >= 0) {
			return true;
		}

		if (userInput.indexOf("multi") >= 0) {
			return true;
		}

		return false;
	}

}
