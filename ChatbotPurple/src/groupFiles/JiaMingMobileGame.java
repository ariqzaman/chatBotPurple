package groupFiles;

public class JiaMingMobileGame implements Topic {
	static boolean inLoop;
	
	public void talk() {
		System.out.println("I like mobile games too, but why do you like mobile games?");
		inLoop=true;
		while(inLoop){
			if(getIndex("hi")>-1){
				System.out.println("sup");
			}
			else{
			System.out.println("Sorry, I do not understand what you just said.");
			}
		}
	}
	public static int getIndex(String string){
		return BillyMain.getInput().indexOf(string);
	}
	public boolean isTriggered(String userInput) {
		if(BillyMain.findKeyword(userInput, "Mobile", 0)>=0 || BillyMain.findKeyword(userInput, "Phone", 0)>=0)
		{
			return true;
		}
		return false;
	}

}
