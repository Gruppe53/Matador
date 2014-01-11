package game;

public class Player {
	private String name;
	private int status;
	private int position;
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}
	
	public void setPos(int position) {
		this.position = position; 
	}
}
