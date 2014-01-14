package game;

import java.io.File;
import java.util.Random;

public class Chance extends Field {
	private ChanceDeck deck;
	
	/**
	 * Chance Constructor
	 * @param Name - The name of the Field
	 */
	public Chance(String name){
		super(name);
		
		if(name == "Create") {
			deck = new ChanceDeck();
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void landOnField(Player player) {
		// TODO
	}
}