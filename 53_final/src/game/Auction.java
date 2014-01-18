package game;

public class Auction {
	private int j = 0;
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
	 * @return The int value for which player on the arraylist have won the auction
	 */
	public void runAction(){

		updater.showMessage("En auktion afholdes for: " + field.getName());

		int i = 0;

		checkTotalBiders();
		if(checkForWinner()==true) i = 7;

		// Runs the auction
		while( i > players.length){

			// Ved situtation af 2 spillere
			if(currentBiders[i] == 1 && !anyBids && totalbiders == 1) {

			}


			// Situation with a Active Player 
			if(currentBiders[i] == 1 && !anyBids){
				if(updater.getUserLeftButtonPressed(players[i].getName() + " kunne de tænke dem at byde på " + field.getName() + ", for: " + field.getPrice(), "Ja" , "Nej")){
					anyBids = true;
				} 
				else {
					currentBiders[i] = 0;
				}
			}

		}
	}

	/**
	 * Check for Winner
	 * @return True if there is a winner, otherwise false
	 * </P>
	 * Check if there is only 1 totalbiders left and sets actionwinner to the number of the winner on array
	 */
	private boolean checkForWinner(){
		if(totalbiders == 1) {
			for(int i = 0; i<players.length; i++){
				if(currentBiders[i] == 1){
					actionWinner = i;
				}
			}
			return true;
		}
		else return false;
	}
	/**
	 * check TotalBiders
	 *  
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

		if(updater.getUserLeftButtonPressed("kunne de tænke dem at købe denne grund til: " + field.getPrice(), "Ja tak", "Nej tak")){
			// TODO
		}


		while(j <= players.length){
			if(updater.getUserLeftButtonPressed(players[j].getName() + ", kunne de tænke dem at byde på den pågældende grund? \nMindste bud tilladt: " + (currentMax + 50), "Ja tak", "Nej tak")){
				currentMax = updater.getUserInteger(players[j].getName() + " kunne de tænke dem at byde? \nmindste bud tilladt: " + (currentMax + 50), (currentMax + 50), 1000000);	
			}



			j++;
			if(j > players.length) j = 0;
		}

		// TODO - Auction - In Progress by Anders

	}
}
