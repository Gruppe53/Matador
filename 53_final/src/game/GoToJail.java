package game;

import boundaryToMatador.GUI;

public class GoToJail extends Field {
	
	/**
	 * GoToJail Constructor
	 * @param Name - The name of the Field
	 */
	public GoToJail(String Name){
		super(Name);
	}
	
	public void landOnField(Player player){
		player.setJailed(true);
		player.setPosition(11);
		// TODO fix GUI knowledge
		GUI.removeAllCars(player.getName());
		GUI.setCar(11, player.getName());
	}

}
