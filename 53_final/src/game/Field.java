package game;

abstract public class Field {
	private String name;
	
	/**
	 * Field Constructor
	 * @param name - The name of the field
	 */
	public Field (String name){
		this.name = name;
	}
	
	/**
	 * Land On Field metode
	 */
	public abstract void landOnField();
	
	/**
	 * getName
	 * @param Returns the name of the field
	 */
	public String getName() {
		return name;
	}
}
