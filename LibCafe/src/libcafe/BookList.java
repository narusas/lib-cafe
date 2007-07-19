package libcafe;

import java.util.LinkedList;
import java.util.List;

public class BookList implements BookListener {
	List<Book> books = new LinkedList<Book>();
	List<BookListListener> listeners = new LinkedList<BookListListener>();
	String name;

	public void add(Book book) {
		book.addListener(this);
		books.add(book);
		notifyAdded(book);
	}

	public void remove(Book book) {
		books.remove(book);
		notifyRemoved(book);
	}

	public Book get(int i) {
		return books.get(i);
	}

	public String getName() {
		return name;
	}

	public int size() {
		return books.size();
	}

	public void setName(String name) {
		this.name = name;
		notifyNameChanged(this);
	}

	public void addListener(BookListListener ListenerbookListListener) {
		listeners.add(ListenerbookListListener);
	}

	public void removeListener(BookListListener bookListListener) {
		listeners.remove(bookListListener);
	}

	private void notifyNameChanged(BookList bookList) {
		for (BookListListener listener : listeners) {
			listener.nameChanged();
		}
	}

	private void notifyAdded(Book book) {
		for (BookListListener listener : listeners) {
			listener.bookAdded(this, book);
		}
	}

	private void notifyRemoved(Book book) {
		for (BookListListener listener : listeners) {
			listener.bookRemoved(this, book);
		}
	}

	private void notifyModified(Book book) {
		for (BookListListener listener : listeners) {
			listener.bookRemoved(this, book);
		}
	}

	@Override
	public void eventNotified(BookEvent e) {
		notifyModified(e.getSrc());
	}

	@Override
	public String toString() {
		return name; 
	}
}
