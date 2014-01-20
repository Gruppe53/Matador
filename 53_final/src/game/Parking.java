package game;

public class Parking extends Field {
	
	/**
	 * Parking Constructor
	 * @param Name - The name of the Field
	 */
	public Parking(String name){
		super(name);
	}
	
	/**
	 * land On Field
	 * @param player - The choice of player
	 * @param updater - The Updater
	 * </p>
	 * Shows message on GUI that tells you have landed on a parking spot
	 */
	public void landOnField(Player player, Updater updater){
		updater.showMessage("De parkerer bilen, og spiser en hjemmepakket rugbrødsmad.");
	}
}
