package game;

import java.io.File;

public class Chance extends Field {
	private ChanceDeck cards;
	private int count;
	private boolean noDeck = true;
	
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
	
	public void landOnField(Player player) {
		// TODO
	}
}