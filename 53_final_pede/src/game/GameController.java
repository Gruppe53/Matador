package game;

import java.awt.Color;
import boundaryToMatador.GUI;

public class GameController {
	private Player[] player;
	private Color[] colorSet = { Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.CYAN };
	
	private TurnController turn;
	private Dice roll = new Dice(1, 6, 2);
	
	// FINALS
	private final int startCash = 30000;
	
	public GameController() {
		// Create board with proper names and descriptions
		GUI.create("materials/fields.txt");
		
		// First receive amount of players by input, then create an array with the amount of players and their names
		createPlayers(countPlayers());
		
		// Keep playing the game 'till someone is victorious
		do {
			// Check if current player hasn't already lost
			if(turn.getIndex(turn.getCurrent()) == 0) {
				System.out.println("Spiller " + turn.getCurrent() + "'s tur");
				
				int c = GUI.getUserInteger(player[turn.getCurrent()].getName() + ", det er din tur.\n0.\tKast med terning\n1.\tKøb hus/hotel\n2.\tSælg hus/hotel\n3.\tPantsæt grund\n4.\tByt med spiller\n5. ", 0,5);
				
				switch(c) {
					case 0:
						roll.throwDice();
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						break;
					default:
						roll.throwDice();
						GUI.setDice(roll.getValue(0), roll.getValue(1));
				}
				
				System.out.println(player[turn.getCurrent()].getName() + " valgte " + c);
				
				turn.nextTurn();
			}
		} while(turn.noWinner());
		
		GUI.close();
	}
	
	// Move the cars in the GUI "smoothly"
	private void movePiece(int i, int n, int c) {
		if (n > 21) {
			n -= 21;

			for (int f = 1; f <= (21 - c); f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar((c + f), player[i].getName());
				sleep(300); // When testing, set to 1, or get bored
			}

			for (int f = 1; f <= n; f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(300); // When testing, set to 1, or get bored
			}
		} else {
			for (int f = (c + 1); f <= n; f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(300); // When testing, set to 1, or get bored
			}
		}

		player[i].setPos(n);
	}

	// Create array with length of the user input n, and add players to GUI with name provided via user input
	private void createPlayers(int n) {
		String name;
		player = new Player[n];
		
		for (int i = 0; i < n; i++) {
			name = GUI.getUserString("Enter player " + (i + 1) + "'s name");

			GUI.addPlayer(name, startCash, colorSet[i]);
			GUI.showMessage(name + " has been added.");

			player[i] = new Player(name);
			turn.setIndex(i, 0);
		}
	}
	
	// Count amount of players by user input
	private int countPlayers() {
		int i = GUI.getUserInteger("Choose amount of players (minimum 2, maximum 6).", 2, 6);
		
		this.turn = new TurnController(i);
		
		return i;
	}
	
	// Make the system wait for n-amount of milliseconds before doing anything
	private void sleep(int n) {
		long start, end;

		start = System.currentTimeMillis();

		do {
			end = System.currentTimeMillis();
		} while ((end - start) < (n));
	}
}