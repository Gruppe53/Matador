package game;

public class Fleet extends Ownable {
	private int Rent;
	
	/**
	 * Fleet Constructor
	 * @param Name - The name of the Fleet
	 * @param Owner - The Player which owns the Fleet
	 * @param Rent - The Rent of the Fleet
	 */
	public Fleet(String Name, int Price, int Rent){
		super(Name, Price);
		this.Rent = Rent;
	}
	
	public void LandOnField(){
		
		// ( 2 ^ (Num_Fleet_Owned - 1) )* Rent

	}
}
