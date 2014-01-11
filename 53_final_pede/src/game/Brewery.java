package game;

public class Brewery extends Ownable {
	private int Rent;
	
	/**
	 * Brewery Constructor
	 * @param Name - The name of the brewery
	 * @param Owner - The player which owns the brewery
	 * @param Price - The price for buying the brewery
	 * @param Rent - The rent of the brewery
	 */
	public Brewery(String Name, Player Owner, int Price, int Rent){
		super(Name, Owner, Price);
		this.Rent = Rent;
	}
	public void LandOnField(){

		// ( Num_Brewery_Owned * Roll_Sum * Rent )
		
	}
}
