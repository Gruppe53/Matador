package game;

import java.io.*;

public class ChanceCreate {
	private File file;
	private String[][][][] card;
	private int count;
	
	public ChanceCreate(File file) {
		this.file = file;
		this.count = 0;
		
		this.readLines();
		this.shuffleDeck();
	}

	private void shuffleDeck() {
		// TODO
	}

	public String getCard(int i) {
		return this.card[i][0][0][0];
	}
	
	public int getDeckSize() {
		return this.card.length;
	}
	
	private String readLines() {
		String content = "";
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = in.readLine()) != null) {
				content += line.trim() + "\n";
			}
			
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
}
