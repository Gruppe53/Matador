package game;

import boundaryToMatador.GUI;

public class Board {
	private Field[] boardArray = new Field[40];
	
	public Board() {
		boardArray[0] = new Start("Start"); // Fixet
		boardArray[1] = new Street("RÃ¸dovrevej", 1200, 1, 50, 250, 750, 2250, 4000, 6000);
		boardArray[2] = new Chance("Chance"); // Bare kald dem alle "Chance"
		boardArray[3] = new Street("Hvidovrevej", 1200, 1, 50, 250, 750, 2250, 4000, 6000);
		boardArray[4] = new Tax("Indkomstskat", 0);
		boardArray[5] = new Fleet("Helsingør-Helsingborg", 4000, 500);
		boardArray[6] = new Street("Roskildevej", 2000, 2, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[7] = new Chance("Chance");
		boardArray[8] = new Street("Valby Langgade", 2000, 2, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[9] = new Street("Allégade", 2400, 2, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[10] = new VisitJail(); // Fixet
		boardArray[11] = new Street("Frederiksberg Allé", 2800, 3, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[12] = new Brewery("Tuborg", 3000, int rent);
		boardArray[13] = new Street("Bülowsvej", 2800, 3, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[14] = new Street("Gl. Kongevej", 3200, 3, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[15] = new Fleet("Mols-Linien", 4000, 500);
		boardArray[16] = new Street("Bernstorffsvej", 3600, 4, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[17] = new Chance("Chance");
		boardArray[18] = new Street("Hellerupvej", 3600, 4, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[19] = new Street("Strandvejen", 4000, 4, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[20] = new Parking(); // Fixet
		boardArray[21] = new Street("Trianglen", 4400, 5, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[22] = new Chance("Chance");
		boardArray[23] = new Street("Østerbrogade", 4400, 5, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[24] = new Street("Grønningen", 4800, 5, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[25] = new Fleet("Gedser-Rostock", 4000, 500);
		boardArray[26] = new Street("Bredgade", 5200, 6, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[27] = new Street("Kgs. Nytorv", 5200, 6, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[28] = new Brewery("Carlsberg", 3000, int rent);
		boardArray[29] = new Street("Østergade", 5600, 6, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[30] = new GoToJail(); // Fixet
		boardArray[31] = new Street("Amagertorv", 6800, 7, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[32] = new Street("Vimmelskaftet", 6000, 7, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[33] = new Chance("Chance");
		boardArray[34] = new Street("Nygade", 6400, 7, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[35] = new Fleet("Rødby-Puttgarden", 4000, 500);
		boardArray[36] = new Chance("Chance");
		boardArray[37] = new Street("Frederiksberg", 7000, 8, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
		boardArray[38] = new Tax("Statsskat",1);
		boardArray[39] = new Street("Rådhuspladsen", 8000, 8, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5);
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
