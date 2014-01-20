package game;

public class VisitJail extends Field {
	
	/**
	 * VisitJail Constructor
	 * @param Name - The name of the Field
	 */
	public VisitJail(String name){
		super(name);
	}
	
	/**
	 * land On Field
	 * @param Player - The choice of player
	 * @param updater - The Updater
	 * </p>
	 * show message in GUI that you have visited the jail
	 */
	public void landOnField(Player player, Updater updater){ 
		updater.showMessage("De er på besøg i fængslet,\n og vinker til de indsatte, mens De kører forbi.");
	}
}
