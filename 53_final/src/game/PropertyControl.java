package game;

public class PropertyControl {
	
	public PropertyControl(Player player, Street[] fields) {
		for(int i = 0; i < fields.length; i++)
			System.out.println("pControl " + (i + 1) + ": " + fields[i].getName());
		// Secure evenly built houses
	}
}
