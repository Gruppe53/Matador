package game;

public class Fleet extends Ownable {
	private int[] rent = {500, 1000, 2000, 4000};
	
	/**
	 * Fleet Constructor
	 * @param name - The name of the Fleet
	 * @param price - The price of the Fleet
	 */
	public Fleet(String name, int price){
		super(name, price);
	}
	
	/**
	 * get Rent
	 * @return returns rent based on the amount of owners fleets
	 * </p>
	 * except if fleets is pawned, than returns -1
	 */
	public int getRent() {
		if(this.isPawned)
			return -1;
		else
			return rent[owner.getFleet() - 1];
	}
}
