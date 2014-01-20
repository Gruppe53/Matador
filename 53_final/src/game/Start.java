package game;

public class Start extends Field {
	
	/**
	 * Start Constructor
	 * @param Name - The name of the Field
	 */
	public Start(String name){
		super(name);
	}
	
	/**
	 * land On Field
	 * @param player - The choice of player
	 * @param updater - The Updater
	 * </p>
	 * show message on GUI that you have landed on start and will recieve 4000. transfer 4000 to the chosen players account
	 */
	public void landOnField(Player player, Updater updater){
		updater.showMessage("De lander på START, og modtager 4.000.");
		player.alterAccount(4000);
		updater.balance(player.getName(), player.getAccount());
	}
}
