package libcafe.ui;

import java.awt.Image;

import javax.swing.table.DefaultTableModel;

import libcafe.Book;
import libcafe.BookList;
import libcafe.BookListListener;

public class BookListTableController extends DefaultTableModel implements
		BookListListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6324746542221450967L;
	private BookList bookList;

	public BookListTableController() {
		super();
	}

	public void setBookList(BookList bookList) {
		if (this.bookList != null) {
			this.bookList.removeListener(this);
		}
		this.bookList = bookList;
		this.bookList.addBookListener(this);
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class[] clazz = new Class[] { Image.class, String.class, String.class,
				Integer.class };
		return clazz[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String[] headers = new String[] { "img", "title", "creator", "rating" };
		return headers[columnIndex];
	}

	@Override
	public int getRowCount() {
		if (bookList == null) {
			return 0;
		}
		return bookList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (bookList == null) {
			return null;
		}
		switch (columnIndex) {
		case 0:
			return "No";
		case 1:
			return bookList.get(rowIndex).getTitle();
		case 2:
			return bookList.get(rowIndex).getCreator();
		case 3:
			return bookList.get(rowIndex).getRating();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean[] editable = new boolean[] { false, false, false, true };
		return editable[columnIndex];
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (columnIndex == 3) {
			int v = ((Integer) value).intValue();
			bookList.get(rowIndex).setRating(v);
			fireTableDataChanged();
		}
	}

	@Override
	public void bookAdded(BookList list, Book book) {
		fireTableDataChanged();
	}

	@Override
	public void bookRemoved(BookList list, Book book) {
		fireTableDataChanged();
	}

	@Override
	public void bookModified(BookList list, Book book) {
		fireTableDataChanged();
	}



	public Book getBookByRow(int row) {
		return bookList.get(row);
	}

	public BookList getBookList() {
		return bookList;
	}

	public void remove(Book book) {
		bookList.remove(book);
	}

	@Override
	public void bookListNameChanged(BookList list) {
		
	}
}
