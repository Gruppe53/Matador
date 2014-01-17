package game;

public class GoToJail extends Field {
	
	/**
	 * GoToJail Constructor
	 * @param Name - The name of the Field
	 */
	public GoToJail(String Name){
		super(Name);
	}
	
	public void landOnField(Player player, Updater updater){
		player.setJailed(true);
		player.setPosition(11);
		// TODO fix GUI knowledge
		updater.position(player);
		
	}

}
