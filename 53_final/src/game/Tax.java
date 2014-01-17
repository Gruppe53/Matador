package game;

import boundaryToMatador.GUI;

public class Tax extends Field {
	private int taxType;
	
	/**
	 * Tax Constructor
	 * @param Name - The name of the Field
	 * @param taxType - taxType = 0 : 10% or 4.000kr else taxType != 0 : 2.000kr 
	 */
	public Tax(String Name,int taxType){
		super(Name);
		this.taxType = taxType;
		
	}
	/**
	 * LandOnField 
	 * <p>
	 * @param player - The player who lands on the field
	 */
	public void landOnField(Player player, Updater updater){
		if (taxType == 0){
			if(updater.getUserLeftButtonPressed(player.getName() + ", de skal betale indkomstskat: betal 10% af deres aktiver eller 4000kr,-", "10%", "4000kr,-")){
				//10%
				updater.showMessage("De betaler " + (int)(player.getAssets() * 0.1) + "kr,- i indkomstskat");
				player.alterAccount(-((int)(player.getAssets() * 0.1)));
				updater.balance(player);
			}
			else{
				//4k
				GUI.showMessage("De betaler 4000kr,- i indkomstskat");
				player.alterAccount(-4000);
				updater.balance(player);
			}
	
		}
		else {
			//2k
			GUI.showMessage("De skal betale Ekstra ordinær statsskat: Betal 2000kr,-");
			player.alterAccount(-2000);
			updater.balance(player);
		}
	}
}
