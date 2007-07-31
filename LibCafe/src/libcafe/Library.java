package libcafe;

import java.util.ArrayList;
import java.util.List;

public class Library implements WholeBookListener {
	
	private WholeBookList wholeBookList;
	private TagList tagList;
	private BorrowerList borrowList;
	private List<LibraryListener> libraryListeners = new ArrayList<LibraryListener>();

	public void setWholeBookList(WholeBookList list) {
		wholeBookList = list;
		wholeBookList.addWholeBookListListener(this);
	}

	public void setTagList(TagList list) {
		tagList = list;
	}

	public void setBorrowList(BorrowerList list) {
		borrowList = list;
	}

	public WholeBookList getWholeBookList() {
		return wholeBookList;
	}

	public TagList getTagList() {
		return tagList;
	}

	public BorrowerList getBorrowList() {
		return borrowList;
	}

	public void addLibraryListener(LibraryListener libraryListener) {
		libraryListeners.add(libraryListener);
	}

	@Override
	public void bookListAdded(WholeBookList list, BookList list2) {
		notifyBookListAddEvent(list, list2);
	}

	private void notifyBookListAddEvent(WholeBookList list, BookList list2) {
		for (LibraryListener listener : libraryListeners) {
			listener.wholeBookListModified(list, list2);
		}
	}

	@Override
	public void bookListRemoved(WholeBookList list, BookList list2) {
		notifyBookListRemovedEvent(list, list2);
	}

	private void notifyBookListRemovedEvent(WholeBookList list, BookList list2) {
		for (LibraryListener listener : libraryListeners) {
			listener.wholeBookListModified(list, list2);
		}
	}

	@Override
	public void bookListModified(WholeBookList list, BookList list2) {
		for (LibraryListener listener : libraryListeners) {
			listener.wholeBookListModified(list, list2);
		}
	}
}
