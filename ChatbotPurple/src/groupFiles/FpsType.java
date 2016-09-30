package groupFiles;

import groupFiles.BillyMain;

public class FpsType implements Topic{
	
	private boolean inFpsLoop;
	private String fpsResponse;
	
	public void talk() {
		inFpsLoop = true;
		while(inFpsLoop){
			BillyMain.print("Oh, you like first person shooters? ME TOO.");
			fpsResponse = BillyMain.getInput();
			if(fpsResponse.indexOf("stop")>=0){
				inFpsLoop = false; //exits the school class and goes back to main class
				BillyMain.talkForever();
			}
			BillyMain.print("That's my favorite part about school too.");
		}
		
	}
	
	public boolean isTriggered(String userInput) {
		if(BillyMain.findKeyword(userInput, "fps", 0)>=0){
			return true;
		}
		
		if(BillyMain.findKeyword(userInput, "first person shooter", 0)>=0){
			return true;
		}
		return false;
	}

}
