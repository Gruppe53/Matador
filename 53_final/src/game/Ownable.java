package game;

abstract public class Ownable extends Field {
	protected Player owner;
	protected int price;
	protected int multiplier;
	protected int pawn;
	protected Updater updater;
	protected boolean isPawned = false;
	protected boolean createAuction;
	
	/**
	 * Ownable Constructor
	 * @param Name - The name of the Field
	 * @param Price - The price of the field
	 */
	public Ownable(String name, int price){
		super(name);
		this.price = price;
	}

	public void landOnField(Player player, Updater updater) {
		this.updater = updater;
		
		if (owner == null) {
			if (updater.getUserLeftButtonPressed("Vil De købe " + name + " for " + price + "?", "Ja", "Nej")) {
				if (player.getAccount() >= price) {
					owner = player;
					owner.alterAccount(-price);
					owner.setAssets((int)(price * 0.5));
					
					if(this instanceof Brewery) {
						owner.setBrewery();
					}
					if(this instanceof Fleet) {
						owner.setFleet();
					}
					
					updater.showMessage(player.getName() + " har købt og ejer nu " + name);
					updater.setOwner(player.getPosition(), player.getName());
				} else {
					updater.showMessage("De har ikke nok penge til at købe denne grund.");
				}
			}
			else {
				this.createAuction = true;
			}
		} else if (!isOwner(player)) {
			this.multiplier = player.getRollSum();
			
			if(this.getRent() == -1) {
				updater.showMessage("Grunden er pantsat, og De betaler derfor ikke leje til " + owner.getName() + ".");
			}
			else {
				updater.showMessage(player.getName() + " betaler " + this.getRent() + " til " + owner.getName() + ".");
				player.payRent(getRent(), owner);
			}
		}
	}
	
	public boolean isOwner(Player player) {
		return player == owner;
	}
	
	public int getPawn() {
		return this.pawn;
	}
	
	public void resetOwner() {
		owner = null;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	abstract int getRent();
	
	public void setOwner(Player player){
		owner = player;
	}
	
	public boolean getCreateAuction(){
		if(createAuction){
			createAuction = false;
			return !createAuction;
		}
		else return false;
	}
}
