package game;

public class Start extends Field {
	
	/**
	 * Start Constructor
	 * @param Name - The name of the Field
	 */
	public Start(String name){
		super(name);
	}
	
	public void landOnField(Player player, Updater updater){
		updater.showMessage("De lander på START, og modtager 4.000.");
		player.alterAccount(4000);
		updater.balance(player.getName(), player.getAccount());
	}
}
