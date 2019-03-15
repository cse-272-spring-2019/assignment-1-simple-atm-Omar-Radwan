
public class History {

	private int start;
	private int end;
	private int size;
	private int lastTransaction;
	private int maxSize;

	Transaction[] transactionsHistory;

	History(int maxSize) {
		this.maxSize = maxSize;
		start = 0;
		end = -1;
		size = 0;
		lastTransaction = 0;
		transactionsHistory = new Transaction[maxSize];
	}

	public void insert(Transaction transaction) {
		if (size < maxSize)
			size++;
		else
			start = (start + 1) % maxSize;

		end = (end + 1) % maxSize;
		transactionsHistory[end] = transaction;

	}

	public Transaction getNext() {
		if (end == -1)
			return null;
		else if (lastTransaction == end) {
			return transactionsHistory[end];
		} else if (lastTransaction == -1) {
			lastTransaction = end;
			return transactionsHistory[lastTransaction];
		} else {
			lastTransaction = (lastTransaction + 1) % maxSize;
			return transactionsHistory[lastTransaction];
		}
	}

	public Transaction getPrevious() {
		if (end == -1)
			return null;
		else if (lastTransaction == start) {
			return transactionsHistory[lastTransaction];
		}

		else if (lastTransaction == -1) {
			lastTransaction = end;
			return transactionsHistory[lastTransaction];
		}

		else {
			lastTransaction = (maxSize + lastTransaction - 1) % maxSize;
			return transactionsHistory[lastTransaction];
		}

	}

	public void resetHistoryToLast() {
		lastTransaction = -1;
	}

	public int getTransactionNumber() {
		if (lastTransaction == -1) {
			return (end - start + maxSize) % maxSize;
		}
		return (lastTransaction - start + maxSize) % maxSize + 1;
	}
}
