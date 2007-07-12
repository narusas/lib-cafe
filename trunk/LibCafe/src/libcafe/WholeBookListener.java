package libcafe;

public interface WholeBookListener {
	void bookListAdded(WholeBookList wList, BookList bList);
	void bookListRemoved(WholeBookList wList, BookList bList);
}
