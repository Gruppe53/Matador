package game;

import boundaryToMatador.GUI;

public class Board {
	private Field[] boardArray = new Field[40];
	
	public Board() {
		boardArray[0] = new Start("Start"); // Fixet
		boardArray[1] = new Street("Rødovrevej", 1200, 1, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[2] = new Chance("Chance"); // Bare kald dem alle "Chance"
		boardArray[3] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[4] = new Tax(String Name, int taxType);
		boardArray[5] = new Fleet(String Name, int Price, int Rent);
		boardArray[6] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[7] = new Chance(String name);
		boardArray[8] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[9] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[10] = new VisitJail(); // Fixet
		boardArray[11] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[12] = new Brewery(String name, int price, int rent);
		boardArray[13] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[14] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[15] = new Fleet(String Name, int Price, int Rent);
		boardArray[16] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[17] = new Chance(String name);
		boardArray[18] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[19] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[20] = new Parking(); // Fixet
		boardArray[21] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[22] = new Chance(String name);
		boardArray[23] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[24] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[25] = new Fleet(String Name, int Price, int Rent);
		boardArray[26] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[27] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[28] = new Brewery(String name, int price, int rent);
		boardArray[29] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[30] = new GoToJail(); // Fixet
		boardArray[31] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[32] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[33] = new Chance(String name);
		boardArray[34] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[35] = new Fleet(String Name, int Price, int Rent);
		boardArray[36] = new Chance(String name);
		boardArray[37] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[38] = new Tax(String Name,int taxType);
		boardArray[39] = new Street(String name, int price, int cType, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
	}
	
	public String getName(int i) {
		return boardArray[i].getName();
	}

	public void landOnField(int i, Player p) {
		boardArray[i].landOnField(p);
	}

	public void resetField(Player player) {
		for (int i = 0; i < 21; i++) {
			if (boardArray[i] instanceof Ownable) {
				if (((Ownable) boardArray[i]).isOwner(player)) {
					((Ownable) boardArray[i]).resetOwner();
				}
			}
		}
	}
}
