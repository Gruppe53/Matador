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
	
	/**
	 * get Rent
	 * @return returns rent based on the amount of owners breweries and players roll
	 * </p>
	 * except if brewery is pawned, than returns -1
	 */
	public int getRent() {
		if(this.isPawned)
			return -1;
		else
			return (this.rent * owner.getBreweries() * multiplier);
	}
}
