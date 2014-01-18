package game;

public class Brewery extends Ownable {
	private int rent;
	
	/**
	 * Brewery Constructor
	 * @param Name - The name of the Brewery
	 * @param Price - The Price of the Brewery
	 */
	public Brewery(String name, int price, int rent){
		super(name, price);
		this.rent = rent;
	}
	
	public int getRent() {
		if(this.isPawned)
			return -1;
		else
			return (this.rent * owner.getBreweries() * multiplier);
	}
}
