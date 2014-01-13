package test;

import java.io.*;

public class ChanceCreate {
	private File file;
	private String[] card;
	private int count;
	
	public ChanceCreate(File file) {
		this.file = file;
		this.count = 0;
		
		this.createDeck();
		this.shuffleDeck();
	}

	private void shuffleDeck() {
		// TODO
	}
	
	private void createDeck() {
		String str = readLines();
		for(int i = 0; i < 3; i++) {
			for (String field : str.split("\\|\\|")) {
	            String[] attributes = field.split(";;");
	            
	            System.out.println(attributes[i].length());
			}
		}
	}

	public String getCard(int i) {
		return this.card[i];
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
