package game;

public class Chance extends ChanceDeck {
	private String name;
	
	/**
	 * Chance Constructor
	 * @param Name - The name of the Field
	 */
	public Chance(String name){
		super(name);
	}
	
	/**
	 * get Name
	 * @return The name of chance field
	 */
	public String getName() {
		return this.name;
	}
}