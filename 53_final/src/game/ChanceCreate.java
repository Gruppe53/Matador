package game;

import java.io.*;
import java.util.ArrayList;

public class ChanceCreate {
	private File file;
	private String[] card;
	private int count;
	public static ArrayList<Field> fields = null;
	private enum Type {
		MATADORLEGAT, MODTAGPENGEBANK, MODTAGPENGESPILLER, BETALPENGE,
		BEVAEGANTALFELTER, BEVAEGTILFELT, BEVAEGTILFELTOGINDKASSER,
		BEVAEGTILNAESTEOGINDKASSER, BETALHUS, BENAADNING
    }
	
	public ChanceCreate(File file) {
		this.file = file;
		this.count = 0;
		
		this.readLines();
		this.shuffleDeck();
	}
	
	private boolean parse() {
        String str = readLines();
        
        if (str == null) {
            return false;
        }
        
        // 75% > 40 so increase in capacity is required
        fields = new ArrayList<Field>(54);
        
        for (String field : str.split("\\|\\|")) {
            String[] attributes = field.split(";;");
            // determine type
            String type = valueOf("type", attributes);
            // Verify type
            Type T = validType(type);
            if (T == null) {
                return false;
            }
            
            // type0: matadorlegat
            // type1: modtag penge fra bank
            // type2: modtag penge fra medspillere
            // type3: betal penge
            // type4: bev�g antal felter
            // type5: bev�g til placering
            // type6: bev�g til placering og indkasser
            // type7: bev�g til n�ste og indkasser, betal, k�b
            // type8: betal for huse/hoteller
            // type9: ben�dning
            
            switch(T) {
                case MATADORLEGAT:
                    createGrant(attributes);
                    break;
                case MODTAGPENGEBANK:
                    createRecieval(/* attributes */);
                    break;
                case MODTAGPENGESPILLER:
                    createPlayerRecieval(attributes);
                    break;
                case BETALPENGE:
                    createPayment(attributes);
                    break;
                case BEVAEGANTALFELTER:
                    createMovement(attributes);
                    break;
                case BEVAEGTILFELT:
                    createSpecificMovement(attributes);
                    break;
                case BEVAEGTILFELTOGINDKASSER:
                    createSpecificMovementAndRecieve(attributes);
                    break;
                case BEVAEGTILNAESTEOGINDKASSER:
                    createNextPlacement(attributes);
                    break;
                case BETALHUS:
                	createHousePayment(attributes);
                    break;
                case BENAADNING:
                	createPardon(attributes);
                	break;
                default:
                	break;
            }
        }
        return true;
    }

	private void createPardon(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createHousePayment(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createNextPlacement(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createSpecificMovementAndRecieve(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createSpecificMovement(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createMovement(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createPayment(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createPlayerRecieval(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void createRecieval() {
		// TODO Auto-generated method stub
		
	}

	private void createGrant(String[] attributes) {
		// TODO Auto-generated method stub
		
	}

	private void shuffleDeck() {
		// TODO Auto-generated method stub
		
	}
	
	private void createDeck(String line) {
		card[count] = line;
		count++;
	}

	public String getCard(int i) {
		return this.card[i];
	}
	
	public int getDeckSize() {
		return this.card.length;
	}
	
	private String readLines() {
		String content = "";
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = in.readLine()) != null) {
				content += line.trim() + "\n";
			}
			
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	private Type validType(String type) {
        for (Type t : Type.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }
	
	private String valueOf(String label, String[] attributes) {
        for (String a : attributes) {
            if (a.split("::")[0].trim().equalsIgnoreCase(label)) {
                return a.split("::")[1];
            }
        }
        System.err.println("GUI - Missing attribute: " + label);
        return null;
    }
}
