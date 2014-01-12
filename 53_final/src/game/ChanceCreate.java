package game;

import java.io.*;

public class ChanceCreate {
	private File file;
	private String[] card;
	private int count;
	
	public ChanceCreate(File file) {
		this.file = file;
		this.count = 0;
		
		this.readLines();
		this.shuffleDeck();
	}

	private void shuffleDeck() {
		// TODO Auto-generated method stub
		
	}

	public String getCard(int i) {
		return this.card[i];
	}
	
	public int getDeckSize() {
		return this.card.length;
	}
	
	private void readLines() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = in.readLine()) != null) {
				createDeck(line);
			}
			
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createDeck(String line) {
		card[count] = line;
		count++;
	}
}
