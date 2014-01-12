package game;

public class Brewery extends Ownable {
	private int rent;
	
	/**
	 * Fleet Constructor
	 * @param Name - The name of the Fleet
	 * @param Rent - The Rent of the Fleet
	 */
	public Brewery(String name, int price, int rent){
		super(name, price);
		this.rent = rent;
	}
	
	public int getRent() {
		return (this.rent * owner.getBreweries() * multiplier);
	}
}
