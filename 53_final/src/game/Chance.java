package game;

import java.io.File;

public class Chance extends Field {
	private ChanceCreate cards;
	private int count;
	
	// FINALS
	private final File file = new File("materials/chance.txt");
	
	/**
	 * Chance Constructor
	 * @param Name - The name of the Field
	 */
	public Chance(String name){
		super(name);
		
		cards = new ChanceCreate(file);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void landOnField(Player player) {
		// TODO
	}
}