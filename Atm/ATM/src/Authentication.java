import java.util.HashMap;

public class Authentication {

	private HashMap<String,Card> dataBase;

	public Authentication() {
		dataBase = new HashMap<>();
		dataBase.put("1234", new Card("1234", 0, new History(5)));
	}
	
	public Card check (String cardNumber){
		return dataBase.get(cardNumber);
	}
	
	
	
}
