package libcafe;

import java.util.LinkedList;
import java.util.List;

public class BorrowerList {

	List<Borrower> borrowers = new LinkedList<Borrower>();
	List<BorrowerListListener> listeners = new LinkedList<BorrowerListListener>();

	public void add(Borrower bwer) {
		borrowers.add(bwer);
		notifyAdded(bwer);
	}

	public void remove(Borrower bwer) {
		borrowers.remove(bwer);
		notifyRemoved(bwer);

	}

	public int size() {
		return borrowers.size();
	}

	public void addListener(BorrowerListListener borrowerListListener) {
		listeners.add(borrowerListListener);
	}

	private void notifyAdded(Borrower bwer) {
		for (BorrowerListListener listener : listeners) {
			listener.borrowerAdded(bwer);
		}
	}

	private void notifyRemoved(Borrower bwer) {
		for (BorrowerListListener listener : listeners) {
			listener.borrowerRemoved(bwer);
		}
	}

}
