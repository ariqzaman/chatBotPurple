package groupFiles;

import java.util.Arrays;

import groupFiles.BillyMain;

public class BillyFpsType implements Topic{

	private boolean inFavLoop;
	private boolean inMainLoop;
	private boolean inStartLoop;
	private String fpsResponse;

	private static String[] consleRes = {"Eww you play on a consle", "It's 2016, pc is master race", "Consles were so 2013"};
	private static String[] consleList = {"consle","playstation","xbox","wii"};
	private static String[] favGame = {"overwatch", "call of duty", "battlefield", "team fortress 2", "counter strike global offensive", "doom"};
	private static String[] pcRes = {"YES PC MASTER RACE", "I'm glad you choose a pc over a consle", "I agree a pc is much better than a consle"};

	public void talk() {
		BillyMain.print("Oh, you like shooting games? ME TOO. What device do you play on?");
		inStartLoop = true;
		while(inStartLoop){
			fpsResponse = BillyMain.getInput();
			if(gameDevicePc()){
				BillyMain.print(randomFromArray(pcRes));
				inStartLoop = false;
				inMainLoop = true;
			}
			else if(Arrays.asList(consleList).contains(fpsResponse)){
				BillyMain.print(randomFromArray(consleRes));
				inStartLoop = false;
				inMainLoop = true;
			}
			else if(BillyMain.findKeyword(fpsResponse, "stop", 0)>= 0){
				exitLoop(inStartLoop);
			}
			else{
				BillyMain.print("Im sorry, I'm not that advanced yet, I don't understand what you're saying :(");
			}
		}
		
		while(inMainLoop){
			BillyMain.print("exit");
			BillyMain.print("What other thing about shooting games do you want to talk about?");
			fpsResponse = BillyMain.getInput();	
			if(BillyMain.findKeyword(fpsResponse, "favorite", 0)>= 0){
				inFavLoop = true;
				inMainLoop=false;
				BillyMain.print("Okay lets talk about your favorite game");
			}
			else if(BillyMain.findKeyword(fpsResponse, "stop", 0)>= 0){
				exitLoop(inMainLoop);
			}
			else if (BillyMain.findKeyword(fpsResponse, "nothing", 0)>= 0){
				BillyMain.print("Okay then");
				exitLoop(inMainLoop);
			}
			else{
				otherTopic(fpsResponse);
			}
		}
		
		while(inFavLoop){
			fpsResponse = BillyMain.getInput();
			int favPsn= BillyMain.findKeyword(fpsResponse, "favorite", 0);
			if(favPsn >= 0){
				String userFav = fpsResponse.substring(favPsn+17);
				BillyMain.print("Cool, so your favorite game is "+userFav);
				String botFav = randomFromArray(favGame);
				if(userFav.equalsIgnoreCase(botFav))
					BillyMain.print("YES, my favorite game is " +botFav+ " too!!!" );
				else
					BillyMain.print("That's a good choice but, I like "+botFav+" more");
				inFavLoop=false;
				inMainLoop=true;
			}
			else if(BillyMain.findKeyword(fpsResponse, "stop", 0)>= 0){
				exitLoop(inFavLoop);
			}
			else{
				BillyMain.print("Im sorry, I'm not that advanced yet, I don't understand what you're saying :(");
			}
		}
//		else if (BillyMain.findKeyword(fpsResponse, "play", 0)>= 0){
//			BillyMain.print("Seems like you want to talk about something else");
//			BillyMain.fps.talk();
//		}
//		else if (BillyMain.findKeyword(fpsResponse, "moblie", 0)>= 0){
//			BillyMain.print("Seems like you want to talk about something else");
//			BillyMain.fps.talk();
//		}
//		else if (BillyMain.findKeyword(fpsResponse, "multi", 0)>= 0){
//			BillyMain.print("Seems like you want to talk about something else");
//			BillyMain.fps.talk();
//		}
	}
	
	private void otherTopic(String response){
		String diffStrg ="Seems like you want to talk about ";
		if (BillyMain.kev.isTriggered(response)) {
			BillyMain.print("Looks like you want to play a game. Let's play Rock Paper Scissors!");
			inMainLoop = false;
			BillyMain.kev.talk();
		} 
		if (BillyMain.ariq.isTriggered(response)) {
			BillyMain.print(diffStrg+"multiplayer games");
			inMainLoop = false;
			BillyMain.ariq.talk();
		} 
		if (BillyMain.jiaMing.isTriggered(response)) {
			BillyMain.print(diffStrg+"moblie games");
			inMainLoop = false;
			BillyMain.jiaMing.talk();
		}
		else
			BillyMain.print("Im sorry, I'm not that advanced yet, I don't understand what you're saying :(");
	}

	private void exitLoop(boolean currentLoop){
		currentLoop = false;
		BillyMain.talkForever();
	}

	private String randomFromArray(String[] array){
		int responseIndex = 0;
		responseIndex = (int)(Math.random() * array.length);
		return array[responseIndex];
	}

	private boolean gameDevicePc() {
		return (BillyMain.findKeyword(fpsResponse, "pc", 0)>= 0) || (BillyMain.findKeyword(fpsResponse, "computer", 0)>= 0);
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
