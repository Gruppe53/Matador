package game;

public class Ownable extends Field {
	public Player Owner;
	public int Price;
	
	/**
	 * Ownable Constructor
	 * @param Name - The name of the Field
	 * @param Owner - The Owner of the field (No Owner = Null)
	 */
	public Ownable(String Name, Player Owner, int Price){
		super(Name);
		this.Owner = Owner;
		this.Price = Price;
		
	}
	
	public void LandOnField(){
		
	}
}
