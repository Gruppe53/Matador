package game;

public class TurnController {
	private int[] index;
	private int current;
	
	public TurnController(int index) {
		this.index = new int[index];
		this.current = 0;
	}
	
	public void setIndex(int i, int index) {
		this.index[i] = index;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public void nextTurn() {
		current++;
		
		if (current >= index.length)
			current = 0;
	}
	
	public int getIndex(int i) {
		return this.index[i];
	}
	
	public int getCurrent() {
		return this.current;
	}
	
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
