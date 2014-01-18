package game;

import java.awt.Color;

public class GameController {
	private Player[] player;
	private Updater updater = new Updater();
	private TurnController turn;
	private Board board = new Board();
	private Dice roll = new Dice(1, 6, 2);
	private boolean secondTurn = false;
	private int multiplePair = 0;
	private boolean createAuction = true;
	// TODO fix "vend tilbage til hovedmenuen"
	// FINALSs
	private final int startCash = 30000;

	public GameController() {
		// Create board with proper names and descriptions
		updater.create();

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
							updater.position(player[turn.getCurrent()].getPosition(), player[turn.getCurrent()].getName());
							updater.showMessage("De slog to ens tre gange i streg, og ryger direkte i fængsel.");
						}
					}
					

					// Assign position-values
					int currentPosition = player[turn.getCurrent()].getPosition();
					int newPosition = currentPosition + roll.getSum();
					
					// Draw the roll
					updater.setDice(roll.getValue(0), roll.getValue(0));

					// Move the piece smoothly
					updater.movePiece(player[turn.getCurrent()], newPosition, currentPosition);
					
					player[turn.getCurrent()].setRollSum(roll.getSum());

					// Make the mechanics of the field start
					fieldTricker(player[turn.getCurrent()]);
					if(createAuction){
						int auctionField = (player[turn.getCurrent()].getPosition()-1);
						Ownable f = board.getField(auctionField);
						Auction auction = new Auction(updater, player[turn.getCurrent()], player, f);
						auction.runAction();
						auction = null;
						createAuction = false;
					}
					
					break;
				case 2:
					str = updater.getUserButtonPressed("Hvad vil De foretage dem?", "1. Byg huse/hoteller", "2. Vend tilbage til spilmenu");
					
					switch(getChoice(str)) {
					case 1:
						buyProperty(player[turn.getCurrent()], updater.getUserSelection("Hvilken grund vil De købe hus/hotel til?", getAvailableGrounds(player[turn.getCurrent()])));
						break;
					default:
						break;
					}
					
					secondTurn = true;
					break;
				case 3:
					updater.getUserInteger("Hvilken grund vil De sælge fra?\n"
							+ "Udskriv liste med grunde, som brugeren ejer, og som har huse/hoteller...",
							0,
							2
					);
					
					secondTurn = true;
					break;
				case 4:
					str = updater.getUserButtonPressed("Hvad vil De foretage dem?", "1. Byg huse/hoteller", "2. Vend tilbage til spilmenu");
					
					break;
				default:
					// Do a new roll with dice
					roll.throwDice();

					// Assign position-values
					int currentPositionDefault = player[turn.getCurrent()].getPosition();
					int newPositionDefault = currentPositionDefault + roll.getSum();
					
					// Draw the roll
					updater.setDice(roll.getValue(0), roll.getValue(1));

					// Move the piece smoothly
					updater.movePiece(player[turn.getCurrent()], newPositionDefault, currentPositionDefault);

					// Make the mechanics of the field start
					fieldTricker(player[turn.getCurrent()]);
					break;

				}
			}
			
			else if(player[turn.getCurrent()].getJailed() == true) {
				if(player[turn.getCurrent()].getBailoutcards() > 0) {
					if(updater.getUserLeftButtonPressed("Hvordan vil De komme ud af fængslet", "Slå med terningen", "Bruge et løsladelseskort")) {
						//slå med terninger for at komme ud
						roll.throwDice();
						
						updater.setDice(roll.getValue(0), roll.getValue(1));
						
						for(int i = 0; i < 2 ; i++) {
							roll.throwDice();
							updater.setDice(roll.getValue(0), roll.getValue(1));
							
							if(roll.isPair()) {
								updater.showMessage("De har slået et par! \nDe kommer nu ud af fængslet");
								player[turn.getCurrent()].setJailed(false);
								updater.movePiece(player[turn.getCurrent()], (player[turn.getCurrent()].getPosition() + roll.getSum()), player[turn.getCurrent()].getPosition());
								secondTurn = true;
								i = 3;
							}
							else {
								if (i < 2)
									updater.showMessage("De slog ikke et par, men har " + (2-i) + "forsøg tilbage");
								else
									updater.showMessage("De slog stadig ingen par og skal forsat sidde i fængsel til det er Deres tur igen");
							}
						}
						
					}
					else {
						//brug bailoutcard
						player[turn.getCurrent()].setBailoutcards(-1);
						player[turn.getCurrent()].setJailed(false);
						secondTurn = true;
						updater.showMessage("De har benyttet dem af deres benådnings kort og kan nu trave frit rundt igen");
					}
				}
				else {
					if(updater.getUserLeftButtonPressed("Hvordan vil De komme ud af fængslet", "Slå med terningen", "Betal 1000,-")) {
						//slå med terninger for at komme ud
						roll.throwDice();
						updater.setDice(roll.getValue(0), roll.getValue(1));
						
						for(int i = 0; i<=2 ; i++) {
							roll.throwDice();
							updater.setDice(roll.getValue(0), roll.getValue(1));

							if(roll.isPair()) {
								updater.showMessage("De har slået et par! \nDe kommer nu ud af fængslet");
								player[turn.getCurrent()].setJailed(false);
								updater.movePiece(player[turn.getCurrent()], (player[turn.getCurrent()].getPosition() + roll.getSum()), player[turn.getCurrent()].getPosition());
								secondTurn = true;
								i = 3;
							}
							else {
								if(i < 2)
									updater.showMessage("De slog ikke et par, men har " + (2-i) + "forsøg tilbage");
								else
									updater.showMessage("De slog stadig ingen par og skal forsat sidde i fængsel til det er Deres tur igen");
							}
						}
					}
					else{
						//Betal 1000
						updater.showMessage("De har betalt dem ud af fængslet");
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
		updater.close();
	}

	private int getChoice(String str) {
		return Integer.parseInt(str.split("\\. ")[0]);
	}

	
	// Create an array with length of the user input n, and add players to GUI with the names provided via user input
	private void createPlayers(int n) {
		String name;
		Color[] colorSet = { Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.CYAN };

		player = new Player[n];

		for (int i = 0; i < n; i++) {
			name = updater.getUserString("Indtast spiller " + (i + 1) + "'s navn");

			updater.addPlayer(name, startCash, colorSet[i]);
			updater.showMessage(name + " spiller nu med.");

			player[i] = new Player(name, startCash);
			turn.setIndex(i, 0);
		}
	}

	// Count amount of players by user input
	private int countPlayers() {
		int i = updater.getUserInteger("Vælg antal spillere (mindst 2, maks 6).", 2, 6);

		this.turn = new TurnController(i);

		return i;
	}

	
	// For trickering the field mechanics for a specific field
	public void fieldTricker(Player player) {
		// Which field has the player landed on (minus 1, since we're dealing with an array from 0-39)
		board.landOnField((player.getPosition() - 1), player, updater, createAuction);

		// If the player landed on a field, which he couldn't afford landing on
		// then reset his owned fields
		// TODO - correctly losing
		// need to make sure that the player's assets is accounted for and sold (ownable fields are pawned before losing)
		// if these make it possible for him to stay in the game
		if (player.getStatus() < 0) {
			board.resetField(player, updater);
		}
	}
	

	private String[] getAvailableGrounds(Player player) {
		return board.getAvailableGrounds(player);
	}
	
	private void buyProperty(Player player, String choice) {
		board.buyProperty(player, choice);
	}
}
