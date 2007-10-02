package libcafe;

import java.util.LinkedList;
import java.util.List;

public class Borrower extends BookList {
	List<BorrowerListener> listeners = new LinkedList<BorrowerListener>();

	private String name;
	
	public Borrower() {
		super();
	}

	public Borrower(int id) {
		super(id);
	}
	public void borrow(Book book) {
		if (books.contains(book)) {
			return;
		}
		books.add(book);
		book.setBorrowed(true);
		notifyChanged();
	}

	public void returnBook(Book book) {
		books.remove(book);
		book.setBorrowed(false);
		notifyChanged();
	}

	@Override
	public void add(Book book) {
//		System.out.println("### add :" + book);
		this.borrow(book);
	}

	@Override
	public void remove(Book book) {
		returnBook(book);
	}

	public Book getBorrewerredBook(int i) {
		return books.get(i);
	}

	public int getBorrewerredBookSize() {
		return books.size();
	}

	public void setName(String name) {
		this.name = name;
		notifyChanged();
	}

	private void notifyChanged() {
		for (BorrowerListener listener : listeners) {
			listener.borrowerChanged(this);
		}
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getName() {

		return name == null ? "anonymous" : name;
	}

	public void addListener(BorrowerListener borrowerListener) {
		listeners.add(borrowerListener);
	}

}
