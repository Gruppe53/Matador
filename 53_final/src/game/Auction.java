package game;

import boundaryToMatador.GUI;

public class Auction {
	/**
	 * Auction Constructor </p>
	 * Created every time a Auction is announced
	 * @param player - The player which denied the purchase, and isn't allowed to make a bid
	 * @param players - The whole player Array
	 */
	public <players> Auction(Player player, Player[] players) {
		int j = 0;
		int fieldPrice = 0;
		int currentMax = 0;
		boolean anyBids = false;
		int totalbiders = 0;
		int[] currentbiders = new int[players.length];

		// Check how many players are allowed to bid (active players, and not the player that could've bought the place)
		for(int i = 0; i<players.length;i++){
			if(!players[i].equals(player)){
				if(players[i].getStatus() >= 0){
					
					totalbiders++;
				}
			}
		}
		// If there is only 2 players
		if(players.length==2) 
			for(int i = 0; i<=2; i++)
				
			if(GUI.getUserLeftButtonPressed("kunne de t&aelig;nke dem at købe denne grund til: " + fieldPrice, "Ja tak", "Nej tak")){
				
			}


		while(j <= players.length){
			//			GUI.getUserButtonPressed("Noget", "Køb", "sælg", "tag tøjet af");
			if(GUI.getUserLeftButtonPressed(players[j].getName() + ", kunne de tænke dem at byde på den pågældende grund? \nMindste bud tilladt: " + (currentMax + 50), "Ja tak", "Nej tak")){
				currentMax = GUI.getUserInteger(players[j].getName() + " What would you like to bid? \nminimum bid allowed: " + (currentMax + 50), (currentMax + 50), 1000000);	
			}



			j++;
			if(j > players.length) j = 0;
		}

		// TODO - Auction

	}
}
