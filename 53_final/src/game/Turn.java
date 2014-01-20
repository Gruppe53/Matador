package game;

public class Turn {
	private int[] index;
	private int current;
	
	/**
	 * Turn Constructor
	 * @param index - the amount of players
	 */
	public Turn(int index) {
		this.index = new int[index];
		this.current = 0;
		
		for(int i = 0; i < this.index.length; i++)
			this.index[i] = 1;
	}
	
	/**
	 * set Index
	 * @param i - the choice of player (from array)
	 * @param index - set activity
	 */
	public void setIndex(int i, int index) {
		this.index[i] = index;
	}
	
	/**
	 * set Current
	 * @param current - current turn
	 */
	public void setCurrent(int current) {
		this.current = current;
	}
	
	/**
	 * next Turn
	 * </p>
	 * Makes the current turn to the next active player
	 */
	public void nextTurn() {
		current++;
		
		if (current >= index.length)
			current = 0;
		
		if(index[current] == -1) {
			boolean notActive = true;
			
			do {
				if(this.current == -1)
					current++;
				else
					notActive = false;
				
				if (current >= index.length)
					current = 0;
					
			} while(notActive);
		}
	}
	
	/**
	 * get Index
	 * @param i - the choice of player (from array)
	 * @return the activity of the chosen player
	 */
	public int getIndex(int i) {
		return this.index[i];
	}
	
	/**
	 * get Current
	 * @return which players turn it is (for array)
	 */
	public int getCurrent() {
		return this.current;
	}
	
	/**
	 * no Winner
	 * @return false if there is a winner, else true
	 */
	public boolean noWinner() {
		int status = index.length;

		for (int i = 0; i < index.length; i++)
			status += index[i];

		if (status == 1)
			return false;
		else
			return true;
	}
}
