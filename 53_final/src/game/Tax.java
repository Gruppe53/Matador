package game;

public class Tax extends Field {
	private int taxType;
	
	/**
	 * Tax Constructor
	 * @param Name - The name of the Field
	 * @param taxType - taxType = 0 : 10% or 4.000kr else taxType != 0 : 2.000kr 
	 */
	public Tax(String Name,int taxType){
		super(Name);
		this.taxType = taxType;
		
	}
	/**
	 * LandOnField 
	 * <p>
	 * 
	 */
	public void landOnField(Player player){
		// TODO .... fix tax ffs
		if (taxType == 0){
			//10% eller 4k
		}
		else {
			//2k
		}
	}
}
