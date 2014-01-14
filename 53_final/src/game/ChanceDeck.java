package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import boundaryToMatador.GUI;

public class ChanceDeck extends Field {
	private ChanceCard[] deck = new ChanceCard[37];
	private int cardCount = 0;
	private int pickCount = 0;
	private String name;
	
	// FINALS
	private final File file = new File("materials/chance.txt");
	
	public ChanceDeck(String name) {
		super(name);
		
		createCards();
		shuffleDeck(deck);
		
		for(int i = 0; i < deck.length; i++)
			System.out.println(deck[i].getDescription());
		}
	
	public void landOnField(Player player) {
		GUI.showMessage("Tr\u00F6k et \"pr\uF008Fv lykken\"-kort");
		GUI.showMessage(deck[pickCount].getDescription());
        
		switch(Integer.parseInt(deck[pickCount].getType())) {
		case 0:
			// Matadorlegat
			break;
		case 1:
			// Modtag penge
			player.alterAccount(Integer.parseInt(deck[pickCount].getAction()));
			break;
		case 2:
			// Modtag penge, af hver spiller
			// Skal lige........... umiddelbart ret svaer at lave, uden at bryde alle designprincipper
			break;
		case 3:
			// Betal penge
			player.alterAccount(Integer.parseInt(deck[pickCount].getAction()));
			break;
		case 4:
			// Ryk brik
			int newPosition = player.getPosition() + Integer.parseInt(deck[pickCount].getAction());
			
			// Hvis "ryk tre felter tilbage" traekkes, og man staar paa felt 2, fikser vi lige manuelt position
			if(newPosition == -1) {
				newPosition = 40;
			}
			
			GUI.removeAllCars(player.getName());
			GUI.setCar(newPosition, player.getName());
			
			break;
		case 5:
			// Ryk direkte
			// Naar man traekker disse kort, og man skal rykke til et felt, og det felt er ejet, har vi et problem
			// - selv hvis det IKKE er ejet, kan det ikke tilbydes til spilleren...
			// Vi har nemlig fra ChanceDeck ingen kendskab til hverken de andre fields eller til andre spillere,
			// og vi kan ikke kalde landOnField her fra
			// - samme problem som med "Modtag penge, af hver spiller", hvor de andre spillere ikke er bekendte
			
			
			if(Integer.parseInt(deck[pickCount].getAction()) == 11) {
				// Ryk i faengsel - kan i det mindste laves... ^_^
				player.setJailed(true);
			}
			
			GUI.removeAllCars(player.getName());
			GUI.setCar(Integer.parseInt(deck[pickCount].getAction()), player.getName());
			break;
		case 6:
			// Ryk direkte og betal
			// Igen... ingen kendskab...
			break;
		case 7:
			// Ryk til rederi
			// Ingen kendskab.
			break;
		case 8:
			// Betal pr. hus/hotel
			String[] str = deck[pickCount].getAction().split("xx");
			int houses = Integer.parseInt(str[0]);
			int hotels = Integer.parseInt(str[1]);
			
			player.alterAccount(-(player.getHouses() * houses));
			player.alterAccount(-(player.getHotels() * hotels));
			break;
		case 9:
			// Bail
			player.setBailoutcards(1);
			break;
		default:
				break;
		}
	}
	
	private void createCards() {
		String str = readLines();
		
		for(String field : str.split("\\|\\|")) {
			String[] attributes = field.split(";;");
			
			String a = attributes[0].split("::")[1];
			String b = attributes[1].split("::")[1];
			String c = attributes[2].split("::")[1];
			String d = attributes[3].split("::")[1];
			
			if(Integer.parseInt(d) > 1) {
				for(int i = 0; i < 2; i++) {
					deck[cardCount] = new ChanceCard(a, b, c);
					
					cardCount++;
				}
			}
			else {
				deck[cardCount] = new ChanceCard(a, b, c);
				
				cardCount++;
			}
		}
	}
	
	public void shuffleDeck(ChanceCard[] deck) {
		Random rnd = new Random();
	    
		for (int i = deck.length - 1; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      
	      // Simple swap
	      ChanceCard a = deck[index];
	      deck[index] = deck[i];
	      deck[i] = a;
	    }
	}
	
	public String getDesc(int i) {
		return deck[i].getDescription();
	}
	
	public String getType(int i) {
		return deck[i].getType();
	}
	
	public String getAction(int i) {
		return deck[i].getAction();
	}
	
	public void setPickCount() {
		this.pickCount++;
		
		if(this.cardCount <= this.pickCount) {
			shuffleDeck(this.deck);
			this.pickCount = 0;
		}
	}

	private String readLines() {
		String content = "";
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			String line = null;
			while ((line = in.readLine()) != null) {
				if(line.trim().indexOf("#") == 0)
				    continue;
				content += line.trim();
			}
			
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
}
