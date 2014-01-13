package game;

import java.io.File;

public class ChanceDeck {
	private ChanceCard deck;
	
	// FINALS
		private final File file = new File("materials/chance.txt");
	
	public ChanceDeck() {
		deck = new ChanceCard(file);
	}
}
