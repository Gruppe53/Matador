package game;

import boundaryToMatador.GUI;

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
		return this.position;
	}
	
	public void setPosition(int position) {
		this.position = position; 
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int alterCash(int amount) {
		if ((this.cash + amount) >= 0) {

			this.cash += amount;
		}

		else {
			this.cash = 0;
			setStatus(-1);
			
			GUI.removeAllCars(this.name);
			GUI.showMessage(this.name + ", you went bankrupt - all your properties has been sold to the bank.");
		}
		
		return status;
		// GUI.setBalance(this.name, Score);
	}

	public int payRent(int rent, Player leaser) {
		int amount;
		
		if (rent > this.cash) {
			amount = this.cash;
			setStatus(-1);
		}
		else {
			amount = rent;
			alterCash(-amount);
		}
		
		leaser.alterCash(amount);
		
		return this.status;
	}
}
