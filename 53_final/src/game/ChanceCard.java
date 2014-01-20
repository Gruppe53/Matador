package game;

public class ChanceCard {
	private String description;
	private String type;
	private String action;
	
	/**
	 * ChanceCard Constructor
	 * @param description - description string on the constructed ChanceCard
	 * @param type - type of event (loose money, gain money or move player)
	 * @param action - amount of money to loose/gain or moves to make
	 */
	public ChanceCard(String description, String type, String action) {
		this.description = description;
		this.type = type;
		this.action = action;
	}

	/**
	 * get Description
	 * @return Description for ChanceCard
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set Description 
	 * @param description - Change description of ChanceCard to the chosen description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get Type
	 * @return Type of the ChanceCard
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * set Type
	 * @param type - set type of the ChanceCard
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * get Action
	 * @return Action of the ChanceCard
	 */
	public String getAction() {
		return action;
	}

	/**
	 * set Action
	 * @param action - set action of the ChanceCard
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
