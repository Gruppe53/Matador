package game;

public class Start extends Field {
	
	/**
	 * Start Constructor
	 * @param Name - The name of the Field
	 */
	public Start(String name){
		super(name);
	}
	
	public void landOnField(Player player, Updater updater, boolean createAuction){
		updater.showMessage("De passerer START, og modtager 4.000.");
	}
}
