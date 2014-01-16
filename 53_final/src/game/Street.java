package game;

import boundaryToMatador.GUI;

public class Street extends Ownable {
	private int cType;
	private int houses;
	private int housePrice;
	private int[] rent = new int[6];
	
	/**
	 * Street Constructor
	 * @param Name - The name of the street
	 * @param Price - The price for buying the street
	 * @param CType - The group of streets
	 * @param Rent0 - The rent with 0 houses on the street
	 * @param Rent1 - The rent with 1 house on the street
	 * @param Rent2 - The rent with 2 houses on the street
	 * @param Rent3 - The rent with 3 houses on the street
	 * @param Rent4 - The rent with 4 houses on the street
	 * @param Rent5 - The rent with 1 hotel on the street
	 */
	
	public Street (String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5, int housePrice) {
		super(name, price);
		this.housePrice = housePrice;
		this.cType = cType;
		this.houses = 0;
		this.rent[0] = rent0;
		this.rent[1] = rent1;
		this.rent[2] = rent2;
		this.rent[3] = rent3;
		this.rent[4] = rent4;
		this.rent[5] = rent5;
	}
	
	/**
	 * 
	 * @return The amount of houses.
	 */
	public int getHouses() {
		return houses;
	}
	
	/**
	 * set Houses
	 * @param field - The field number for which we want to buy houses for.
	 * @param houses - Sets the amount of houses for the field.
	 */
	public void setHouses(int field, int houses) {
		this.houses = houses;
		
		// TODO GUI-knowledge
		GUI.setHouses(field, houses);
	}
	
	/**
	 * set Hotel	
	 * @param field - The field number for which we want to buy hotel for
	 */
	public void setHotel(int field) {
		this.houses = 5;
		
		// TODO GUI-knowledge
		GUI.setHotel(field, true);
	}
	
	/**
	 * remove Hotel
	 * @param field - The field number for which we want to remove hotel from
	 * @param houses - The new amount of houses for the field
	 */
	public void removeHotel(int field, int houses) {
		this.houses = houses;
		
		// TODO GUI-knowledge
		GUI.setHotel(field, false);
		GUI.setHouses(field, houses);
	}
	
	/**
	 * 
	 * @return The amount of rent for the field, based on how many houses the field has.
	 */
	public int getRent() {
		return rent[this.getHouses()];
	}
	
	/**
	 * 
	 * @return The cluster for which the field is a part of
	 */
	public int getcType() {
		return this.cType;
	}
	
	public int getHousePrice() {
		return this.housePrice;
	}
}
