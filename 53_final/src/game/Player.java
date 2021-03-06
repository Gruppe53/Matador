package game;

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
	private Updater updater = new Updater();
	
/**
 * Player Constructor
 * @param name - The name you would like to give the player
 * @param account - The amount you would like to set the players account to
 */
	public Player(String name, int account) {
		this.name = name;
		this.account = account;
		this.assets = 0;
		this.position = 1;
		this.breweries = 0;
		this.fleets = 0;
		this.bailoutcards = 0;
		this.status = 0;

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
			
			updater.removeCar(this.name);
			updater.showMessage(this.name + ", De er gået fallit - alle Deres grunde er solgt til banken.");
		}
		
		updater.balance(this.name, this.account);
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
	 * @param amount - Adds the amount to the players current assets amount
	 */
	public void setAssets(int amount) {
		assets += amount;
	}
	
	/**
	 * get Assets
	 * @return The players assets + his account
	 */
	public int getAssets() {
		return (assets + account);
	}

	/**
	 * pay Rent
	 * @param rent - The amount that is being transfered 
	 * @param leaser - The player which the given player has to pay
	 * @return The activity status of the given player
	 */
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

	/**
	 * set Fleet
	 * </p>
	 * add 1 to the given players fleet amount
	 */
	public void setFleet() {
		this.fleets += 1;
	}
	
	/**
	 * get Fleet
	 * @return The amount of fleets the given player has
	 */
	public int getFleet() {
		return this.fleets;
	}
	
	/**
	 * set Brewery
	 * </p>
	 * add 1 to the given players brewery amount
	 */
	public void setBrewery() {
		this.breweries += 1;
	}
	
	/**
	 * get Breweries
	 * @return The amount of breweries the given player has
	 */
	public int getBreweries() {
		return this.breweries;
	}

	/**
	 * get Rollsum
	 * @return The sum of the latest roll the given player has made
	 */
	public int getRollSum() {
		return this.rollSum;
	}
	
	/**
	 * set Rollsum
	 * @param rollSum - The given players latest roll
	 */
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
