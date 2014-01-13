package game;

import java.awt.Color;

import boundaryToMatador.GUI;

public class GameController {
	// For at fikse ae, oe og aa midlertidigt (problem vedr. GitHub), kan entities bruges:
	// &aelig; er lille ae | &AElig; er stort AE
	// &oslash; er lille OE | &Oslash; er stort OE
	// &aring; er lille AA | &Aring; er stort AA
	// Hvis den stadig ikke udskriver entities, skal I tilfoeje <html> i starten af string
	// og </html> i slutningen af string
	
	private Player[] player;
	
	private TurnController turn;
	private Dice roll = new Dice(1, 6, 2);
	private JailController jailControl;
	
	// FINALS
	private final int startCash = 30000;
	private Board board;
	
	public GameController() {
		// Create board with proper names and descriptions
		GUI.create("materials/fields.txt");
		
		// First receive amount of players by input, then create an array with the amount of players and their names
		createPlayers(countPlayers());
		
		// Keep playing the game 'till someone is victorious
		do {
			// Check if current player hasn't already lost
			if((turn.getIndex(turn.getCurrent()) == 0) && (jailControl.isInJail() == false)) {				
				int choice = GUI.getUserInteger(player[turn.getCurrent()].getName() 
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
				
				switch(choice) {
					case 0:
						// Draw the roll
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						// Move the piece smoothly
						movePiece(turn.getCurrent(), newPosition, currentPosition);
						
						// Make the mechanics of the field start
						System.out.println(player[turn.getCurrent()].getClass());
						fieldTricker(player[turn.getCurrent()]);
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
						GUI.getUserInteger("Hvilken grund vil De sælge fra?\n"
								+ "Udskriv liste med grunde, som brugeren ejer, og som har huse/hoteller...",
								0,
								2
						);
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
						
						// Make the mechanics of the field start
						fieldTricker(player[turn.getCurrent()]);
						break;
						
				}
			}
			
			else if(jailControl.isInJail() == true) {
				
			}
			
			// Next player's turn
			turn.nextTurn();
			
		} while(turn.noWinner());
		
		
		// End GUI-session, when game is done.
		GUI.close();
	}
	
	// Move the cars in the GUI "smoothly"
	private void movePiece(int i, int n, int c) {
		if(n > 40) {
			n -= 40;
			
			// First move the piece the last steps before hitting START
			for (int f = 1; f <= (40 - c); f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar((c + f), player[i].getName());
				sleep(100); // When testing, set to 1, or get bored
			}
			
			// Now move the piece the fields after START
			for (int f = 1; f <= n; f++) {
				if(f == 2){
					player[i].alterAccount(4000);
					GUI.setBalance(player[i].getName(), player[i].getAccount());
				}
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(100); // When testing, set to 1, or get bored
			}
		} else {
			// Move the piece the require fields
			for (int f = (c + 1); f <= n; f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(100); // When testing, set to 1, or get bored
			}
		}

		player[i].setPosition(n);
	}

	// Create an array with length of the user input n, and add players to GUI with the names provided via user input
	private void createPlayers(int n) {
		String name;
		Color[] colorSet = { Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.CYAN };
		
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
	
	// For trickering the field mechanics for a specific field
	// TODO - fieldTricker returnerer fejl, nullPointException??
	private void fieldTricker(Player player) {
		System.out.println("1");
		// Which field has the player landed on (minus 1, since we're dealing with an array from 0-39)
		board.landOnField(player.getPosition() - 1, player);
		System.out.println("2");
		
		// If the player landed on a field, which he couldn't afford landing on
		// then reset his owned fields
		// TODO - correctly losing
		// need to make sure that the player's assets is accounted for and sold (ownable fields)
		// if these make it possible for him to stay in the game
		if (player.getStatus() < 0) {
			board.resetField(player);
		}
	}
}
