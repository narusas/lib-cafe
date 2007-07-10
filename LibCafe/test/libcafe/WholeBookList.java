package libcafe;

import java.util.ArrayList;
import java.util.List;

public class WholeBookList extends BookList{

	List<BookList> bookLists = new ArrayList<BookList>();
	private List<WholeBookListener> wholeBookListeners = new ArrayList<WholeBookListener>();
	
	public void add(BookList bookList) {
		bookLists.add(bookList);
		notifyBookListAdded(bookList);
	}

	private void notifyBookListAdded(BookList bookList) {
		for (WholeBookListener listener : wholeBookListeners) {
			listener.bookListAdded(this, bookList);
		}
	}

	public int getBookListSize() {
		return bookLists.size();
	}

	public void addWholeBookListListener(WholeBookListener wholeBookListener) {
		wholeBookListeners .add(wholeBookListener);
	}

}
