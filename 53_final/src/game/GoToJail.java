package game;

public class GoToJail extends Field {
	
	/**
	 * GoToJail Constructor
	 * @param Name - The name of the Field
	 */
	public GoToJail(String name){
		super(name);
	}
	
	/**
	 * land On Field
	 * @param player - The choice of player
	 * @param updayer - The Updater
	 * </p>
	 * Jails the player and sets player position to 11
	 */
	public void landOnField(Player player, Updater updater){
		player.setJailed(true);
		player.setPosition(11);
		updater.position(player.getPosition() ,player.getName());
	}
}
