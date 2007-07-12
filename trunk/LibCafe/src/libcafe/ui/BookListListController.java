package libcafe.ui;

import javax.swing.ListModel;

import libcafe.BookList;
import libcafe.WholeBookList;
import libcafe.WholeBookListener;

public class BookListListController {

	private final BookListListUI ui;
	private BookListListModel model;

	public BookListListController(BookListListUI ui) {
		this.ui = ui;
		model = new BookListListModel();
		ui.bookListList.setModel(model);
	}

	public void setWholeBookList(WholeBookList list) {
		model.setWholeList(list);
	}
}
