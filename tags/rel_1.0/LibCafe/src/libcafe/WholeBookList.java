package libcafe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class WholeBookList extends BookList implements BookListListener {

	List<BookList> bookLists = new ArrayList<BookList>();

	private List<WholeBookListener> wholeBookListeners = new ArrayList<WholeBookListener>();

	public WholeBookList() {
		bookLists.add(this);
		setName("Whole Book List");
	}

	public void add(BookList bookList) {
		bookLists.add(bookList);

		bookList.addBookListener(new BookListListener() {

			@Override
			public void bookAdded(BookList list, Book book) {
				mergeBook(book);
			}

			@Override
			public void bookModified(BookList list, Book book) {

			}

			@Override
			public void bookRemoved(BookList list, Book book) {

			}

			@Override
			public void bookListNameChanged(BookList list) {

			}
		});
		mergeBooks(bookList);
		notifyBookListAdded(bookList);
	}

	private void mergeBooks(BookList bookList) {
		for (Iterator it = bookList.books.iterator(); it.hasNext();) {
			mergeBook((Book) it.next());
		}
	}

	private void mergeBook(Book book) {
		if (this.books.contains(book) == false) {
			add(book);
		}
	}

	@Override
	public void add(Book book) {
		super.add(book);
		for (WholeBookListener listener : wholeBookListeners) {
			listener.bookListModified(this, this);
		}
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
		if (bookLists == null) {
			return null;
		}

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
	public void bookListNameChanged(BookList list) {
		for (WholeBookListener listener : wholeBookListeners) {
			listener.bookListModified(this, list);
		}
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
