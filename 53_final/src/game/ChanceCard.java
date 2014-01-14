package game;

public class ChanceCard {
	private String description;
	private String type;
	private String action;
	
	public ChanceCard(String description, String type, String action) {
		this.description = description;
		this.type = type;
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
