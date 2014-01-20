package game;

public class Turn {
	private int[] index;
	private int current;
	
	public Turn(int index) {
		this.index = new int[index];
		this.current = 0;
		
		for(int i = 0; i < this.index.length; i++)
			this.index[i] = 1;
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
