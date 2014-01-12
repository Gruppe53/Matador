package game;

public class Street extends Ownable {
	private int cType;
	private int houses;
	private int[] rent = new int[5];
	
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
		this.rent[0] = rent0;
		this.rent[1] = rent1;
		this.rent[2] = rent2;
		this.rent[3] = rent3;
		this.rent[4] = rent4;
		this.rent[5] = rent5;
	}
	
	public void landOnField(){
		// TODO - landOnField(Street)
	}

	public int getHouses() {
		return houses;
	}

	public void setHouses(int houses) {
		this.houses = houses;
	}

	public int getRent() {
		return rent[this.getHouses()];
	}

	public int getcType() {
		return cType;
	}
}
