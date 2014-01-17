package game;

public class PropertyControl {
	
	public PropertyControl(Player player, Street[] fields) {
		for(int i = 0; i < fields.length; i++)
			System.out.println("pControl " + (i + 1) + ": " + fields[i].getName());
		// Secure evenly built houses
		// find hvor mange huse der er
		int highestCount = 0;
		
		for(Street a : fields) {
			if(a.getHouses() > highestCount)
				highestCount = a.getHouses();
		}
		
		
	}
}
