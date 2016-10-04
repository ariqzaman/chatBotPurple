package groupFiles;

import groupFiles.BillyMain;

public class BillyFpsType implements Topic{

	private boolean inFpsLoop;
	private String fpsResponse;

	private static String[] consleRes = {"Eww you play on a consle", "It's 2016, pc is master race", "Consles were so 2013"};
	private static String[] favGame = {"Overwatch", "Call of Duty", "Battlefield", "Team fortress 2", "Counter Strike Global Offensive", "Doom"};

	public void talk() {
		int responseIndex =0;
		inFpsLoop = true;
		while(inFpsLoop){
			BillyMain.print("Oh, you like shooting games? ME TOO. What device do you play on?");
			fpsResponse = BillyMain.getInput();
			if(gameDevicePc()){
				BillyMain.print("YESSS PC MASTER RACE");
			}
			else {
				responseIndex = (int)(Math.random() * consleRes.length);
				BillyMain.print(consleRes[responseIndex]);
			}
			BillyMain.print("What other thing about shooting games do you want to talk about?");
			if(fpsResponse.indexOf("stop")>=0){
				inFpsLoop = false;
				BillyMain.talkForever();
			}
		}
	}

	private boolean gameDevicePc() {
		if(BillyMain.findKeyword(fpsResponse, "pc", 0)>=0){
			return true;
		}
		return false;
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
