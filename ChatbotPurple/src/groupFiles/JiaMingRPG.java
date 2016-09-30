package groupFiles;

public class JiaMingRPG implements Topic {

	public void talk() {

	}

	public boolean isTriggered(String userInput) {
		if(BillyMain.findKeyword(userInput, "RPG", 0)>=0)
		{
			return true;dasdasdasd
		}
		return false;
	}

}
