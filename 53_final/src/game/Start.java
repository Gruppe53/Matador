package game;

import boundaryToMatador.GUI;

public class Start extends Field {
	
	/**
	 * Start Constructor
	 * @param Name - The name of the Field
	 */
	public Start(String name){
		super(name);
	}
	
	public void landOnField(Player player){
		// TODO - GUI knowledge
		GUI.showMessage("Du passerer START, du modtager 4.000.");
	}
}
