package groupFiles;

import groupFiles.BillyMain;

public class BillyHello implements Topic {

	private boolean inHelloLoop;
	private String helloResponse;

	private int helloCount;

	//responses are constants(never change)
	private static String[] calmResponses = {"We've already said hello. Remember?", "Yes, hello to you too. Lets actually talk."};
	private static String[] angryResponses = {"Okay, seriously. This has to stop.", "You are beginning to annoy me. "+ "We've SAID HELLO"};

	public BillyHello(){
		helloCount = 0;
	}

	public void talk(){
		inHelloLoop = true;
		while(inHelloLoop){
			helloCount++;
			printResponse();
			helloResponse=BillyMain.getInput();
			if(!isTriggered(helloResponse)){
				inHelloLoop=false;
				BillyMain.talkForever();
			}
		}

	}

	private void printResponse() {
		int responseIndex =0;
		if(helloCount < 5){
			responseIndex = (int)(Math.random() * calmResponses.length);
			BillyMain.print(calmResponses[responseIndex]);
		}
		else{
			responseIndex = (int)(Math.random() * angryResponses.length);
			BillyMain.print(angryResponses[responseIndex]);
		}

	}

	public boolean isTriggered(String userInput){
		if(BillyMain.findKeyword(userInput, "hi", 0)>=0){
			return true;
		}
		if(BillyMain.findKeyword(userInput, "hello", 0)>=0){
			return true;
		}
		return false;
	}

}
