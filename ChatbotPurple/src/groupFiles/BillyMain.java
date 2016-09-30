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

		public static void main(String[] args) {
			createTopics();
			promptName();
			talkForever();

		}

		public static void promptName() {
			print("Hello, human! I am a GameBOT. What is your name?");
			// /n makes the line break
			user = input.nextLine(); //user referencing the field user
			print("Hello "+ user);
		}

		public static void talkForever() {
			inLoop = true;
			while(inLoop){ //what ever is inside the while loop is true, it keeps running
				//promptInput();
				print("Greetings, "+user+". What kinds of games do you like??");
				response = getInput();
//				if (findKeyword(response, "good", 0)>=0){
//					print("I'm so happy you're good");
//				}
				if (fps.isTriggered(response)){
					inLoop =false;//exits the loop
					fps.talk();
				}
				else if (ariq.isTriggered(response)){
					inLoop =false;//exits the loop
					ariq.talk();
				}
				else if (jiaMing.isTriggered(response)){
					inLoop =false;//exits the loop
					jiaMing.talk();
				}
				else
					print("Im sorry i dont understand you");
			}
		}

		public static int findKeyword(String searchString, String key, int startIndex) {
			String phrase = searchString.trim();
			phrase = phrase.toLowerCase();
			key = key.toLowerCase();
			int psn = phrase.indexOf(key); 
			while (psn>=0){ 
				String before=" ";
				String after = " ";
				if(psn + key.length() <phrase.length()){ 
					after = phrase.substring(psn + key.length(), psn + key.length()+1);
				}
				if(psn>0){ 
					before = phrase.substring(psn-1,psn);
				}
				if(before.compareTo("a")<0 && after.compareTo("a")<0){
					if(noNegation(phrase, psn)){
						return psn;
					}
					return -1;
				}
				psn = phrase.indexOf(key,psn+1);
			}
			
			return -1; 
		}
		
		private static boolean noNegation(String phrase, int index) {
			//check for word "NO " - 3 characters long
			//check to see if there is space for the word "NO " to be in front of index
			if(index - 3 >= 0 && phrase.substring(index -3, index).equals("no ")){
				return false;
			}
			//check for word "NOT " - 4 characters long
			if(index - 4 >= 0 && phrase.substring(index -4, index).equals("not ")){
				return false;
			}
			//check for word "NEVER  " - 6 characters long
			if(index - 6 >= 0 && phrase.substring(index -6, index).equals("never ")){
				return false;
			}
			//check for word "N'T  " - 4 characters long
			if(index - 4 >= 0 && phrase.substring(index -4, index).equals("n't ")){
				return false;
			}
			return true;
		}

		public static void promptInput() {
			print(user+" Try inputing a string");
			String userInput = input.nextLine();
			print("You typed "+userInput);

		}
		
		public static String getInput(){
			return input.nextLine();
		}

		public static void print(String s){
			String printString = "";
			//loop to count the worlds per line
			int cutOff = 35;
			while(s.length() > 0){
				String currentLine = "";
				String nextWord = "";
				//while the current line and next word are less than 35(cutoff) & there are still words to add, do the following
				while(currentLine.length()+nextWord.length()<=cutOff && s.length() > 0){
					//add next word to the line
					currentLine += nextWord;
					//remove the word added
					s = s.substring(nextWord.length());
					int endOfWord = s.indexOf(" "); //check to see if this is last word
					if(endOfWord == -1){
						endOfWord = s.length()-1;
					}
					nextWord = s.substring(0,endOfWord+1);
				}
				printString += currentLine + "\n";
			}
			System.out.println(printString);
			//		lineCount++ ;
			//		print("Line #: " + lineCount+" "+s);//don't need this line, can use print() instead
		}


		public static void createTopics() {
			input = new Scanner(System.in);
			fps = new BillyFpsType();
			ariq = new AriqMultiplayer();
			jiaMing = new JiaMingRPG();
			//initialize topics

		}

	}
