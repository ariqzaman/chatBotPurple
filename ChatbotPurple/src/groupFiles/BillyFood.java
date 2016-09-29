package groupFiles;

public class BillyFood implements Topic {
	
	private boolean inFoodLoop;
	private String foodResponse;
	
	private static String[] healthyFoods = {"apple", "banana", "broccoli"};
	private static String[] unhealthyFoods = {"pizza", "chesseburger", "hamburger", "icecream"};
	private static String[] unhealthy = {"That's not healthy, you should eat less of it"};
	private static String[] healthy = {"That's very healthy, you should eat more of it"};

	public void talk() {
		BillyMain.print("What are some of your favorite foods?");
		inFoodLoop = true;
		while (inFoodLoop){
			foodResponse = BillyMain.getInput();
			if(BillyMain.findKeyword(foodResponse, "food", 0) >=0){
				
			}
		}
	}
	
	public boolean isTriggered(String userInput) {
		if(BillyMain.findKeyword(userInput, "food", 0)>=0){
			return true;
		}
		return false;
	}

}
