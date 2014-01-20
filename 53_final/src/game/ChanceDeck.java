package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class ChanceDeck extends Field {
	private ChanceCard[] deck = new ChanceCard[34];
	private int cardCount = 0;
	private int pickCount = 0;
	private String name;
	
	// FINALS
	private final File file = new File("materials/chance.txt");
	
	/**
	 * Chance Deck Constructor
	 * @param name - The name of The ChanceDeck (unused)
	 */
	public ChanceDeck(String name) {
		super(name);
		
		createCards();
		shuffleDeck(deck);
		}
	
	/**
	 * land On Field
	 * @param player - The choice of player
	 * @param updater - The Updater
	 */
	public void landOnField(Player player, Updater updater) {
		updater.showMessage("Træk et \"prøv lykken\"-kort");
		updater.showMessage(deck[pickCount].getDescription());
        
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
			player.setPosition(newPosition);
			
			// TODO - Virker IKKE!
			// Hvis "ryk tre felter tilbage" traekkes, og man staar paa felt 2, fikser vi lige manuelt position
			if(newPosition == -1) {
				newPosition = 40;
				player.setPosition(newPosition - 1);
			}
			
			updater.position(newPosition, player.getName());
			
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
				player.setPosition(11);
			}
			updater.position(Integer.parseInt(deck[pickCount].getAction()), player.getName());
			
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
		
		setPickCount();
	}
	
	/**
	 * create Cards
	 * </p>
	 * creates every single card (ChanceCard)
	 */
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
	
	/**
	 * shuffle Deck
	 * @param deck - The coice of ChanceCard array
	 * </p>
	 * suffle the ChanceCard array 
	 */
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
	
	/**
	 * get Desc
	 * @param i - The choice of ChanceCard (from array)
	 * @return Description of the chosen ChanceCard
	 */
	public String getDesc(int i) {
		return deck[i].getDescription();
	}
	
	/**
	 * get Type
	 * @param i - The choice of ChanceCard (from array)
	 * @return Type of the chosen ChanceCard
	 */
	public String getType(int i) {
		return deck[i].getType();
	}
	
	/**
	 * get Action
	 * @param i - The choice of ChanceCard (from array)
	 * @return Action of the chosen ChanceCard
	 */
	public String getAction(int i) {
		return deck[i].getAction();
	}
	
	/**
	 * set Pick Count
	 * adds 1 to pickCount.
	 * </p>
	 * Shuffles deck and reset pickCount if there is no more cards left
	 */
	public void setPickCount() {
		this.pickCount++;
		
		if(this.cardCount <= this.pickCount) {
			shuffleDeck(this.deck);
			this.pickCount = 0;
		}
	}

	/**
	 * read Lines
	 * @return String with content of all ChanceCards from .txt file
	 * </p>
	 * Used to create cards
	 */
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
	
	/**
	 * get Name
	 * @return Name of ChanceDeck (unused)
	 */
	public String getName() {
		return this.name;
	}
}
