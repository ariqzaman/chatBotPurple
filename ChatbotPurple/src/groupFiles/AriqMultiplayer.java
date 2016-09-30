package groupFiles;

public class AriqMultiplayer implements Topic {

	@Override
	public void talk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTriggered(String userInput) {
		if(BillyMain.findKeyword(userInput, "play", 0) >= 0){
			return true;
		}
		return false;
	}

}
