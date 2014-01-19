package game;

import java.awt.Color;
import java.lang.Exception;

public class GameController {
	private Player[] player;
	private Updater updater = new Updater();
	private TurnController turn;
	private Board board = new Board();
	private Dice roll = new Dice(1, 6, 2);
	private boolean secondTurn = false;
	private int multiplePair = 0;
	
	// FINALS
	private final int startCash = 30000;
	private final String path = "materials/fields.txt"; 

	public GameController() {
		// Create board with proper names and descriptions
		updater.create(path);

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
				
					// Assign position-values
					int currentPosition = player[turn.getCurrent()].getPosition();
					int newPosition = currentPosition + roll.getSum();
					
					// Draw the roll
					updater.setDice(roll.getValue(0), roll.getValue(1));

					// Move the piece smoothly
					updater.movePiece(player[turn.getCurrent()], newPosition, currentPosition);
					
					player[turn.getCurrent()].setRollSum(roll.getSum());

					// Get an extra turn if dices are pair
					if(roll.isPair()){
						secondTurn = true;
						multiplePair++;
						if(multiplePair >= 3){
							player[turn.getCurrent()].setJailed(true);
							player[turn.getCurrent()].setPosition(11);
							updater.position(player[turn.getCurrent()].getPosition(), player[turn.getCurrent()].getName());
							updater.showMessage("De slog to ens tre gange i streg, og ryger direkte i fængsel.");
							break;
						}
					}
					
					// Make the mechanics of the field start
					if(multiplePair != 3)
						fieldTricker(player[turn.getCurrent()]);
					// If Ownable and didn't buy it, run an Auction 
					try{
						if(board.getField(player[turn.getCurrent()].getPosition()-1).getCreateAuction()){
							int auctionField = (player[turn.getCurrent()].getPosition()-1);
							Ownable f = board.getField(auctionField);
							Auction auction = new Auction(updater, player[turn.getCurrent()], player, f);
							auction.runAction();
							auction = null;
						}
					} 
					catch(Exception VisitJail){
						// TODO Exception for Auction, ved ikke hvorfor.... men det virker ! :D
					}
					break;
				case 2:
					str = updater.getUserButtonPressed("Hvad vil De foretage dem?", "1. Byg huse/hoteller", "2. Vend tilbage til spilmenu");
					
					switch(getChoice(str)) {
					case 1:
						board.buyProperty(player[turn.getCurrent()], updater.getUserSelection("Hvilken grund vil De købe hus/hotel til?", board.getAvailableGrounds(player[turn.getCurrent()])), updater);
						break;
					default:
						break;
					}
					
					secondTurn = true;
					break;
				case 3:
					str = updater.getUserButtonPressed("Hvad vil De foretage dem?", "1. Byg huse/hoteller", "2. Vend tilbage til spilmenu");
					
					switch(getChoice(str)) {
					case 1:
						board.sellProperty(player[turn.getCurrent()], updater.getUserSelection("Hvilken grund vil De sælge fra?", board.getPropertyGrounds(player[turn.getCurrent()])), updater);
						break;
					default:
						break;
					}
					updater.getUserInteger("Hvilken grund vil De sælge fra?\n"
							+ "Udskriv liste med grunde, som brugeren ejer, og som har huse/hoteller...",
							0,
							2
					);
					
					secondTurn = true;
					break;
				case 4:
					str = updater.getUserButtonPressed("Hvad vil De foretage dem?", "1. Pantsæt grunde", "2. Tilbagekøb pantsat grund", "3. Vend tilbage til spilmenu");
					
					switch(getChoice(str)) {
					case 1:
						board.pawnProperty(player[turn.getCurrent()], updater.getUserSelection("Hvilken grund vil De pantsætte?", board.getAvailablePawns(player[turn.getCurrent()], true)), updater, true);
						break;
					case 2:
						board.pawnProperty(player[turn.getCurrent()], updater.getUserSelection("Hvilken grund vil De gerne tilbagekøbe?", board.getAvailablePawns(player[turn.getCurrent()], false)), updater, false);
						break;
					default:
						break;
					}
					
					secondTurn = true;
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
						//Roll dice to bail out
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
						//Use bail out card
						player[turn.getCurrent()].setBailoutcards(-1);
						player[turn.getCurrent()].setJailed(false);
						secondTurn = true;
						updater.showMessage("De har benyttet dem af deres benådnings kort og kan nu trave frit rundt igen");
					}
				}
				else {
					if(updater.getUserLeftButtonPressed("Hvordan vil De komme ud af fængslet", "Slå med terningen", "Betal 1000,-")) {
						//Roll dice to bail out
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
						//Pay 1000 to bail out
						updater.showMessage("De har betalt dem ud af fængslet");
						player[turn.getCurrent()].setJailed(false);
						player[turn.getCurrent()].alterAccount(-1000);
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
		board.landOnField((player.getPosition() - 1), player, updater);

		// If the player landed on a field, which he couldn't afford landing on
		// then reset his owned fields
		// TODO - correctly losing
		// need to make sure that the player's assets is accounted for and sold (ownable fields are pawned before losing)
		// if these make it possible for him to stay in the game
		if (player.getStatus() < 0) {
			board.resetField(player, updater);
			turn.setIndex(-1, turn.getCurrent());
		}
	}
}
