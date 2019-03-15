
public class Card {

	private String number  ;
	private double balance ;
	private History history ;
	private final static double maxDepositValue  = 30000;  

	
	public Card(String number, double balance,History history) {
		this.number = number;
		this.balance = balance;
		this.history = history;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public String getStringBalance() {
		return "Your Balance is "+Double.toString(balance);
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getMaxDepositValue() {
		return maxDepositValue;
	}

	public boolean deposit (double amount) {
			boolean isLess = true;
			if (amount>maxDepositValue) isLess = false;
			amount = Math.min(amount, maxDepositValue);
			
			double temp = balance + amount;
			history.insert(new Transaction("Deposit", this.balance ,temp, amount ));
			balance= temp;
			return isLess;

	}
	
	public boolean withdraw (double amount) {
		if (balance>=amount) {
			double temp = balance - amount;
			history.insert(new Transaction("Withdraw", this.balance ,temp, amount ));
			balance= temp;
			return true;
		}
		
		return false;
	}
}
