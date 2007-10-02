package libcafe;

public interface BookListListener {
	void bookAdded(BookList list, Book book) ;
	void bookRemoved(BookList list, Book book);
	void bookModified(BookList list, Book book);
	void bookListNameChanged(BookList list);
}
