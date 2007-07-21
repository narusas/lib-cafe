package libcafe;

import java.util.LinkedList;
import java.util.List;

import libcafe.ui.BorrowerListModel;

public class BorrowerList implements BorrowerListener {

	List<Borrower> borrowers = new LinkedList<Borrower>();
	List<BorrowerListListener> listeners = new LinkedList<BorrowerListListener>();

	public void add(Borrower bwer) {
		bwer.addListener(this);
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


	private void notifyChanged(Borrower bwer) {
		for (BorrowerListListener listener : listeners) {
			listener.borrowerChanged(bwer);
		}
	}

	
	public Borrower get(int index) {

		return borrowers.get(index);
	}

	@Override
	public void borrowerChanged(Borrower bwer) {
		notifyChanged(bwer);
	}
}
