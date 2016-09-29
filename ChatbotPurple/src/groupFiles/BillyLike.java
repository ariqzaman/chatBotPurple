package groupFiles;

public class BillyLike implements Topic {
	
	private boolean inLikeLoop;
	private String likeResponse;

	public void talk() {
		BillyMain.print("What are some things you like?");
		inLikeLoop = true;
		while (inLikeLoop){
			likeResponse = BillyMain.getInput();
			int likePsn= BillyMain.findKeyword(likeResponse, "like", 0);
			if(likePsn >= 0){
				String thingsLiked = likeResponse.substring(likePsn+5);
				BillyMain.print("You are such an interesting person because you like "+thingsLiked);
				if(BillyMain.findKeyword(thingsLiked, "food", 0)>=0){
					inLikeLoop = false;
					BillyMain.print("So you like food?");
					BillyMain.food.talk();
				}
				else{
				inLikeLoop = false;
				BillyMain.talkForever();
				}
			}
			else
				BillyMain.print("I dont understand you ");
		}
	}
	
	public boolean isTriggered(String userInput) {
		//String[] triggers = ("school", "class", "teacher", "grades");
		//you can use a for loop to loop through a array
		if(BillyMain.findKeyword(userInput, "like", 0)>=0){
			return true;
		}
		return false;
	}
}
