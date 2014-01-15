package game;

import boundaryToMatador.GUI;

abstract public class Ownable extends Field {
	protected Player owner;
	protected int price;
	protected int multiplier;
	
	/**
	 * Ownable Constructor
	 * @param Name - The name of the Field
	 * @param Price - The price of the field
	 */
	public Ownable(String name, int price){
		super(name);
		this.price = price;
	}

	public void landOnField(Player player) {
		if (owner == null) {
			// TODO - Fix GUI knowledge
			if (GUI.getUserLeftButtonPressed("Vil De købe " + name + " for " + price + "?", "Ja", "Nej")) {
				if (player.getAccount() >= price) {
					owner = player;
					owner.setAssets(price);
					
					if(this instanceof Brewery) {
						owner.setBrewery();
					}
					if(this instanceof Fleet) {
						owner.setFleet();
					}
					
					// TODO - Fix GUI knowledge
					GUI.showMessage(player.getName() + " købte og ejer nu " + name);
					GUI.setOwner(player.getPosition(), player.getName());
				} else {
					// TODO - Fix GUI knowledge
					GUI.showMessage("De har ikke nok penge til at købe denne grund.");
				}
			}
		} else if (!isOwner(player)) {
			this.multiplier = player.getRollSum();
			// TODO - Fix GUI knowledge
			GUI.showMessage(player.getName() + " betaler " + getRent() + " til " + owner.getName());
			player.payRent(getRent(), owner);
		}
	}
	
	public boolean isOwner(Player player) {
		return player == owner;
	}
	
	public void resetOwner() {
		owner = null;
	}
	
	abstract int getRent();
}
