package game;

public class PropertyControl {
	private boolean notDone = true;
	private Player player;
	private Street[] fields;
	private int[] fieldNumbers;
	private Updater updater;
	private boolean buying;
	
	/**
	 * Property Control
	 * @param player - The choice of player
	 * @param fields - Street array of the group of streets
	 * @param fieldNumbers - int array for Streets array (from original array)
	 * @param updater - The Updater
	 * @param buying - The choice of buying (true) or selling (false)
	 */
	public PropertyControl(Player player, Street[] fields, int[] fieldNumbers, Updater updater, boolean buying) {
		do {
			if(buying)
				this.buildEvenly(player, fields, fieldNumbers, updater);
			else
				this.sellEvenly(player, fields, fieldNumbers, updater);
		} while(notDone);
	}
	
	/**
	 * run Builder
	 * </p>
	 * runs sellEvenly or buildEvenly until done
	 */
	public void runBuilder() {
		do {
			if(buying)
				this.buildEvenly(player, fields, fieldNumbers, updater);
			else
				this.sellEvenly(player, fields, fieldNumbers, updater);
		} while(notDone);
	}
	
	/**
	 * sell Evenly
	 * @param player - The choice of player
	 * @param fields - Street array of the group of streets
	 * @param fieldNumbers - int array for Streets array (from original array)
	 * @param updater - The Updater
	 * </p>
	 * runs sell progress for houses and hotels and does it evenly
	 */
	private void sellEvenly(Player player, Street[] fields, int[] fieldNumbers, Updater updater) {
		String[] options = null;
		
		if(fields.length == 3) {
			options = new String[4];
			
			for(int i = 0; i < fields.length; i++)
				options[i] = (i + 1) + ". " + fields[i].getName();
			
			options[3] = "4. Færdig med salg af huse.";
		}
		
		else if(fields.length == 2) {
			options = new String[3];
			
			for(int i = 0; i < fields.length; i++)
				options[i] = (i + 1) + ". " + fields[i].getName();
			
			options[2] = "4. Færdig med salg af huse.";
		}
		
		String choice = updater.getUserButtonPressed("Salgspris pr. hus: " + (fields[0].getHousePrice() / 2) + ". Vælg en grund De vil sælge fra.", options);
		
		switch(getChoice(choice)) {
		case 1:
			if(fields[0].getHouses() > 0) {
				if(canSellHouse(0, fields)) {
					if(fields[0].getHouses() == 5) {
						fields[0].removeHotel(fieldNumbers[0]);
						player.setHotels((player.getHotels() - 1));
					}
					else {
						fields[0].setHouses(fieldNumbers[0], (fields[0].getHouses() - 1));
					}
					
					player.alterAccount((fields[0].getHousePrice() / 2));
					player.setAssets(-((int) Math.floor((0.5 * fields[0].getHousePrice()))));
					player.setHouses((fields[0].getHouses() - 1));
				}
				else
					updater.showMessage("De skal sælge jævnt fra grundene.");
			}
			else
				updater.showMessage("De kan ikke sælge flere huse fra "+ fields[0].getName() + ", da der ikke er flere huse på grunden.");
			break;
		case 2:
			if(fields[1].getHouses() > 0) {
				if(canSellHouse(1, fields)) {
					if(fields[1].getHouses() == 5) {
						fields[1].removeHotel(fieldNumbers[1]);
						player.setHotels((player.getHotels() - 1));
					}
					else {
						fields[1].setHouses(fieldNumbers[1], (fields[1].getHouses() - 1));
					}
					
					player.alterAccount((fields[1].getHousePrice() / 2));
					player.setAssets(-((int) Math.floor((0.5 * fields[1].getHousePrice()))));
					player.setHouses((fields[1].getHouses() - 1));
				}
				else
					updater.showMessage("De skal sælge jævnt fra grundene.");
			}
			else
				updater.showMessage("De kan ikke sælge flere huse fra "+ fields[1].getName() + ", da der ikke er flere huse på grunden.");
			break;
		case 3:
			if(fields[2].getHouses() > 0) {
				if(canSellHouse(2, fields)) {
					if(fields[2].getHouses() == 5) {
						fields[2].removeHotel(fieldNumbers[2]);
						player.setHotels((player.getHotels() - 1));
					}
					else {
						fields[2].setHouses(fieldNumbers[2], (fields[2].getHouses() - 1));
					}
					
					player.alterAccount((fields[2].getHousePrice() / 2));
					player.setAssets(-((int) Math.floor((0.5 * fields[2].getHousePrice()))));
					player.setHouses((fields[2].getHouses() - 1));
				}
				else
					updater.showMessage("De skal sælge jævnt fra grundene.");
			}
			else
				updater.showMessage("De kan ikke sælge flere huse fra "+ fields[2].getName() + ", da der ikke er flere huse på grunden.");
			break;
		case 4:
			notDone = false;
			break;
		}
	}

