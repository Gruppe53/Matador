package game;

public class Player {
	private String name;
	private int status;
	private int position;
	private int cash;
	
	public Player(String name, int cash) {
		this.name = name;
		this.position = 1;
		this.cash = cash;
	}

	public String getName() {
		return this.name;
	}

	public int getStatus() {
		return this.status;
	}
	
	public int getPosition() {
		return this.cash;
	}
	
	public void setPos(int position) {
		this.position = position; 
	}
}
