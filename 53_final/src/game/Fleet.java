package game;

public class Fleet extends Ownable {
	private int rent;
	
	/**
	 * Fleet Constructor
	 * @param Name - The name of the Fleet
	 * @param Owner - The Player which owns the Fleet
	 * @param Rent - The Rent of the Fleet
	 */
	public Fleet(String name, int price, int rent){
		super(name, price);
		this.rent = rent;
	}
	
	public void landOnField(){
		// ( 2 ^ (Num_Fleet_Owned - 1) )* Rent
	}
	
	public int getRent() {
		return this.rent;
	}
}
