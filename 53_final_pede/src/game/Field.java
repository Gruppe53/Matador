package game;

abstract public class Field {
	private String Name;
	
	/**
	 * Field Constructor
	 * @param Name - The name of the field
	 */
	public Field (String Name){
		this.Name = Name;
	}
	
	/**
	 * Land On Field metode
	 */
	public abstract void LandOnField();

}
