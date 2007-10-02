package libcafe.ui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libcafe.BookList;
import libcafe.WholeBookList;

public class BookListListController implements ListSelectionListener {

	private final BookListListUI ui;
	private BookListListModel model;
	private BookList selectedBookList;

	public BookListListController(BookListListUI ui) {
		this.ui = ui;
		model = new BookListListModel();
		ui.bookListList.addListSelectionListener(this);
		ui.bookListList.setModel(model);
	}

	public void setWholeBookList(WholeBookList list) {
		model.setWholeList(list);
	}

	public BookList getSelectionList() {
		if(selectedBookList == null)
			return model.getWholeBookList();
		return selectedBookList;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		selectedBookList = (BookList) ui.bookListList.getSelectedValue();
	}
}
