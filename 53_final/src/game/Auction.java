package game;

import boundaryToMatador.GUI;

public class Auction {
	/**
	 * Auction Constructor <\p>
	 * Created every time a Auction is announced
	 * @param <players>
	 * @param player - The player which denied the purchase, and isn't allowed to make a bid
	 * @param players - The whole player Array
	 */
	public <players> Auction(Player player, Player[] players) {
		int i = 0;
		int currentMax = 0;
		boolean anyBids = false;
		
		while(i < players.length){
			GUI.getUserInteger(players[i].getName() + "", (currentMax + 50), 1000000);
			
			
			i++;
		}
		
		// TODO - Auction
		
	}
}
