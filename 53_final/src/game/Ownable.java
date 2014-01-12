package game;

public class Ownable extends Field {
	public Player Owner;
	public int Price;
	
	/**
	 * Ownable Constructor
	 * @param Name - The name of the Field
	 * @param Owner - The Owner of the field (No Owner = Null)
	 */
	public Ownable(String Name, int Price){
		super(Name);
		this.Price = Price;
		
	}
	
	public void LandOnField(){
		// TODO
	}

	public boolean isOwner(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public void resetOwner() {
		// TODO Auto-generated method stub
		
	}
}
