package game;

import java.util.ArrayList;

public class PropertyControl {
	
	public PropertyControl(Player player, ArrayList<Street> fields) {
		int b = 0;
		
		for(Street a : fields)
			System.out.println("pControl " + b++ + ": " + a.getName());
		// Secure evenly built houses
	}
}
