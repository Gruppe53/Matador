package game;

import boundaryToMatador.GUI;

public class PropertyControl {
	private boolean notDone = true;
	
	public PropertyControl(Player player, Street[] fields, int[] fieldNumbers) {
		for(int i = 0; i < fields.length; i++)
			System.out.println("pControl " + (i + 1) + ": " + fields[i].getName());
		// Secure evenly built houses
		do {
			this.buildEvenly(player, fields, fieldNumbers);
		} while(notDone);
	}
	
	private void buildEvenly(Player player, Street[] fields, int[] fieldNumbers) {
		String[] options = new String[4];
		
		// !!!!!!!!!Hvis alle er ens, lav exception!!!!!!!!!!!!
		// HUSK AT MAN MAKS KAN HAVE 4 HUSE (5 FOR HOTEL)
		for(int i = 0; i < fields.length; i++) {
			if(highestCount(fields) == 0)
				options[i] = (i + 1) + ". " + fields[i].getName();
			else if(fields[i].getHouses() < highestCount(fields))
				options[i] = (i + 1) + ". " + fields[i].getName();
		}
		
		options[3] = "4. Færdig med køb af huse.";
		
		String choice = GUI.getUserButtonPressed("Pris pr. hus: " + fields[0].getHousePrice(), options);
		
		switch(getChoice(choice)) {
		case 1:
			if(player.getAccount() - fields[0].getHousePrice() >= 0)
				fields[0].setHouses(fieldNumbers[0], (fields[0].getHouses() + 1));
			else
				GUI.showMessage("De har ikke råd til at købe huset.");
			break;
		case 2:
			if(player.getAccount() - fields[0].getHousePrice() >= 0)
				fields[1].setHouses(fieldNumbers[1], (fields[1].getHouses() + 1));
			else
				GUI.showMessage("De har ikke råd til at købe huset.");
			break;
		case 3:
			if(player.getAccount() - fields[0].getHousePrice() >= 0)
				fields[2].setHouses(fieldNumbers[2], (fields[2].getHouses() + 1));
			else
				GUI.showMessage("De har ikke råd til at købe huset.");
			break;
		case 4:
			notDone = false;
			break;
		}
		// Husk at opdatere assets
	}
	
	private int highestCount(Street[] fields) {
		int highestCount = 0;
		boolean same = true;
		
		for(Street a : fields)
			if(a.getHouses() > highestCount)
				highestCount = a.getHouses();
		
		for(Street a : fields)
			if(a.getHouses() != highestCount)
				same = false;
		
		if(same)
			highestCount = 0;
		
		return highestCount;
	}
	
	private int getChoice(String str) {
		return Integer.parseInt(str.split("\\. ")[0]);
	}
}