	/**
	 * build Evenly
	 * @param player - The choice of player
	 * @param fields - Street array of the group of streets
	 * @param fieldNumbers - int array for Streets array (from original array)
	 * @param updater - The Updater
	 * </p>
	 * runs buy progress for houses and hotels and does it evenly
	 */
	private void buildEvenly(Player player, Street[] fields, int[] fieldNumbers, Updater updater) {
		String[] options = null;
		
		if(fields.length == 3) {
			options = new String[4];
			
			for(int i = 0; i < fields.length; i++)
				options[i] = (i + 1) + ". " + fields[i].getName();
			
			options[3] = "4. Færdig med køb af huse.";
		}
		
		else if(fields.length == 2) {
			options = new String[3];
			
			for(int i = 0; i < fields.length; i++)
				options[i] = (i + 1) + ". " + fields[i].getName();
			
			options[2] = "4. Færdig med køb af huse.";
		}
		
		String choice = updater.getUserButtonPressed("Pris pr. hus: " + fields[0].getHousePrice() + ". Vælg en grund De vil købe hus på.", options);
		
		switch(getChoice(choice)) {
		case 1:
			if(player.getAccount() - fields[0].getHousePrice() >= 0)
				if(fields[0].getHouses() <= 4) {
					if(canBuyHouse(0, fields)) {
						if(fields[0].getHouses() == 4) {
							fields[0].setHotel(fieldNumbers[0]);
							player.setHotels((player.getHotels() + 1));
						}
						else {
							fields[0].setHouses(fieldNumbers[0], (fields[0].getHouses() + 1));
						}
						
						player.alterAccount(-fields[0].getHousePrice());
						player.setAssets((int) Math.floor((0.5 * fields[0].getHousePrice())));
						player.setHouses((fields[0].getHouses() - 1));
					}
					else
						updater.showMessage("De skal bygge jævnt på grundene.");
				}
				else
					updater.showMessage("De kan ikke købe flere huse til "+ fields[0].getName() + ", da der allerede er bygget et hotel.");
			else
				updater.showMessage("De har ikke råd til at købe huset.");
			break;
		case 2:
			if(player.getAccount() - fields[1].getHousePrice() >= 0)
				if(fields[1].getHouses() <= 4) {
					if(canBuyHouse(1, fields)) {
						if(fields[1].getHouses() == 4) {
							fields[1].setHotel(fieldNumbers[1]);
							player.setHotels((player.getHotels() + 1));
						}
						else {
							fields[1].setHouses(fieldNumbers[1], (fields[1].getHouses() + 1));
						}
						
						player.alterAccount(-fields[1].getHousePrice());
						player.setAssets((int) Math.floor((0.5 * fields[1].getHousePrice())));
						player.setHouses((fields[1].getHouses() - 1));
					}
					else
						updater.showMessage("De skal bygge jævnt på grundene.");
				}
				else
					updater.showMessage("De kan ikke købe flere huse til "+ fields[1].getName() + ", da der allerede er bygget et hotel.");
			else
				updater.showMessage("De har ikke råd til at købe huset.");
			break;
		case 3:
			if(player.getAccount() - fields[2].getHousePrice() >= 0)
				if(fields[2].getHouses() <= 4) {
					if(canBuyHouse(2, fields)) {
						if(fields[2].getHouses() == 4) {
							fields[2].setHotel(fieldNumbers[2]);
							player.setHotels((player.getHotels() + 1));
						}
						else {
							fields[2].setHouses(fieldNumbers[2], (fields[2].getHouses() + 1));
						}
						
						player.alterAccount(-fields[2].getHousePrice());
						player.setAssets((int) Math.round((0.5 * fields[2].getHousePrice())));
						player.setHouses((fields[2].getHouses() - 1));
					}
					else
						updater.showMessage("De skal bygge jævnt på grundene.");
				}
				else
					updater.showMessage("De kan ikke købe flere huse til "+ fields[2].getName() + ", da der allerede er bygget et hotel.");
			else
				updater.showMessage("De har ikke råd til at købe huset.");
			break;
		case 4:
			notDone = false;
			break;
		}
	}
	
	/**
	 * highest Count
	 * @param fields - Street array of the group of streets
	 * @param buying - The choice of buying (true) or selling (false) 
	 * @return the higest amount of houses on one street from street array
	 */
	private int highestCount(Street[] fields, boolean buying) {
		int highestCount = 0;
		boolean same = true;
		
		for(Street a : fields)
			if(a.getHouses() > highestCount)
				highestCount = a.getHouses();
		
		for(Street a : fields)
			if(a.getHouses() != highestCount)
				same = false;
		
		if(same && buying)
			highestCount = 0;
		
		return highestCount;
	}
	
	/**
	 * can Buy House
	 * @param i - the field number for the choice of street
	 * @param fields - Street array of the group of streets
	 * @return true if able to buy house on chosen street, else false
	 */
	private boolean canBuyHouse(int i, Street[] fields) {
		if(highestCount(fields, true) == 0)
			return true;
		else if(fields[i].getHouses() < highestCount(fields, true))
			return true;
		
		return false;
	}
	
	/**
	 * get Choice
	 * @param str - the choice string
	 * @return the number indicator for the string
	 */
	private int getChoice(String str) {
		return Integer.parseInt(str.split("\\.")[0]);
	}
	
	/**
	 * can Sell House
	 * @param i - the field number for the choice of street
	 * @param fields - Street array of the group of streets
	 * @return true if able to sell house on chosen street, else false
	 */
	private boolean canSellHouse(int i, Street[] fields) {
		if(fields[i].getHouses() == highestCount(fields, false))
			return true;
		
		return false;
	}
}
