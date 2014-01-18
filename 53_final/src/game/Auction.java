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
	 */
	public void runAction(){

		updater.showMessage("En auktion afholdes for: " + field.getName());

		int i = 0;

		checkTotalBiders();

		// Runs the auction
		while( i > players.length){

			// Situation with only 2 players
			if(currentBiders[i] == 1 && !anyBids && totalbiders == 1) {
				if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at byde på " + field.getName() + ", for: " + field.getPrice(), "Ja" , "Nej")){
					anyBids = true;
					changeHighestBider(i);
					//TODO give currentBiders[i] owner and buy for currentMax
				}
				else currentBiders[i] = 0;
			}

			// Situation with more than 2 players
			else if(currentBiders[i] == 1 && anyBids && totalbiders > 1) {
				if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at byde på " + field.getName() + ". Minimum bud: " + (currentMax + 50), "Ja" , "Nej")){
					currentMax = updater.getUserInteger("Hvor meget kunne de tænke dem at byde? (minimum: " + (currentMax + 50) + "):", (currentMax + 50), players[i].getAccount());
					changeHighestBider(i);
					//TODO set new currentMax and Find out how to identify person which has highest current bid ( evt. currentBiders[i] = 2)
				}
				else currentBiders[i] = 0;
			}

			if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at byde på " + field.getName() + ", for: " + field.getPrice(), "Ja" , "Nej")){
				anyBids = true;
				changeHighestBider(i);
			} 
			else {
				currentBiders[i] = 0;
			}
			checkTotalBiders();
			if(checkForWinner() == true) i = 7;
			else if (totalbiders == 0) i = 7;
			else if (i >= players.length) i = 0;
			else i++;
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
				}
			}
			return true;
		}

		else return false;
	}

	/**
	 * check TotalBiders
	 *  </p>
	 *  Set totalbiders to the amount of people bidding
	 */
	private void checkTotalBiders(){
		// Check how many players are allowed to bid (active players, and not the player that could've bought the place)
		this.totalbiders = 0;
		for(int i = 0; i<players.length;i++){
			if(!players[i].equals(player)){
				if(players[i].getStatus() >= 0){
					this.currentBiders[i] = 1;
					this.totalbiders++;
				}
			}
		}
	}

	/**
	 * change HigestBider
	 * @param player - the player (int from array) which is now the highestbider (currentBiders[player] = 2)
	 */
	private void changeHighestBider(int player) {
		for(int i = 0 ; i > players.length ; i++){
			if(currentBiders[i] == 2) currentBiders[i] = 1; 
		}
		currentBiders[player] = 2;
	}

	private void callWinner(){
		if(totalbiders == 1){
			updater.showMessage(players[actionWinner].getName() + " har vundet auktionen på " + field.getName() + " med sit bud på: " + currentMax);
			field.setOwner(players[actionWinner]);
			updater.setOwner(player.getPosition(), players[actionWinner].getName());
		}
		else if(totalbiders <= 0){
			updater.showMessage("Der var ingen bud på " + field.getName());
		}
	}
}
