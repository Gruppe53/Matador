package game;

import java.awt.Color;

import boundaryToMatador.GUI;

public class GameController {
	// NY VERSION AF AE, OE OG AA!
	// BRUG ESCAPETAGS I STEDET! 
	// F.EKS.:
	// FORKERT	String a = "Åen er lang og ællinger svømmer i den";
	// KORREKT	String a = "\u00C5en er lang og \u00E6llinger sv\u00F8mmer i den";
	// LISTE:
	// AA: \u00C5 | aa: \u00E5
	// OE: \u00D8 | oe: \u00F8
	// AE: \u00C6 | ae: \u00E6
	// DET SER IKKE UD TIL HELT AT VIRKE I CONSOLE :<
	// ... det ser ud til slet ikke at virke o_o

	public Player[] player;

	private TurnController turn;
	private Board board = new Board();
	private Dice roll = new Dice(1, 6, 2);
	private boolean secondTurn = false;

	// FINALSs
	private final int startCash = 30000;

	public GameController() {
		// Create board with proper names and descriptions
		GUI.create("materials/fields.txt");

		// First receive amount of players by input, then create an array with the amount of players and their names
		createPlayers(countPlayers());

		// Keep playing the game 'till someone is victorious
		do {
			// Check if current player hasn't already lost
			if((turn.getIndex(turn.getCurrent()) == 0) && (player[turn.getCurrent()].getJailed() == false)) {
				String str = GUI.getUserButtonPressed(player[turn.getCurrent()].getName() + ", det er din tur.\n", "1. Slaa med terning", "2. Byg hus/hotel", "3. Saelg hus/hotel", "4. Pantsaet grund");

				switch(getChoice(str)) {
				case 1:
					// Do a new roll with dice
					roll.throwDice();

					// Assign position-values
					int currentPosition = player[turn.getCurrent()].getPosition();
					int newPosition = currentPosition + roll.getSum();
					
					// Draw the roll
					GUI.setDice(roll.getValue(0), roll.getValue(1));

					// Move the piece smoothly
					movePiece(turn.getCurrent(), newPosition, currentPosition);

					// Make the mechanics of the field start
					System.out.println(player[turn.getCurrent()].toString());
					fieldTricker(player[turn.getCurrent()]);
					break;
				case 2:
					str = GUI.getUserButtonPressed("Hvad vil De foretage dem?", "1. Byg hus(e)", "2. Byg hoteller", "3. Vend tilbage til spilmenu");
					
					switch(getChoice(str)) {
					case 1:
						GUI.showMessage("Huse...");
						break;
					case 2:
						GUI.showMessage("Hoteller...");
						break;
					default:
						break;
					}
					
					secondTurn = true;
					break;
				case 3:
					GUI.getUserInteger("Hvilken grund vil De s&aelig;lge fra?\n"
							+ "Udskriv liste med grunde, som brugeren ejer, og som har huse/hoteller...",
							0,
							2
					);
					
					secondTurn = true;
					break;
				case 4:
					GUI.getUserInteger("Vaelg en grund De gerne vil pantsaette.\n"
							+ "Udskriv liste med grunde, som brugeren ejer, og som IKKE er pantsat i forvejen\n"
							+ "NB: Husk at grunde, hvor der er huse paa, skal foerst have fjernet huse.",
							0,
							2
					);
					
					break;
				default:
					// Do a new roll with dice
					roll.throwDice();

					// Assign position-values
					int currentPositionDefault = player[turn.getCurrent()].getPosition();
					int newPositionDefault = currentPositionDefault + roll.getSum();
					
					// Draw the roll
					GUI.setDice(roll.getValue(0), roll.getValue(1));

					// Move the piece smoothly
					movePiece(turn.getCurrent(), newPositionDefault, currentPositionDefault);

					// Make the mechanics of the field start
					fieldTricker(player[turn.getCurrent()]);
					break;

				}
			}
			
			else if(player[turn.getCurrent()].getJailed() == true) {
				if(player[turn.getCurrent()].getBailoutcards() > 0) {
					if(GUI.getUserLeftButtonPressed("Hvordan vil de komme ud af f&aelig;ngslet", "Sl&aring; med terningen", "Bruge et l&oslash;sladelseskort")) {
						//slaa med terninger for at komme ud
						roll.throwDice();
						
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						for(int i = 0; i < 2 ; i++) {
							roll.throwDice();
							GUI.setDice(roll.getValue(0), roll.getValue(1));
							
							if(roll.isPair()) {
								GUI.showMessage("De har sl&aring;et et par! \nDe kommer nu ud af f&aelig;ngslet");
								player[turn.getCurrent()].setJailed(false);
								secondTurn = true;
								i = 3;
							}
							else {
								if (i <= 2)
									GUI.showMessage("De slog ikke et par, men har " + (2-i) + "fors&oslash;g tilbage");
								else
									GUI.showMessage("De slog stadig ingen par og skal forsat sidde i f&aelig;ngsel til det er deres tur igen");
							}
						}
						
					}
					else {
						//brug bailoutcard
						player[turn.getCurrent()].setBailoutcards(-1);
						player[turn.getCurrent()].setJailed(false);
						secondTurn = true;
						GUI.showMessage("De har benyttet dem af deres ben&aring;dnings kort og kan nu trave frit rundt igen");
					}
				}
				else {
					if(GUI.getUserLeftButtonPressed("Hvordan vil de komme ud af f&aelig;ngslet", "Sl&aring; med terningen", "Betal 1000,-")) {
						//sl\u00E5 med terninger for at komme ud
						roll.throwDice();
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						for(int i = 0; i<2 ; i++) {
							roll.throwDice();
							GUI.setDice(roll.getValue(0), roll.getValue(1));

							if(roll.isPair()) {
								GUI.showMessage("De har sl&aring;et et par! \nDe kommer nu ud af f&aelig;ngslet");
								player[turn.getCurrent()].setJailed(false);
								secondTurn = true;
								i = 3;
							}
							else {
								if(i <= 2)
									GUI.showMessage("De slog ikke et par, men har " + (2-i) + "fors&aring;g tilbage");
								else
									GUI.showMessage("De slog stadig ingen par og skal forsat sidde i f&aelig;ngsel til det er deres tur igen");
							}
						}
					}
				}
			} // else if line 102	
		
			// Next player's turn
			if(!secondTurn)
				turn.nextTurn();
			else
				secondTurn = false;
		} while(turn.noWinner());

		// End GUI-session, when game is done.
		GUI.close();
	}

	private int getChoice(String str) {
		return Integer.parseInt(str.split("\\. ")[0]);
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

		player[turn.getCurrent()].setPosition(n);
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
	public void fieldTricker(Player player) {
		// Which field has the player landed on (minus 1, since we're dealing with an array from 0-39)
		board.landOnField((player.getPosition() - 1), player);

		// If the player landed on a field, which he couldn't afford landing on
		// then reset his owned fields
		// TODO - correctly losing
		// need to make sure that the player's assets is accounted for and sold (ownable fields)
		// if these make it possible for him to stay in the game
		if (player.getStatus() < 0) {
			board.resetField(player);
		}
	}
	
	public void buyHouseHotel(Player player) {
		board.buildHouseHotel(player, board.getAvailableGrounds(player));
	}
}
