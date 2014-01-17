package game;

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
			buildEvenly();
		} while(notDone);
	}
	
	private void buildEvenly() {
		
	}
	
	private int highestCount() {
		int highestCount = 0;
		
		for(Street a : fields)
			if(a.getHouses() > highestCount)
				highestCount = a.getHouses();
		
		return highestCount;
	}
}
