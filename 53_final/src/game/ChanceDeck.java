package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class ChanceDeck {
	private ChanceCard[] deck = new ChanceCard[46];
	private int cardCount = 0;
	private int pickCount = 0;
	
	// FINALS
	private final File file = new File("materials/chance.txt");
	
	public ChanceDeck() {
		createCards();
		System.out.println(deck[44].getDescription() + " ---- test: \u00E5");
		System.out.println(deck[45].getDescription());
		shuffleDeck(deck);
		System.out.println(deck[0].getDescription());
		shuffleDeck(deck);
		System.out.println(deck[0].getDescription());
	}
	
	private void createCards() {
		String str = readLines();
		
		for(String field : str.split("\\|\\|")) {
			String[] attributes = field.split(";;");
			
			String a = attributes[0].split("::")[1];
			String b = attributes[1].split("::")[1];
			String c = attributes[2].split("::")[1];
			String d = attributes[3].split("::")[1];
			
			if(Integer.parseInt(d) > 1) {
				for(int i = 0; i < 2; i++) {
					deck[cardCount] = new ChanceCard(a, b, c);
					
					cardCount++;
				}
			}
			else {
				deck[cardCount] = new ChanceCard(a, b, c);
				
				cardCount++;
			}
		}
	}
	
	public void shuffleDeck(ChanceCard[] deck) {
		Random rnd = new Random();
	    
		for (int i = deck.length - 1; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      
	      // Simple swap
	      ChanceCard a = deck[index];
	      deck[index] = deck[i];
	      deck[i] = a;
	    }
	}
	
	public String getDesc(int i) {
		return deck[i].getDescription();
	}
	
	public String getType(int i) {
		return deck[i].getType();
	}
	
	public String getAction(int i) {
		return deck[i].getAction();
	}
	
	public void setPickCount() {
		this.pickCount++;
		
		if(this.cardCount <= this.pickCount) {
			shuffleDeck(this.deck);
			this.pickCount = 0;
		}
	}

	private String readLines() {
		String content = "";
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			String line = null;
			while ((line = in.readLine()) != null) {
				if(line.trim().indexOf("#") == 0)
				    continue;
				content += line.trim();
			}
			
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
}
