package groupFiles;

public class AriqMultiplayer implements Topic {

	private boolean inMultiplayerLoop;
	private String MultiplayerResponse;

	private boolean gameTrigger;

	private static String[] multi = { "", "", "" };
	private static String[] favMultiplayerGame = { "Overwatch", "League of legends", "Dota", "TERA",
			"Counter Strike Global Offensive" };
	private static String[] deny = { "don't", "no", "not" };

	public void talk() {
		int ariqIndex = 0;
		inMultiplayerLoop = true;
main:
		while (inMultiplayerLoop) {
			if (gameTrigger) {
				BillyMain.print("That's a cool multi-player game");
				MultiplayerResponse = BillyMain.getInput();
				
				
				break;
				
			} else {
				BillyMain.print("You like multiplayer games too?");
				MultiplayerResponse = BillyMain.getInput();
				if (gameType()) {
					BillyMain.print("That's cool. What multiplayer games do you play?");
					MultiplayerResponse = BillyMain.getInput();
					for (int i = 0; i < favMultiplayerGame.length; i++) {
						if(MultiplayerResponse.indexOf(favMultiplayerGame[i]) >= 0){
							gameTrigger = true;
							continue main;
						}
					}

				} else {
					ariqIndex = (int) (Math.random() * multi.length);
					BillyMain.print(multi[ariqIndex]);
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
