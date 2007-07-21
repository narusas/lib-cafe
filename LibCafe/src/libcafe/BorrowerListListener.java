package libcafe;

public interface BorrowerListListener {

	void borrowerAdded(Borrower borrower);

	void borrowerRemoved(Borrower borrower);
	
	void borrowerChanged(Borrower borrower);

}
