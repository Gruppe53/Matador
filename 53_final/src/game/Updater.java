package game;

import java.awt.Color;

import boundaryToMatador.GUI;

public class Updater {
	
	/**
	 * Updater Constructor
	 * </p>
	 * Constructs updater
	 */
	public Updater(){
		
	}
	
	/**
	 * balance All
	 * @param players - The player array
	 * </p>
	 * Updates the balance on the GUI for all players
	 */
	public void balanceAll(Player[] players){
		
		for(int i = 0 ; i < players.length ; i++ ){
			GUI.setBalance(players[i].getName(), players[i].getAccount());
		}
	}
	
	/**
	 * position All
	 * @param players - The player array
	 * </p>
	 * Updates the position on the GUI for all players
	 */
	public void positionAll(Player[] players){
		for(int i = 0 ; i < players.length ; i++ ) {
			GUI.removeAllCars(players[i].getName());
			GUI.setCar(players[i].getPosition(), players[i].getName());
		}
	}
	
	/**
	 * balance
	 * @param player - The player which you would like to update
	 * </p>
	 * Updates the balance on the GUI for the chosen player
	 */
	public void balance(String name, int account){
		GUI.setBalance(name, account);
	}
	
	/**
	 * position 
	 * @param player - The player which you would like to update
	 * Updates the position on the GUI for the chosen player
	 */
	public void position(int fieldNumber, String name){
		GUI.removeAllCars(name);
		GUI.setCar(fieldNumber, name);
	}
	
	/**
	 * move Piece Tester
	 * @param player - The player which you would like to move
	 * @param newPosition - The position you wish the player to move to
	 * @param currentPosition - The position the player is already on
	 */
	public void movePiece(Player player, int newPosition, int currentPosition) {
		if(newPosition > 40) {
			newPosition -= 40;

			// First move the piece the last steps before hitting START
			for (int f = 1; f <= (40 - currentPosition); f++) {
				GUI.removeAllCars(player.getName());
				GUI.setCar((currentPosition + f), player.getName());
				sleep(300); // When testing, set to 1, or get bored
			}

			// Now move the piece the fields after START
			for (int f = 1; f <= newPosition; f++) {
				if(f == 2){
					player.alterAccount(4000);
					GUI.setBalance(player.getName(), player.getAccount());
				}
				GUI.removeAllCars(player.getName());
				GUI.setCar(f, player.getName());
				sleep(300); // When testing, set to 1, or get bored
			}
		} else {
			// Move the piece the require fields
			for (int f = (currentPosition + 1); f <= newPosition; f++) {
				GUI.removeAllCars(player.getName());
				GUI.setCar(f, player.getName());
				sleep(300); // When testing, set to 1, or get bored
			}
		}

		player.setPosition(newPosition);
	}
	
	/**
	 * sleep
	 * @param n - The amount of time to wait in milliseconds
	 */
	private void sleep(int n) {
		long start, end;

		start = System.currentTimeMillis();

		do {
			end = System.currentTimeMillis();
		} while ((end - start) < (n));
	}
	
	//*************************************************************************************************************************
	// Everything after this point is direct GUI commands
	//*************************************************************************************************************************
	
	/**
	 * create
	 * @param path - the choice of string
	 * </p>
	 * calls GUI.create(path)
	 */
	public void create(String path){
		GUI.create(path);
	}
	
	/**
	 * add Player
	 * @param name - Name of player
	 * @param balance - account of player
	 * @param color - color on the players car
	 * </p>
	 * adds player to GUI
	 */
	public void addPlayer(String name, int balance, Color color){
		GUI.addPlayer(name, balance, color);
	}
	
	/**
	 * set Owner
	 * @param fieldNumber - The choice of field (number on board)
	 * @param name - The choice of player (string name)
	 * </p>
	 * sets Owner on GUI for chosen field to chosen player
	 */
	public void setOwner(int fieldNumber, String name){
		GUI.setOwner(fieldNumber, name);
	}
	
	/**
	 * remove Owner
	 * @param fieldNumber - The choice of field (number on board)
	 * </p>
	 * removes owner on GUI for chosen field
	 */
	public void removeOwner(int fieldNumber){
		GUI.removeOwner(fieldNumber);
	}
	
	/**
	 * remove Car
	 * @param name - The choice of player (string name)
	 *</p>
	 *removes all cars from GUI for the chosen player
	 */
	public void removeCar(String name){
		GUI.removeAllCars(name);
	}
	
	/**
	 * set Houses
	 * @param fieldNumber - The choice of field (number on board)
	 * @param houseCount - amount of houses
	 * </p>
	 * sets the amount of houses on the chosen field
	 */
	public void setHouses(int fieldNumber, int houseCount){
		GUI.setHouses(fieldNumber, houseCount);
	}
	
	/**
	 * set Hotel
	 * @param fieldNumber - The choice of field (number on board)
	 * @param hasHotel - has hotel (true) or not (false)
	 * </p>
	 * sets or remove hotel on the chosen field
	 */
	public void setHotel(int fieldNumber, Boolean hasHotel){
		GUI.setHotel(fieldNumber, hasHotel);
	}
	
	/**
	 * Displays a message to the user and awaits the button pressed response
	 * @param msg - The message that promts the user
	 * @param buttons - A number of strings that should be printed on the buttons the user can press
	 * @return The string from the button that the user pressed
	 */
	public String getUserButtonPressed(String msg, String... buttons){
		return GUI.getUserButtonPressed(msg, buttons);
	}
	
	/**
	 * Shows two dice on the board. The dice will have the specified values, but the placement is random.
	 * @param faceValue1 - int [1:6]
	 * @param faceValue2 - int [1:6]
	 * (If a parameter is out of bounds nothing will happen!) Uses random rotation.
	 */
	public void setDice(int faceValue1, int faceValue2){
		GUI.setDice(faceValue1, faceValue2);
	}
	
	/**
	 * Displays a message to the user and awaits the integer response. Only values between min and max are allowed.
	 * @param msg - The message that promts the user
	 * @param min - The minimum value the user is allowed to enter
	 * @param max - The maximum value the user is allowed to enter
	 * @return The integer that the user selected.
	 */
	public int getUserInteger(String msg, int min, int max){
		return GUI.getUserInteger(msg, min, max);
	}
	
	/**
	 * Displays a message to the user and awaits the boolean response.
	 * @param msg - The message that promts the user
	 * @param trueButton - The text that should appear on the left button
	 * @param falseButton - The text that should appear on the right button
	 * @return True if the left button is pressed by the user. False otherwise.
	 */
	public boolean getUserLeftButtonPressed(String msg, String trueButton, String falseButton){
		return GUI.getUserLeftButtonPressed(msg, trueButton, falseButton);
	}
	
	/**
	 * Displays a message to the user.
	 * @param msg - The message to print
	 */
	public void showMessage(String msg){
		GUI.showMessage(msg);
	}
	
	/**
	 * Displays a message to the user and awaits the response.
	 * @param msg - The message that promts the user
	 * @return The string that the user has entered
	 */
	public String getUserString(String msg){
		return GUI.getUserString(msg);
	}
	
	/**
	 * close
	 * </p>
	 * closes GUI
	 */
	public void close(){
		GUI.close();
	}
	
	/**
	 * Displays a dropdown to the user and awaits the response.
	 * @param msg - The message which prompts the user
	 * @param options - An array with possible options to choose between from the dropdown.
	 * @return
	 */
	public String getUserSelection(String msg, String[] options) {
		return GUI.getUserSelection(msg, options);
	}
}
