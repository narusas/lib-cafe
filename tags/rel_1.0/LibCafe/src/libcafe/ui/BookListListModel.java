package libcafe.ui;

import javax.swing.AbstractListModel;

import libcafe.Book;
import libcafe.BookList;
import libcafe.BookListListener;
import libcafe.WholeBookList;
import libcafe.WholeBookListener;

public class BookListListModel extends AbstractListModel implements WholeBookListener, BookListListener {

	private WholeBookList list;

	@Override
	public Object getElementAt(int index) {
		return list.getBookList(index);
	}

	@Override
	public int getSize() {
		return list == null ? 0 : list.getBookListSize();
	}

	public void setWholeList(WholeBookList list) {
		this.list = list;
		list.addWholeBookListListener(this);
		for (int i = 0; i < this.list.getBookListSize(); i++) {
			BookList item = this.list.getBookList(i);
			item.addBookListener(this);

		}
		fireContentsChanged(this, 0, this.list.getBookListSize());
	}

	@Override
	public void bookListAdded(WholeBookList list, BookList list2) {
		fireContentsChanged(this, 0, this.list.getBookListSize());
	}

	@Override
	public void bookListRemoved(WholeBookList list, BookList list2) {
		fireContentsChanged(this, 0, this.list.getBookListSize());
	}

	@Override
	public void bookAdded(BookList list, Book book) {
	}

	@Override
	public void bookModified(BookList list, Book book) {
		// TODO Auto-generated method stub

	}

	public void bookRemoved(BookList list, Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bookListModified(WholeBookList list, BookList list2) {
		fireContentsChanged(this, 0, this.list.getBookListSize());
	}

	@Override
	public void bookListNameChanged(BookList list) {
		fireContentsChanged(this, 0, this.list.getBookListSize());
	}

	public BookList getWholeBookList() {
		return list;
	}
}
