package game;

abstract public class Field {
	protected String name;
	
	/**
	 * Field Constructor
	 * @param name - The name of the field
	 */
	public Field (String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Land On Field
	 * @param Player - The player who lands on the field
	 */
	public abstract void landOnField(Player player);
	
	/**
	 * getName
	 * @param Returns the name of the field
	 */
	public String getName() {
		return name;
	}
}
