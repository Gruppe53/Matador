package game;

import boundaryToMatador.GUI;

abstract public class Ownable extends Field {
	protected Player owner;
	protected int price;
	protected int multiplier;
	
	/**
	 * Ownable Constructor
	 * @param Name - The name of the Field
	 * @param Owner - The Owner of the field (No Owner = Null)
	 */
	public Ownable(String name, int price){
		super(name);
		this.price = price;
	}

	public void landOnField(Player player) {
		if (owner == null) {
			// TODO - Fix GUI knowledge
			if (GUI.getUserLeftButtonPressed("Do you want to buy " + name + " for " + price + "?", "Yes", "No")) {
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
					GUI.showMessage(player.getName() + " bought and now owns " + name);
					GUI.setOwner(player.getPosition(), player.getName());
				} else {
					// TODO - Fix GUI knowledge
					GUI.showMessage("You haven't got enough money to buy the property.");
				}
			}
		} else if (!isOwner(player)) {
			this.multiplier = owner.getBreweries();
			// TODO - Fix GUI knowledge
			GUI.showMessage(player.getName() + " pays " + getRent() + " to " + owner.getName());
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
