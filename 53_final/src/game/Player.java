package game;

import boundaryToMatador.GUI;

public class Player {
	private String name;
	private int status;
	private int position;
	private int account;
	private int assets;
	private int fleets;
	private int breweries;
	private int rollSum;
	private int bailoutcards;
	private boolean jailed;
	private int houses;
	private int hotels;
	
/**
 * Player Constructor
 * @param name - The name you would like to give the player
 * @param account - The amount you would like to set the players account to
 */
	public Player(String name, int account) {
		this.name = name;
		this.account = account;
		this.assets = account;
		this.position = 1;
		this.fleets = 0;
		this.bailoutcards = 0;

	}
	/**
	 * get Name
	 * @return The name of the player
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * get Status
	 * @return The activity status of the player (0 = active , -1 = inactive)
	 */
	public int getStatus() {
		return this.status;
	}
	/**
	 * get Position
	 * @return The position on the board of the player
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * set Position
	 * @param position - Set the position on the board of the player
	 */
	public void setPosition(int position) {
		this.position = position; 
	}
	/**
	 * set Status 
	 * @param status - Set the activity status of the player (0 = active , -1 = inactive)
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * alter Account
	 * @param amount - The amount you would like to add to the players account, if possible. 
	 * </p>
	 * If impossible account set to 0, and player activity status is set to inactive.
	 */
	public void alterAccount(int amount) {
		if ((this.account + amount) >= 0) {

			this.account += amount;
		}

		else {
			this.account = 0;
			setStatus(-1);
			
			// TODO - Fix GUI knowledge
			GUI.removeAllCars(this.name);
			GUI.showMessage(this.name + ", du er g\u00E5et fallit - alle dine grunde er solgt til banken.");
		}
		
		// TODO - Fix GUI knowledge
		GUI.setBalance(this.name, account);
	}
	/**
	 * get Account
	 * @return The amount on the players account
	 */
	public int getAccount() {
		return this.account;
	}
	
	/**
	 * set Assets
	 * @param amount The amount that the player has bought for, adding the value to the total assets and subtracting from account. This value has to be positive.
	 */
	public void setAssets(int amount) {
		alterAccount(-amount);
		assets += amount;
	}
	
	public int getAssets() {
		return assets;
	}

	public int payRent(int rent, Player leaser) {
		int amount;
		
		if (rent > this.account) {
			amount = this.account;
			setStatus(-1);
		}
		else {
			amount = rent;
			alterAccount(-amount);
		}
		
		leaser.alterAccount(amount);
		
		return this.status;
	}

	public void setFleet() {
		this.fleets += 1;
	}
	
	public int getFleet() {
		return this.fleets;
	}
	
	public void setBrewery() {
		this.breweries += 1;
	}
	
	public int getBreweries() {
		return this.breweries;
	}

	public int getRollSum() {
		return this.rollSum;
	}
	
	public void setRollSum(int rollSum) {
		this.rollSum = rollSum;
	}
	/**
	 * set Jailed
	 * @param jailed - is the player jailed? True = yes , false = no
	 */
	public void setJailed(boolean jailed){
		this.jailed = jailed;
	}
	
	/**
	 * get Jailed
	 * @return The statement of if player is in jail. True = yes, false = no
	 */
	public boolean getJailed(){
		return jailed;
	}
	
	/**
	 * set Bail get out cards
	 * @param i - the increase of bailoutcards.
	 */
	public void setBailoutcards(int i){
		this.bailoutcards += i;
	}
	
	/**
	 * get Bail get out cards
	 * @return the amount of bailoutcards the given player has
	 */
	public int getBailoutcards(){
		return this.bailoutcards;
	}
	
	/**
	 * get Houses
	 * @return The amount of houses the player own across the board
	 */
	public int getHouses() {
		return houses;
	}
	
	/**
	 * set Houses
	 * @param houses Set the total amount of houses the player owns
	 */
	public void setHouses(int houses) {
		this.houses = houses;
	}
	
	/**
	 * get Hotels
	 * @return The amount of hotels the player own across the board
	 */
	public int getHotels() {
		return hotels;
	}
	
	/**
	 * set Hotels
	 * @param hotels Set the total amount of hotels the player owns
	 */
	public void setHotels(int hotels) {
		this.hotels = hotels;
	}
}
