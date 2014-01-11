package game;

public class Street extends Ownable {
	private int CType;
	private int Houses;
	
	private int Rent0;
	private int Rent1;
	private int Rent2;
	private int Rent3;
	private int Rent4;
	private int Rent5;
	
	/**
	 * Street Constructor
	 * @param Name - The name of the street
	 * @param Owner - The Player which owns the street
	 * @param Price - The price for buying the street
	 * @param CType - The group of streets
	 * @param Houses - The current number of houses on the street
	 * @param Rent0 - The rent with 0 houses on the street
	 * @param Rent1 - The rent with 1 house on the street
	 * @param Rent2 - The rent with 2 houses on the street
	 * @param Rent3 - The rent with 3 houses on the street
	 * @param Rent4 - The rent with 4 houses on the street
	 * @param Rent5 - The rent with 1 hotel on the street
	 */
	public Street (String Name, Player Owner, int Price, int CType, int Houses, int Rent0, int Rent1, int Rent2, int Rent3, int Rent4, int Rent5){
		super(Name, Owner, Price);
		this.CType = CType;
		this.Houses = Houses;
		this.Rent0 = Rent0;
		this.Rent1 = Rent1;
		this.Rent2 = Rent2;
		this.Rent3 = Rent3;
		this.Rent4 = Rent4;
		this.Rent5 = Rent5;
	}
	
	public void LandOnField(){

	}
}
