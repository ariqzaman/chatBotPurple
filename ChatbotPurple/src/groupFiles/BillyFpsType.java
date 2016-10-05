package groupFiles;

import groupFiles.BillyMain;

public class BillyFpsType implements Topic{

	private boolean inFpsLoop;
	private boolean inDeviceLoop;
	private String fpsResponse;

	private static String[] consleRes = {"Eww you play on a consle", "It's 2016, pc is master race", "Consles were so 2013"};
	private static String[] favGame = {"Overwatch", "Call of Duty", "Battlefield", "Team fortress 2", "Counter Strike Global Offensive", "Doom"};

	public void talk() {
		int responseIndex =0;
		inDeviceLoop = true;
		BillyMain.print("Oh, you like shooting games? ME TOO. What device do you play on?");
		while(inDeviceLoop){
			fpsResponse = BillyMain.getInput();
			if(gameDevicePc()){
				BillyMain.print("YESSS PC MASTER RACE");
			}
			else {
				BillyMain.print(randomFromArray(consleRes));
			}
			inDeviceLoop = false;
			BillyMain.print("What other thing about shooting games do you want to talk about?");
			inFpsLoop = true;
		}
		
		while(inFpsLoop){	
			fpsResponse = BillyMain.getInput();
			int favPsn= BillyMain.findKeyword(fpsResponse, "favorite", 0);
			if(favPsn >= 0){
				String userFav = fpsResponse.substring(favPsn+17);
				BillyMain.print("Cool, so your favorite game is "+userFav);
				String botFav = randomFromArray(favGame);
				if(userFav == botFav )
					BillyMain.print("YES, my favorite game is " +botFav+ " too!!!" );
				else
					BillyMain.print("That's a good choice but, I like "+botFav+" more");
			}
			
			if(fpsResponse.indexOf("stop")>=0){
				inFpsLoop = false;
				BillyMain.talkForever();
			}
		}
	}
	
	private String randomFromArray(String[] array){
		int responseIndex = 0;
		responseIndex = (int)(Math.random() * array.length);
		return array[responseIndex];
	}

	private boolean gameDevicePc() {
		return BillyMain.findKeyword(fpsResponse, "pc", 0)>= 0;
	}

	public boolean isTriggered(String userInput) {
		if(BillyMain.findKeyword(userInput, "fps", 0)>=0){
			return true;
		}

		if(userInput.indexOf("shoot")>=0){
			return true;
		}
		return false;
	}

}
