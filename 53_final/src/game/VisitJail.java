package game;

public class VisitJail extends Field {
	
	/**
	 * VisitJail Constructor
	 * @param Name - The name of the Field
	 */
	public VisitJail(String name){
		super(name);
	}
	
	public void landOnField(Player player, Updater updater, boolean createAuction){ 
		updater.showMessage("De er på besøg i fængslet,\n og vinker til de indsatte, mens De kører forbi.");
	}
}
