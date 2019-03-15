
public class Transaction {

	private String type;
	private double balanceBefore;
	private double balanceAfter;
	private double amount;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalanceBefore() {
		return balanceBefore;
	}

	public void setBalanceBefore(double balanceBefore) {
		this.balanceBefore = balanceBefore;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(double balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	@Override
	public String toString() {
		return "Transaction type: " + type + "\nAmount: " + amount + "\nBalanceBefore: " + balanceBefore
				+ "\nBalanceAfter: " + balanceAfter + "\n";
	}

	public Transaction(String type, double balanceBefore, double balanceAfter, double amount) {
		this.type = type;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		this.amount = amount;
	}

}
