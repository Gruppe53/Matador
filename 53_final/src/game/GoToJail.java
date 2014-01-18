package game;

public class GoToJail extends Field {
	
	/**
	 * GoToJail Constructor
	 * @param Name - The name of the Field
	 */
	public GoToJail(String name){
		super(name);
	}
	
	public void landOnField(Player player, Updater updater){
		player.setJailed(true);
		player.setPosition(11);
		updater.position(player);
	}
}
