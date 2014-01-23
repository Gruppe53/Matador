package game;

public class Dice {
	private int[] faceValue;
	private int quantity, faceSum, min, max;
	private boolean isPair;
	
	// FOR TESTING
	private int[] cheatDice;
	private int count = 0;

	/**
	 * Dice Constructor
	 * @param min - The minimum facevalue of dice
	 * @param max - The maximum facevalue of dice
	 * @param quantity - The amount of dices
	 */
	public Dice(int min, int max, int quantity) {
		this.quantity = quantity;
		this.min = min;
		this.max = max;
		
		faceValue = new int[quantity];
	}
	
	/**
	 * "Roll" the dices. <p>
	 * Giving each dice a randomized facevalue
	 */
	public void throwDice() {
		for(int i = 0; i < quantity; i++)
			faceValue[i] = (int) (Math.random() * max + min);
	}
	
	public void cheatDice() {
		faceValue[0] = cheatDice[count];
		
		count++;
	}
	
	/**
	 * setMin
	 * @param min - minimum facevalue for dices
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * setMax
	 * @param max - maximum facevalue for dices
	 */
	public void setMax(int max) {
		this.max = max;
	}
	
	/**
	 * setQuan
	 * @param quantity - the amount of dices
	 */
	public void setQuan(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * getQuan
	 * @return the amount of dices
	 */
	public int getQuan() {
		return quantity;
	}
	
	/**
	 * setCheatDice
	 * @param i sets the roll sequence.
	 */
	public void setCheatDice(int[] i) {
		this.cheatDice = i;
	}
	
	/**
	 * getValue
	 * @param i - The number of a dice (Dice (i+1))
	 * @return The facevalue of the choosen dice, if dice doesn't exist returns 0.
	 */
	public int getValue(int i) {
		if(i > quantity || i < 0)
			return 0;
		else
			return faceValue[i];
	}
	
	/**
	 * getSum
	 * @return The sum of the facevalue on all dices
	 */
	public int getSum() {
		faceSum = 0;

		for(int i = 0; i < quantity; i++)
			faceSum += faceValue[i];

		return faceSum;
	}
	
	/**
	 * isPair <p>
	 * Checks for equality of all the dices
	 * @return true if dices are equal, else false
	 */
	public boolean isPair() {
		isPair = true;
		
		if(this.quantity == 1)
			return false;

		for(int i = 0; i < (quantity - 1) && isPair; i++)
			if(faceValue[i] != faceValue[i + 1])
				isPair = false;

		return isPair;
	}
}
