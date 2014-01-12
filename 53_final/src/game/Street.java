package game;

public class Street extends Ownable {
	private int cType;
	private int houses;
	
	private int rent0;
	private int rent1;
	private int rent2;
	private int rent3;
	private int rent4;
	private int rent5;
	
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
	public Street (String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5) {
		super(name, price);
		this.cType = cType;
		this.houses = 0;
		this.rent0 = rent0;
		this.rent1 = rent1;
		this.rent2 = rent2;
		this.rent3 = rent3;
		this.rent4 = rent4;
		this.rent5 = rent5;
	}
	
	public void LandOnField(){
		// TODO
	}
}
