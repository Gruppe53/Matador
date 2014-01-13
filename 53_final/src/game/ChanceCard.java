package game;

import java.io.*;

public class ChanceCard {
	private File file;
	private String[] card;
	private int count;
	
	public ChanceCard(File file) {
		this.file = file;
		this.count = 0;
	}
	
	public card getCards() {
		return card;
	}
	
	private void createCards() {
		String str = readLines();
		
		for (String field : str.split("\\|\\|")) {
            String[] attributes = field.split(";;");
            
            System.out.println(attributes);
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
