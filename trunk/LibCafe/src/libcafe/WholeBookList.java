package libcafe;

import java.util.ArrayList;
import java.util.List;

public class WholeBookList extends BookList implements BookListListener {

	List<BookList> bookLists = new ArrayList<BookList>();
	private List<WholeBookListener> wholeBookListeners = new ArrayList<WholeBookListener>();

	public void add(BookList bookList) {
		bookLists.add(bookList);
		notifyBookListAdded(bookList);
	}

	public void remove(BookList bookList) {
		bookLists.remove(bookList);
		notifyBookListRemoved(bookList);
	}

	private void notifyBookListRemoved(BookList bookList) {
		for (WholeBookListener listener : wholeBookListeners) {
			listener.bookListRemoved(this, bookList);
		}
	}

	private void notifyBookListAdded(BookList bookList) {
		for (WholeBookListener listener : wholeBookListeners) {
			listener.bookListAdded(this, bookList);
		}
	}

	public BookList getBookList(int index) {
		return bookLists.get(index);
	}

	public int getBookListSize() {
		return bookLists.size();
	}

	public void addWholeBookListListener(WholeBookListener wholeBookListener) {
		wholeBookListeners.add(wholeBookListener);
	}

	@Override
	public String toString() {
		return "WholeList";
	}

	@Override
	public void bookAdded(BookList list, Book book) {
		add(book);
	}

	@Override
	public void bookModified(BookList list, Book book) {

	}

	@Override
	public void bookRemoved(BookList list, Book book) {

	}

	@Override
	public void nameChanged() {

	}

	@Override
	public void remove(Book book) {
		for (BookList list : bookLists) {
			if (list == this) {
				super.remove(book);
				continue;
			}
			list.remove(book);
		}
		
	}
}
