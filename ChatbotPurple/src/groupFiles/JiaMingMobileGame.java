package groupFiles;
//fun,convenient,no,yes
//what other genre of games do you play?
//cursing, hate topic
public class JiaMingMobileGame implements Topic {
	static boolean inLoop; //each loop is made for each different path that the user has chosen themself based on their response
	static boolean inLoop2;
	static boolean inLoop3;
	static boolean inLoop4;
	static boolean inLoop5;
	static String response;
	static int rudeCounter=0; //if rudeCounter>1, then the program will shut down
	
	
	static String[] returnToMyQuestion={"What do you like most about mobile games?","What makes mobile games better than others?",
			"What about gaming on laptop? They are pretty convenient too.","Do you like Nintendo DS too?","What about PC games?","What other genres do you like?",
			"What's your favorite mobile game?","What genre of PC games like FPS or Multiplayer?","Which Nintendo DS game is your favorite?","Which PC game do you like the best?"};
	// this array allows the bot to say the question again after it is interrupted by a question from the user
	public void talk() {
		System.out.println("YES! Mobile games are the best! What do you like most about mobile games?");
		inLoop=true;
		while(inLoop){
			response=BillyMain.getInput();
			checkString(0);
			if(BillyMain.findKeyword(response, "fun", 0)>-1 || BillyMain.findKeyword(response, "exciting", 0)>-1 || BillyMain.findKeyword(response, "good", 0)>-1 || BillyMain.findKeyword(response, "cool", 0)>-1){
				System.out.println("All video games are like that! But what makes mobile games better than others?");
				inLoop2=true;
				while(inLoop2){
					response=BillyMain.getInput();
					checkString(1);
					if(BillyMain.findKeyword(response, "travel", 0)>-1 || BillyMain.findKeyword(response, "move", 0)>-1 || BillyMain.findKeyword(response, "convenient", 0)>-1 || BillyMain.findKeyword(response, "handy", 0)>-1 || BillyMain.findKeyword(response, "comfortable", 0)>-1){ //predicting 
						System.out.println("Yeah, they are pretty convenient, you can play whereever you want. What about gaming on laptop? They are pretty convenient too.");
						inLoop3=true;
						while(inLoop3){
						response=BillyMain.getInput();
						checkString(2);
						if(BillyMain.findKeyword(response, "not as", 0)>-1 || BillyMain.findKeyword(response, "not the same", 0)>-1 || BillyMain.findKeyword(response, "boring", 0)>-1){ 
							// hates laptop
							System.out.println("Aww, that's too bad, what's your favorite mobile game?");
							response=BillyMain.getInput();
							checkString(6);
							System.out.println("Nice! My favorite is Crusader's Quest.");
							replyToHateTopic();	// asks for another genre of game to talk about
						}
						if(BillyMain.findKeyword(response, "good", 0)>-1 || BillyMain.findKeyword(response, "same", 0)>-1 || BillyMain.findKeyword(response, "convenient", 0)>-1){ 
							// enjoy laptop
							System.out.println("What genre of PC games do you like? (Multiplayer, FPS and etc.)");
							response=BillyMain.getInput();
							checkString(7);
							replyToHateTopic();	// asks for another genre of game to talk about
						}
						else
							System.out.println("I don't relly understand what you just said. Please answser me correctly.");
						}
					}
					else if(BillyMain.findKeyword(response, "hand", 0)>-1 || BillyMain.findKeyword(response, "hold", 0)>-1){ //predicting
						System.out.println("You like playing games with your hands, huh? I can assume you like the Nintendo DS too?");
						inLoop4=true;
						while(inLoop4){
						response=BillyMain.getInput();
						checkString(3);
						if(BillyMain.findKeyword(response, "no", 0)>-1 || BillyMain.findKeyword(response, "nah", 0)>-1 || BillyMain.findKeyword(response, "nope", 0)>-1 || BillyMain.findKeyword(response, "don", 0)>-1){
							// hates nintendo ds
							System.out.println("Aww, that's too bad, what's your favorite mobile game?");
							response=BillyMain.getInput();
							checkString(6);
							System.out.println("Nice! My favorite is Crusader's Quest.");
							replyToHateTopic();	// asks for another genre of game to talk about
						}
						if(BillyMain.findKeyword(response, "yes", 0)>-1 || BillyMain.findKeyword(response, "yeah", 0)>-1 || BillyMain.findKeyword(response, " i like", 0)>-1 || BillyMain.findKeyword(response, "same", 0)>-1){
							// likes nintendo ds
							System.out.println("What's your favorite game on the Nintendo DS? My favorites are the Pokemon franchises.");
							response=BillyMain.getInput();
							checkString(8);
							System.out.println("That game was one of my favorites!");
							replyToHateTopic();	// asks for another genre of game to talk about
						}
						else
							System.out.println("I don't relly understand what you just said. Please answser me correctly.");
						}
					}
					else if(BillyMain.findKeyword(response, "cheap", 0)>-1 || BillyMain.findKeyword(response, "free", 0)>-1){ //predicting
						System.out.println("True, most of the games on the APP stores are free. But there is also free PC games. Are those good or bad?");
						inLoop5=true;
						while(inLoop5){
						response=BillyMain.getInput();
						checkString(4);
						if(BillyMain.findKeyword(response, "bad", 0)>-1 || BillyMain.findKeyword(response, "horrible", 0)>-1 || BillyMain.findKeyword(response, "don", 0)>-1 || BillyMain.findKeyword(response, "hate", 0)>-1){
							// does not enjoy pc gaming
							System.out.println("Aww, that's too bad, what's your favorite mobile game?");
							response=BillyMain.getInput();
							checkString(6);
							System.out.println("Nice! My favorite is Crusader's Quest.");
							replyToHateTopic();	// asks for another genre of game to talk about
						}
						if(BillyMain.findKeyword(response, "good", 0)>-1 || BillyMain.findKeyword(response, "amazing", 0)>-1 || BillyMain.findKeyword(response, "fun", 0)>-1 || BillyMain.findKeyword(response, "too", 0)>-1){
							// enjoys pc gaming
							System.out.println("That's awesome, my favorite PC game is League of Legends, what is yours?");
							response=BillyMain.getInput();
							checkString(9);
							System.out.println("My friends like that game alot.");
							replyToHateTopic();	// asks for another genre of game to talk about
						}
						else // each loop has a "I don't understand you line", then it would return back to the beginning of the loop and ask the user for another input
							System.out.println("I don't relly understand what you just said. Please answser me correctly.");
					}	
					}
					else
						System.out.println("I don't relly understand what you just said. Please answser me correctly.");
				}
			}
			else
				System.out.println("I don't really understand what you just said. Please answser me correctly.");
			}
		}
	
