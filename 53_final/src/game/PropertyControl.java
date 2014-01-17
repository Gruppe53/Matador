package game;

import boundaryToMatador.GUI;

public class PropertyControl {
	private Street[] fields;
	private Player player;
	private boolean notDone = true;
	
	public PropertyControl(Player player, Street[] fields, int fieldNumbers) {
		this.player = player;
		this.fields = fields;
		
		for(int i = 0; i < fields.length; i++)
			System.out.println("pControl " + (i + 1) + ": " + fields[i].getName());
		// Secure evenly built houses
		do {
			this.buildEvenly(fields);
		} while(notDone);
	}
	
	private void buildEvenly(Street[] fields) {
		String[] options = new String[4];
		
		// !!!!!!!!!Hvis alle er ens, lav exception!!!!!!!!!!!!
		// HUSK AT MAN MAKS KAN HAVE 4 HUSE (5 FOR HOTEL)
		for(int i = 0; i < fields.length; i++) {
			if(fields[i].getHouses() < highestCount(fields))
				options[i] = (i + 1) + ". " + fields[i].getName();
		}
		
		options[4] = "4. Færdig med køb af huse.";
		
		String choice = GUI.getUserButtonPressed("Pris pr. hus: " + fields[0].getHousePrice(), options);
		
		switch(getChoice(choice)) {
		case 1:
			fields[0].setHouses(field, houses);
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			notDone = false;
			break;
		}
		// Husk at opdatere assets
	}
	
	private int highestCount(Street[] fields) {
		int highestCount = 0;
		
		for(Street a : fields)
			if(a.getHouses() > highestCount)
				highestCount = a.getHouses();
		
		return highestCount;
	}
	
	private int getChoice(String str) {
		return Integer.parseInt(str.split("\\. ")[0]);
	}
}
