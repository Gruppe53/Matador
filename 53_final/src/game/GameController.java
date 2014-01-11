package game;

import java.awt.Color;
import boundaryToMatador.GUI;

public class GameController {
	private Player[] player;
	private Color[] colorSet = { Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.CYAN };
	
	private TurnController turn;
	private Dice roll = new Dice(1, 6, 2);
	
	// Sidste test
	
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
				int c = GUI.getUserInteger(player[turn.getCurrent()].getName() 
						+ ", det er din tur.\n"
						+ "0.\tKast med terning\n"
						+ "1.\tByg hus/hotel\n"
						+ "2.\tSælg hus/hotel\n"
						+ "3.\tPantsæt grund\n"
						+ "4.\tByt med spiller\n"
						+ "5.\tEt eller andet...",
						0,
						5
				);
				
				// Do a new roll with dice
				roll.throwDice();
				
				// Assign position-values
				int currentPosition = player[turn.getCurrent()].getPosition();
				int newPosition = currentPosition + roll.getSum();
				
				switch(c) {
					case 0:
						// Draw the roll
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						// Move the piece smoothly
						movePiece(turn.getCurrent(), newPosition, currentPosition);
						break;
					case 1:
						GUI.getUserInteger("Hvad vil de foretage dem?\n"
								+ "0.\tByg hus(e)\n"
								+ "1.\tByg hoteller\n"
								+ "2.\tVende tilbage til spilmenu",
								0,
								2
						);
						break;
					case 2:
						// Something...
						break;
					case 3:
						// Something..
						break;
					case 4:
						// Something..
						break;
					case 5:
						// Something..
						break;
					default:
						// Draw the roll
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						// Move the piece smoothly
						movePiece(turn.getCurrent(), newPosition, currentPosition);
				}
				
				// Next player's turn
				turn.nextTurn();
			}
		} while(turn.noWinner());
		
		
		// End GUI-session, when game is done.
		GUI.close();
	}
	
	// Move the cars in the GUI "smoothly"
	private void movePiece(int i, int n, int c) {
		int temp = 0;
		// If we pass start
		if (n > 40) {
			System.out.println("over 40");
			System.out.println(player[i].getName() + " rykker fra " + c + " til " + n);
			n -= 40;
			
			// First move the piece the last steps before hitting START
			for (int f = 1; f <= (21 - c); f++, temp++) {
				System.out.println(temp);
				System.out.println();
				GUI.removeAllCars(player[i].getName());
				GUI.setCar((c + f), player[i].getName());
				sleep(300); // When testing, set to 1, or get bored
			}
			
			// Now move the piece the fields after START
			for (int f = 1; f <= n; f++, temp++) {
				System.out.println(temp);
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(300); // When testing, set to 1, or get bored
			}
		} else {
			// Move the piece the require fields
			for (int f = (c + 1); f <= n; f++, temp++) {
				System.out.println(temp);
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(300); // When testing, set to 1, or get bored
			}
		}

		player[i].setPosition(n);
	}

	// Create an array with length of the user input n, and add players to GUI with the names provided via user input
	private void createPlayers(int n) {
		String name;
		player = new Player[n];
		
		for (int i = 0; i < n; i++) {
			name = GUI.getUserString("Enter player " + (i + 1) + "'s name");

			GUI.addPlayer(name, startCash, colorSet[i]);
			GUI.showMessage(name + " has been added.");

			player[i] = new Player(name, startCash);
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
