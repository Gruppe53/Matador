package game;

import boundaryToMatador.GUI;

public class PropertyControl {
	private Street[] fields;
	private Player player;
	private boolean notDone = true;
	
	public PropertyControl(Player player, Street[] fields) {
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
		String[] options = new String[3];
		
		for(int i = 0; i < fields.length; i++) {
			if(fields[i].getHouses() < highestCount(fields))
				options[i] = fields[i].getName();
		}
		
		GUI.getUserSelection("Pris pr. hus: " + fields[0].getHousePrice(), options);
		GUI.getUserButtonPressed("Pris pr. hus: " + fields[0].getHousePrice(), options);
		
		// Husk at opdatere assets
	}
	
	private int highestCount(Street[] fields) {
		int highestCount = 0;
		
		for(Street a : fields)
			if(a.getHouses() > highestCount)
				highestCount = a.getHouses();
		
		return highestCount;
	}
}
