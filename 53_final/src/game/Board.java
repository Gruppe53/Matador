package game;

public class Board {
	private Field[] boardArray = new Field[40];
	
	public Board() {
		boardArray[0] = new Start("Start");
		boardArray[1] = new Street("R&oslash;dovrevej", 1200, 1, 50, 250, 750, 2250, 4000, 6000);
		boardArray[2] = new Chance("Chance");
		boardArray[3] = new Street("Hvidovrevej", 1200, 1, 50, 250, 750, 2250, 4000, 6000);
		boardArray[4] = new Tax("Indkomstskat", 0);
		boardArray[5] = new Fleet("Helsing&oslash;r-Helsingborg", 4000);
		boardArray[6] = new Street("Roskildevej", 2000, 2, 100, 600, 1800, 5400, 8000, 11000);
		boardArray[7] = new Chance("Chance");
		boardArray[8] = new Street("Valby Langgade", 2000, 2, 100, 600, 1800, 5400, 8000, 11000);
		boardArray[9] = new Street("Allegade", 2400, 2, 150, 800, 2000, 6000, 9000, 12000);
		boardArray[10] = new VisitJail("F&aelig;ngsel"); 
		boardArray[11] = new Street("Frederiksberg Alle", 2800, 3, 200, 1000, 3000, 9000, 12500, 15000);
		boardArray[12] = new Brewery("Tuborg", 3000, 100);
		boardArray[13] = new Street("B&uuml;lowsvej", 2800, 3, 200, 1000, 3000, 9000, 12500, 15000);
		boardArray[14] = new Street("Gl. Kongevej", 3200, 3, 250, 1250, 3750, 10000, 14000, 18000);
		boardArray[15] = new Fleet("Mols-Linien", 4000);
		boardArray[16] = new Street("Bernstorffsvej", 3600, 4, 300, 1400, 4000, 11000, 15000, 19000);
		boardArray[17] = new Chance("Chance");
		boardArray[18] = new Street("Hellerupvej", 3600, 4, 300, 1400, 4000, 11000, 15000, 19000);
		boardArray[19] = new Street("Strandvejen", 4000, 4, 350, 1600, 4400, 12000, 16000, 20000);
		boardArray[20] = new Parking("Parkering");
		boardArray[21] = new Street("Trianglen", 4400, 5, 350, 1800, 5000, 14000, 17500, 21000);
		boardArray[22] = new Chance("Chance");
		boardArray[23] = new Street("&Oslash;sterbrogade", 4400, 5, 350, 1800, 5000, 14000, 17500, 21000);
		boardArray[24] = new Street("Gr&oslash;nningen", 4800, 5, 400, 2000, 6000, 15000, 18500, 22000);
		boardArray[25] = new Fleet("Gedser-Rostock", 4000);
		boardArray[26] = new Street("Bredgade", 5200, 6, 450, 2200, 6600, 16000, 19500, 23000);
		boardArray[27] = new Street("Kgs. Nytorv", 5200, 6, 450, 2200, 6600, 16000, 19500, 23000);
		boardArray[28] = new Brewery("Carlsberg", 3000, 100);
		boardArray[29] = new Street("&Oslash;stergade", 5600, 6, 500, 2400, 7200, 17000, 20500, 24000);
		boardArray[30] = new GoToJail("Ryk i f&aelig;ngsel");
		boardArray[31] = new Street("Amagertorv", 6800, 7, 550, 2600, 7800, 18000, 22000, 25000);
		boardArray[32] = new Street("Vimmelskaftet", 6000, 7, 550, 2600, 7800, 18000, 22000, 25000);
		boardArray[33] = new Chance("Chance");
		boardArray[34] = new Street("Nygade", 6400, 7, 600, 3000, 9000, 20000, 24000, 28000);
		boardArray[35] = new Fleet("R&oslash;dby-Puttgarden", 4000);
		boardArray[36] = new Chance("Chance");
		boardArray[37] = new Street("Frederiksberg", 7000, 8, 700, 3500, 10000, 22000, 26000, 30000);
		boardArray[38] = new Tax("Statsskat",1);
		boardArray[39] = new Street("R&aring;dhuspladsen", 8000, 8, 1000, 40000, 12000, 28000, 34000, 40000);
	}
	
	public String getName(int i) {
		return boardArray[i].getName();
	}

	public void goToField(int i, Player player) {
		boardArray[i].landOnField(player);
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
