package game;

import boundaryToMatador.GUI;

public class Parking extends Field {
	
	/**
	 * Parking Constructor
	 * @param Name - The name of the Field
	 */
	public Parking(String name){
		super(name);
	}
	
	public void landOnField(Player player){
		// TODO - Fix GUI-knowledge
		GUI.showMessage("De parkerer bilen, og spiser en hjemmepakket rugbrødsmad.");
	}
}
