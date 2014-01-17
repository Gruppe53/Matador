package game;

public class Parking extends Field {
	
	/**
	 * Parking Constructor
	 * @param Name - The name of the Field
	 */
	public Parking(String name){
		super(name);
	}
	
	public void landOnField(Player player, Updater updater){
		// TODO - Fix GUI-knowledge
		updater.showMessage("De parkerer bilen, og spiser en hjemmepakket rugbrødsmad.");
	}
}
