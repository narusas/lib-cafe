package libcafe;

import java.util.LinkedList;
import java.util.List;

public abstract class Tag {

	private final String name;
	List<Book> books = new LinkedList<Book>();

	protected Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void add(Book book) {
		books.add(book);
	}

	public void remove(Book book) {
		books.remove(book);
	}

}
