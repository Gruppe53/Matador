package game;

import boundaryToMatador.GUI;

public class Board {
	private Field[] boardArray = new Field[40];
		
	public Board() {
		boardArray[0] = new Start("Start");
		boardArray[1] = new Street("Rødovrevej", 1200, 1, 50, 250, 750, 2250, 4000, 6000, 1000, 2000);
		boardArray[2] = new Chance("Create");
		boardArray[3] = new Street("Hvidovrevej", 1200, 1, 50, 250, 750, 2250, 4000, 6000, 1000, 2000);
		boardArray[4] = new Tax("Indkomstskat", 0);
		boardArray[5] = new Fleet("Helsingør-Helsingborg", 4000);
		boardArray[6] = new Street("Roskildevej", 2000, 2, 100, 600, 1800, 5400, 8000, 11000, 1000, 2000);
		boardArray[7] = boardArray[2];
		boardArray[8] = new Street("Valby Langgade", 2000, 2, 100, 600, 1800, 5400, 8000, 11000, 1000, 2000);
		boardArray[9] = new Street("Allégade", 2400, 2, 150, 800, 2000, 6000, 9000, 12000, 1000, 2000);
		boardArray[10] = new VisitJail("Fængsel"); 
		boardArray[11] = new Street("Frederiksberg Allé", 2800, 3, 200, 1000, 3000, 9000, 12500, 15000, 1000, 2000);
		boardArray[12] = new Brewery("Tuborg", 3000, 100);
		boardArray[13] = new Street("Bülowsvej", 2800, 3, 200, 1000, 3000, 9000, 12500, 15000, 1000, 2000);
		boardArray[14] = new Street("Gl. Kongevej", 3200, 3, 250, 1250, 3750, 10000, 14000, 18000, 1000, 2000);
		boardArray[15] = new Fleet("Mols-Linien", 4000);
		boardArray[16] = new Street("Bernstorffsvej", 3600, 4, 300, 1400, 4000, 11000, 15000, 19000, 1000, 2000);
		boardArray[17] = boardArray[2];
		boardArray[18] = new Street("Hellerupvej", 3600, 4, 300, 1400, 4000, 11000, 15000, 19000, 1000, 2000);
		boardArray[19] = new Street("Strandvejen", 4000, 4, 350, 1600, 4400, 12000, 16000, 20000, 1000, 2000);
		boardArray[20] = new Parking("Parkering");
		boardArray[21] = new Street("Trianglen", 4400, 5, 350, 1800, 5000, 14000, 17500, 21000, 1000, 2000);
		boardArray[22] = boardArray[2];
		boardArray[23] = new Street("Østerbrogade", 4400, 5, 350, 1800, 5000, 14000, 17500, 21000, 1000, 2000);
		boardArray[24] = new Street("Grønningen", 4800, 5, 400, 2000, 6000, 15000, 18500, 22000, 1000, 2000);
		boardArray[25] = new Fleet("Gedser-Rostock", 4000);
		boardArray[26] = new Street("Bredgade", 5200, 6, 450, 2200, 6600, 16000, 19500, 23000, 1000, 2000);
		boardArray[27] = new Street("Kgs. Nytorv", 5200, 6, 450, 2200, 6600, 16000, 19500, 23000, 1000, 2000);
		boardArray[28] = new Brewery("Carlsberg", 3000, 100);
		boardArray[29] = new Street("Østergade", 5600, 6, 500, 2400, 7200, 17000, 20500, 24000, 1000, 2000);
		boardArray[30] = new GoToJail("Ryk i fængsel");
		boardArray[31] = new Street("Amagertorv", 6800, 7, 550, 2600, 7800, 18000, 22000, 25000, 1000, 2000);
		boardArray[32] = new Street("Vimmelskaftet", 6000, 7, 550, 2600, 7800, 18000, 22000, 25000, 1000, 2000);
		boardArray[33] = boardArray[2];
		boardArray[34] = new Street("Nygade", 6400, 7, 600, 3000, 9000, 20000, 24000, 28000, 1000, 2000);
		boardArray[35] = new Fleet("Rødby-Puttgarden", 4000);
		boardArray[36] = boardArray[2];
		boardArray[37] = new Street("Frederiksberg", 7000, 8, 700, 3500, 10000, 22000, 26000, 30000, 1000, 2000);
		boardArray[38] = new Tax("Statsskat",1);
		boardArray[39] = new Street("Rådhuspladsen", 8000, 8, 1000, 40000, 12000, 28000, 34000, 40000, 1000, 2000);
	}
	
	public String getName(int i) {
		return boardArray[i].getName();
	}
	
	public void landOnField(int i, Player player) {
		System.out.println("3");
		boardArray[i].landOnField(player);
	}
	

	public void resetField(Player player) {
		for (int i = 0; i < boardArray.length; i++) {
			if (boardArray[i] instanceof Ownable) {
				if (((Ownable) boardArray[i]).isOwner(player)) {
					((Ownable) boardArray[i]).resetOwner();

					GUI.removeOwner((i + 1));
				}
			}
		}
	}
	
	public Street[] getAvailableGrounds(Player player) {
		int count = 0;
		
		for(int i = 0; i < boardArray.length; i++) {
			if(boardArray[i] instanceof Street) {
				if(((Street) boardArray[i]).isOwner(player)) {
					count = i;
				}
			}
		}
		
		Street[] available = new Street[count];
		
		for(int i = 0; i < count; i++) {
			if(boardArray[i] instanceof Street) {
				if(((Street) boardArray[i]).isOwner(player)) {
					available[i] = (Street) boardArray[i];
				}
			}
		}
		
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// FIIIICUUCCUUUUUUUUUUUUUUUUUUCUCUUCUCUUUUUCUUU uUuUUuUUUUUuUUUCUCJCKKCKCKCK!!!!!!!!!!!!!!!!!!!!!!!!!
		// .... kristin signing off
		
		return available;
	}

	public void buildHouseHotel(Player player, Street[] available) {
		HouseHotelControl build = new HouseHotelControl(player, available);
	}
}
