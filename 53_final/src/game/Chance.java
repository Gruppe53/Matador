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
	
	public String getName() {
		return this.name;
	}
}