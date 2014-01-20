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

	/**
	 * land On Field
	 * @param player - The choice of player
	 * @param updater - The Updater
	 * </p>
	 * Runs functions for buying if no owners, else makes transfer to owner from player
	 */
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
					this.createAuction = true;
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
	
	/**
	 * is Owner?
	 * @param player - The choice of player
	 * @return true if the chosen player is owner, else false.
	 */
	public boolean isOwner(Player player) {
		return player == owner;
	}
	
	/**
	 * get Pawn
	 * @return int value for pawned or not
	 */
	public int getPawn() {
		return this.pawn;
	}
	
	/**
	 * reset Owner
	 * </p>
	 * sets owner of Ownable to null
	 */
	public void resetOwner() {
		owner = null;
	}
	
	/**
	 * get Price
	 * @return buy price on Ownable
	 */
	public int getPrice(){
		return this.price;
	}
	
	/**
	 * get Rent
	 * @return rent on Ownable
	 */
	abstract int getRent();
	
	/**
	 * set Owner
	 * @param player - The choice of player
	 * </p>
	 * sets owner to the chosen player
	 */
	public void setOwner(Player player){
		owner = player;
	}
	
	/**
	 * get CreateAuction
	 * @return true if a Auction needs to be made, else false
	 */
	public boolean getCreateAuction(){
		if(createAuction){
			createAuction = false;
			return !createAuction;
		}
		else return false;
	}
}
