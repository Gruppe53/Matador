package game;

import java.awt.Color;

import boundaryToMatador.GUI;

public class GameController {
	private Player[] player;
	private Updater updater = new Updater();
	private TurnController turn;
	private Board board = new Board();
	private Dice roll = new Dice(1, 6, 2);
	private boolean secondTurn = false;
	private int multiplePair = 0;
	// TODO fix "vend tilbage til hovedmenuen"
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
				String str = updater.getUserButtonPressed(player[turn.getCurrent()].getName() + ", det er Deres tur.\n", "1. Slå med terning", "2. Byg hus/hotel", "3. Sælg hus/hotel", "4. Pantsæt grund");

				switch(getChoice(str)) {
				case 1:
					// Do a new roll with dice
					roll.throwDice();
					// Get an extra turn if dices are pair
					if(roll.isPair()){
						secondTurn = true;
						multiplePair++;
						if(multiplePair >= 3){
							player[turn.getCurrent()].setJailed(true);
							player[turn.getCurrent()].setPosition(11);
							GUI.removeAllCars(player[turn.getCurrent()].getName());
							GUI.setCar(11, player[turn.getCurrent()].getName());
							GUI.showMessage("De slog to ens tre gange i streg, og ryger direkte i fængsel.");
						}
					}
					

					// Assign position-values
					int currentPosition = player[turn.getCurrent()].getPosition();
					int newPosition = currentPosition + roll.getSum();
					
					// Draw the roll
					GUI.setDice(roll.getValue(0), roll.getValue(1));

					// Move the piece smoothly
					movePiece(turn.getCurrent(), newPosition, currentPosition);
					
					player[turn.getCurrent()].setRollSum(roll.getSum());

					// Make the mechanics of the field start
					fieldTricker(player[turn.getCurrent()]);
					break;
				case 2:
					str = GUI.getUserButtonPressed("Hvad vil De foretage dem?", "1. Byg huse/hoteller", "2. Vend tilbage til spilmenu");
					
					switch(getChoice(str)) {
					case 1:
						buyProperty(player[turn.getCurrent()], GUI.getUserSelection("Hvilken grund vil De købe hus/hotel til?", getAvailableGrounds(player[turn.getCurrent()])));
						break;
					default:
						break;
					}
					
					secondTurn = true;
					break;
				case 3:
					GUI.getUserInteger("Hvilken grund vil De sælge fra?\n"
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
					if(GUI.getUserLeftButtonPressed("Hvordan vil De komme ud af fængslet", "Slå med terningen", "Bruge et løsladelseskort")) {
						//slå med terninger for at komme ud
						roll.throwDice();
						
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						for(int i = 0; i < 2 ; i++) {
							roll.throwDice();
							GUI.setDice(roll.getValue(0), roll.getValue(1));
							
							if(roll.isPair()) {
								GUI.showMessage("De har slået et par! \nDe kommer nu ud af fængslet");
								player[turn.getCurrent()].setJailed(false);
								movePiece(turn.getCurrent(), (player[turn.getCurrent()].getPosition() + roll.getSum()), player[turn.getCurrent()].getPosition());
								secondTurn = true;
								i = 3;
							}
							else {
								if (i < 2)
									GUI.showMessage("De slog ikke et par, men har " + (2-i) + "forsøg tilbage");
								else
									GUI.showMessage("De slog stadig ingen par og skal forsat sidde i fængsel til det er Deres tur igen");
							}
						}
						
					}
					else {
						//brug bailoutcard
						player[turn.getCurrent()].setBailoutcards(-1);
						player[turn.getCurrent()].setJailed(false);
						secondTurn = true;
						GUI.showMessage("De har benyttet dem af deres benådnings kort og kan nu trave frit rundt igen");
					}
				}
				else {
					if(GUI.getUserLeftButtonPressed("Hvordan vil De komme ud af fængslet", "Slå med terningen", "Betal 1000,-")) {
						//slå med terninger for at komme ud
						roll.throwDice();
						GUI.setDice(roll.getValue(0), roll.getValue(1));
						
						for(int i = 0; i<=2 ; i++) {
							roll.throwDice();
							GUI.setDice(roll.getValue(0), roll.getValue(1));

							if(roll.isPair()) {
								GUI.showMessage("De har slået et par! \nDe kommer nu ud af fængslet");
								player[turn.getCurrent()].setJailed(false);
								movePiece(turn.getCurrent(), (player[turn.getCurrent()].getPosition() + roll.getSum()), player[turn.getCurrent()].getPosition());
								secondTurn = true;
								i = 3;
							}
							else {
								if(i < 2)
									GUI.showMessage("De slog ikke et par, men har " + (2-i) + "forsøg tilbage");
								else
									GUI.showMessage("De slog stadig ingen par og skal forsat sidde i fængsel til det er Deres tur igen");
							}
						}
					}
					else{
						//Betal 1000
						GUI.showMessage("De har betalt dem ud af fængslet");
						secondTurn = true;
					}
				}
			} // else if line 102	
		
			// Next player's turn
			if(!secondTurn){
				turn.nextTurn();
				multiplePair = 0;
			}
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
	private void movePiece(int i, int newPosition, int currentPosition) {
		if(newPosition > 40) {
			newPosition -= 40;

			// First move the piece the last steps before hitting START
			for (int f = 1; f <= (40 - currentPosition); f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar((currentPosition + f), player[i].getName());
				sleep(100); // When testing, set to 1, or get bored
			}

			// Now move the piece the fields after START
			for (int f = 1; f <= newPosition; f++) {
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
			for (int f = (currentPosition + 1); f <= newPosition; f++) {
				GUI.removeAllCars(player[i].getName());
				GUI.setCar(f, player[i].getName());
				sleep(100); // When testing, set to 1, or get bored
			}
		}

		player[turn.getCurrent()].setPosition(newPosition);
	}

	// Create an array with length of the user input n, and add players to GUI with the names provided via user input
	private void createPlayers(int n) {
		String name;
		Color[] colorSet = { Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.CYAN };

		player = new Player[n];

		for (int i = 0; i < n; i++) {
			name = GUI.getUserString("Indtast spiller " + (i + 1) + "'s navn");

			GUI.addPlayer(name, startCash, colorSet[i]);
			GUI.showMessage(name + " spiller nu med.");

			player[i] = new Player(name, startCash);
			turn.setIndex(i, 0);
		}
	}

	// Count amount of players by user input
	private int countPlayers() {
		int i = GUI.getUserInteger("Vælg antal spillere (mindst 2, maks 6).", 2, 6);

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
		// need to make sure that the player's assets is accounted for and sold (ownable fields are pawned before losing)
		// if these make it possible for him to stay in the game
		if (player.getStatus() < 0) {
			board.resetField(player);
		}
	}
	

	private String[] getAvailableGrounds(Player player) {
		return board.getAvailableGrounds(player);
	}
	
	public void buyProperty(Player player, String choice) {
		board.buyProperty(player, choice);
	}
}
