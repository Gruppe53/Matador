package game;

public class Auction {
	private int currentMax;
	private boolean anyBids = false;
	private int totalbiders = 0;
	private int actionWinner; // For array list
	private int[] currentBiders;
	private Player player;
	private Player[] players;
	private Updater updater;
	private Ownable field;
	private int minExtra = 100; //Minimum for the difference in the next bid

	/**
	 * Auction Constructor </p>
	 * Created every time a Auction is announced
	 * @param player - The player which denied the purchase, and isn't allowed to make a bid
	 * @param players - The whole player Array
	 * @param field - The field the auction runs over
	 */
	public Auction(Updater updater, Player player, Player[] players, Ownable field) {

		this.field = field;
		this.currentMax = field.getPrice();
		this.currentBiders = new int[players.length];
		this.player = player;
		this.players = players;
		this.updater = updater;

	}
	/**
	 * run Action
	 * Runs the Action with the infomation given from the Auction Constructor
	 */
	public void runAction(){

		updater.showMessage("En auktion afholdes for: " + field.getName());

		int i = 0;

		startTotalBiders();
		//If there is only one possible buyer from the start (normally 2 player game)
		if (totalbiders == 1){
			while(i < players.length){
				if(currentBiders[i] == 1 && players[i].getAccount() >= currentMax){
					if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at købe " + field.getName() + ", for: " + field.getPrice(), "Ja" , "Nej")){
						anyBids = true;
						changeHighestBider(i);
						checkTotalBiders();
						checkForWinner();
					}
					else {
						currentBiders[i] = 0;
						checkTotalBiders();
						checkForWinner();
					}
				}
				else {
					currentBiders[i] = 0;
					checkTotalBiders();
					checkForWinner();
				}
				i++;
			}
		}
		//If there is more then one possible buyter from the start (3 or more active players left in game)
		if(totalbiders > 1){
			while( i < players.length){

				// Situation with more than 2 players - ask to bid on the already bidded field
				if(currentBiders[i] == 1 && anyBids && totalbiders >= 1 && players[i].getAccount() >= (currentMax + minExtra)) {
					if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at byde på " + field.getName() + ". Minimum bud: " + (currentMax + minExtra), "Ja" , "Nej")){
						currentMax = updater.getUserInteger("Hvor meget kunne de tænke dem at byde? (minimum: " + (currentMax + minExtra) + "):", (currentMax + minExtra), players[i].getAccount());
						changeHighestBider(i);
						//TODO set new currentMax and Find out how to identify person which has highest current bid ( evt. currentBiders[i] = 2)
					}
					else currentBiders[i] = 0;
				}

				//Situation with more than 2 players - ask to bid on the not already bidded field
				else if(currentBiders[i] == 1 && !anyBids && totalbiders >= 1 && players[i].getAccount() >= (currentMax)) {
					if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at byde på " + field.getName() + ", for: " + field.getPrice(), "Ja" , "Nej")){
						anyBids = true;
						changeHighestBider(i);
					}
					else {
						currentBiders[i] = 0;
					} 

				}
				else if(currentBiders[i] == 1 && players[i].getAccount() < (currentMax + minExtra)){
					currentBiders[i] = 0;
					updater.showMessage(players[i].getName() + ", de har desværre ikke nok penge i deres pengebeholdning til at kunne byde");
				}
				checkTotalBiders();
				i++;
				if (i >= players.length) i = 0;
				if(checkForWinner() == true) i = 7;
				else if (totalbiders == 0) i = 7;
			}
		}
		callWinner();
	}

	/**
	 * Check for Winner
	 * @return True if there is a winner, otherwise false
	 * </P>
	 * Check if there is only 1 totalbiders left and sets actionwinner to the number of the winner on array
	 */
	private boolean checkForWinner(){
		if (totalbiders == 1) {
			for(int i = 0 ; i < players.length ; i++){
				if(currentBiders[i] == 2){
					actionWinner = i;
					return true;
				}
			}
			return false;

		}
		//		else if (totalbiders == 0){
		//			for(int i = 0 ; i < players.length ; i++){
		//				currentBiders[i] = 0;
		//			}
		//			return true;
		//		}

		else return false;
	}

	/**
	 * check TotalBiders
	 *  </p>
	 *  Set totalbiders to the amount of people bidding, used while the auction is in progress
	 */
	private void checkTotalBiders(){
		// Check how many players are allowed to bid (active players, and not the player that could've bought the place)
		this.totalbiders = 0;
		for(int i = 0; i<players.length;i++){
			if(!players[i].equals(player)){
				if(players[i].getStatus() >= 0 && currentBiders[i] == 1){
					this.currentBiders[i] = 1;
					this.totalbiders++;
				}
				if(players[i].equals(player)){
					this.currentBiders[i] = 0;
				}
				if(players[i].getStatus() >= 0 && currentBiders[i] == 2){
					this.totalbiders++;
				}
			}
		}
	}

	/**
	 * start TotalBiders
	 * </p>
	 * Set totalbiders to the amount of people bidding, used before the auction starts 
	 */
	private void startTotalBiders(){
		this.totalbiders = 0;
		for(int i = 0; i<players.length;i++){
			if(!players[i].equals(player)){
				if(players[i].getStatus() >= 0 && currentBiders[i]!=2){
					this.currentBiders[i] = 1;
					this.totalbiders++;
				}
				if(players[i].equals(player)){
					this.currentBiders[i] = 0;
				}
				if(players[i].getStatus() >= 0 && currentBiders[i] == 2){
					this.totalbiders++;
				}
			}
		}
	}

	/**
	 * change HigestBider
	 * @param j - the player (int from array) which is now the highestbider (currentBiders[player] = 2)
	 */
	private void changeHighestBider(int j) {
		for(int i = 0 ; i < players.length ; i++){
			if(currentBiders[i] == 2) currentBiders[i] = 1; 
		}
		currentBiders[j] = 2;
	}

	/**
	 * call Winner
	 * announce who's the winner, set owner and removes money
	 * </p>
	 * If there is no Winner it will end the Auction
	 */
	private void callWinner(){
		if(totalbiders == 1){
			updater.showMessage(players[actionWinner].getName() + " har vundet auktionen på " + field.getName() + " med sit bud på: " + currentMax);
			field.setOwner(players[actionWinner]);
			updater.setOwner(player.getPosition(), players[actionWinner].getName());
			players[actionWinner].alterAccount(-currentMax);
			players[actionWinner].setAssets((int)(field.getPrice() * 0.5));
			updater.balance(players[actionWinner].getName(), players[actionWinner].getAccount());


		}
		else if(totalbiders <= 0){
			updater.showMessage("Der var ingen bud på " + field.getName());
		}
	}
}
