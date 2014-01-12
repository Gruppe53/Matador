package game;

import java.io.File;

public class Chance extends Field {
	private ChanceCreate cards;
	private int count;
	
	// FINALS
	private final String file = "materials/chance.txt";
	
	/**
	 * Chance Constructor
	 * @param Name - The name of the Field
	 */
	public Chance(String name){
		super(name);
		
		this.cards = new ChanceCreate(new File(file));
		this.count = 0;
	}
	
	public void landOnField(Player player) {
		cards.getCard(count);
		count++;
		
		if(count > (cards.getDeckSize() - 1)) {
			count = 0;
		}
	}
}