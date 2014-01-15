package game;

public class Fleet extends Ownable {
	private int[] rent = {500, 1000, 2000, 4000};
	
	/**
	 * Fleet Constructor
	 * @param name - The name of the Fleet
	 * @param price - The price of the Fleet
	 */
	public Fleet(String name, int price, int pawn){
		super(name, price, pawn);
	}
	
	public int getRent() {
		return rent[owner.getFleet() - 1];
	}
}
