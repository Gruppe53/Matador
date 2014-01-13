package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ChanceDeck {
	private ChanceCard[] deck;
	private int count = 0;
	
	// FINALS
		private final File file = new File("materials/chance.txt");
	
	public ChanceDeck() {
		createCards();
	}
	
	private void createCards() {
		String str = readLines();
		
//		for (String field : str.split("\\|\\|")) {
//            String[] attributes = field.split(";;");
//		}
		
		for(String field : str.split("\\|\\|")) {
			String[] attributes = field.split(";;");
			System.out.println(attributes[0]);
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