	private static void checkIfAskBotQuestion(int whereWeLeftOff){ // respond to possible questions asked by user
		if(response.indexOf("?")>-1){ // have to use indexOf because no one puts a space before putting a question mark (ex. are you a bot ? vs are you a bot?)
			if(BillyMain.findKeyword(response, "what", 0)>-1 && BillyMain.findKeyword(response, "you like", 0)>-1 && BillyMain.findKeyword(response, "mobile", 0)>-1 || BillyMain.findKeyword(response, "phone", 0)>-1){
				String[] ans={"I like touching the screen with my fingers.","I like it because there are more interactions."};
				System.out.println(ans[(int) (Math.random()*1)]);
			}
			else if(BillyMain.findKeyword(response, "ds", 0)>-1 && BillyMain.findKeyword(response, "you like", 0)>-1){
				String[] ans={"It's pretty fun but theres not enough games.","I liked the pokemon games on the DS."};
				System.out.println(ans[(int) (Math.random()*1)]);
			}
			else if(BillyMain.findKeyword(response, "favorite", 0)>-1 && BillyMain.findKeyword(response, "game", 0)>-1 || BillyMain.findKeyword(response, "games", 0)>-1){
				System.out.println("My favorite mobile game is Crusader's Quest, I have a lot of fun playing it.");
			}
			else if(BillyMain.findKeyword(response, "other genre", 0)>-1 || BillyMain.findKeyword(response, "other genres", 0)>-1 || BillyMain.findKeyword(response, "play", 0)>-1){
				System.out.println("Other than mobile games, I only like to play PC games.");
			}
			String[] returnResponse = {"Now back to where we left off.","I wanna know what you think.","That's cool."};
			System.out.println(returnResponse[(int)(Math.random()*2)]);
			System.out.println(returnToMyQuestion[whereWeLeftOff]);
			response=BillyMain.getInput();
			checkString(whereWeLeftOff);
		}
	}
	private static void checkString(int whereWeLeftOff){ // checks if the person is cursing or wants to change topic or if they are asking a question
			if(determineIfCursing()){
				replyToCurse(whereWeLeftOff);
			}
			else if(determineIfHateTopic()){
				System.out.println("Well then... Let's talk about something else shall we?");
				replyToHateTopic();
			}
			checkIfAskBotQuestion(whereWeLeftOff);
		}
	private static boolean determineIfHateTopic(){ // determine if they hate the mobile game topic / change topic if they do hate it
		if(BillyMain.findKeyword(response, "stop", 0)>-1 || BillyMain.findKeyword(response, "don't like mobile", 0)>-1 || BillyMain.findKeyword(response, "do not like mobile", 0)>-1 || BillyMain.findKeyword(response, "hate mobile", 0)>-1 || BillyMain.findKeyword(response, "hate them", 0)>-1 ||BillyMain.findKeyword(response, "dont like mobile", 0)>-1 || BillyMain.findKeyword(response, "something else", 0)>-1 || BillyMain.findKeyword(response, "another genre", 0)>-1 || BillyMain.findKeyword(response, "different genre", 0)>-1 || BillyMain.findKeyword(response, "another topic", 0)>-1 || BillyMain.findKeyword(response, "different topic", 0)>-1){
			return true;
		}
		else{
			return false;
		}
	}
	private static void replyToHateTopic(){ // respond to them wanting to change topic
			System.out.println("What other genre of games do you like? :D");
			boolean topicLoop=true;
			while(topicLoop){
			response=BillyMain.getInput();
			if (BillyMain.fps.isTriggered(response)){
				inLoop =false;
				topicLoop=false;
				BillyMain.fps.talk();
			}
			if (BillyMain.kev.isTriggered(response)){
				inLoop =false;
				topicLoop=false;
				BillyMain.kev.talk();
			}
			if (BillyMain.ariq.isTriggered(response)){
				inLoop =false;
				topicLoop=false;
				BillyMain.ariq.talk();
			}
			if (BillyMain.findKeyword(response,"mobile",0)>-1 || BillyMain.findKeyword(response,"phone",0)>-1){
				System.out.println("Nope, choose another topic to talk about, you said you hated mobile games.");
			}
			else{
				System.out.println("I'm sorry I dont understand you.");
			}
		}
	}
	private static boolean determineIfCursing(){ // determine if the person is cursing
		String[] rudeWords= {"fuck","fucking","fking","suck my dick","smd","suckmydick","fuckyou","bitch","stupid","cunt","faggot","gay","anus","ass","whore"};
		for(int i=0;i<rudeWords.length;i++){
			if(BillyMain.findKeyword(response, rudeWords[i], 0)>-1){
				return true;
			}
		}
		return false;
	}
	private static void replyToCurse(int whereWeLeftOff){ // respond to them cursing
		String[] rudeReply= {"Don't curse at me again, okay?","Please apologize for using bad words!"};
		boolean rudeLoop = determineIfCursing();
		if(rudeLoop){
			if(rudeCounter==1){
				System.out.println("I don't talk to people that speak such foul language. Bye.");
				System.exit(0);
			}
			else{
				System.out.println(rudeReply[(int)(Math.random()*1)]);
				response=BillyMain.getInput();
				if(BillyMain.findKeyword(response, "okay", 0)>-1 || BillyMain.findKeyword(response, "ok", 0)>-1 || BillyMain.findKeyword(response, "sorry", 0)>-1 || BillyMain.findKeyword(response, "yes", 0)>-1){
						System.out.println("Thank you, if you curse again I won't talk to you anymore! Back to where we left off, ");
						System.out.println(returnToMyQuestion[whereWeLeftOff]);
					}
				else{
						System.out.println("Don't then, if you curse one more time, I will stop talking to you. Back to where we left off, ");
						System.out.println(returnToMyQuestion[whereWeLeftOff]);
					}
					rudeCounter++;
					rudeLoop=false;
					response=BillyMain.getInput();
					checkString(whereWeLeftOff);
			}
		}
	}
	
	public boolean isTriggered(String userInput) { // initialize the JiaMingMobileGame
		if(BillyMain.findKeyword(userInput, "Mobile", 0)>=0 || BillyMain.findKeyword(userInput, "Phone", 0)>=0)
		{
			return true;
		}
		return false;
	}

}
