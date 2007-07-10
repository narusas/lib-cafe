package libcafe;

import java.util.LinkedList;
import java.util.List;

public class Borrower {
	List<Book> books = new LinkedList<Book>();

	public void borrow(Book book) {
		books.add(book);
		book.setBorrowed(true);
	}

	public void returnBook(Book book) {
		books.remove(book);
		book.setBorrowed(false);
	}

	public Book getBorrewerredBook(int i) {
		return books.get(i);
	}

	public int getBorrewerredBookSize() {
		return books.size();
	}

}
