package libcafe.ui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libcafe.Book;
import libcafe.BookList;
import libcafe.Borrower;
import libcafe.BorrowerList;
import libcafe.WholeBookList;

public class MainFrameTest {
	public static void main(String[] args) {

		final MainFrame f = new MainFrame();
		f.setSize(1024, 768);
		f.setVisible(true);

		/*
		 * Test할 data setting
		 */
		Book book1 = new Book();
		Book book2 = new Book();
		book1.setTitle("Test Book 1");
		book2.setTitle("Test Book 2");

		BookList bookList1 = new BookList();
		BookList bookList2 = new BookList();
		bookList1.setName("book List 1");

		WholeBookList wBookList = new WholeBookList();
		wBookList.add(bookList1);
		wBookList.add(bookList2);

		bookList1.add(book1);
		bookList1.add(book2);
		bookList2.add(book1);

		Borrower b1 = new Borrower();
		Borrower b2 = new Borrower();

		b1.setName("성민");

		BorrowerList bList = new BorrowerList();

		bList.add(b1);
		bList.add(b2);

		/*
		 * 각 UI의 Controller 생성.
		 */
		final BookListListController bListListController = new BookListListController(
				f.bookListListPanel);
		bListListController.setWholeBookList(wBookList);
		f.bookListListPanel.validate();

		final BookListTableController bListTableController = new BookListTableController();
		f.bookListTablePanel.setModel(bListTableController);
		bListTableController.setBookList(wBookList);

		final BorrowerListModel bListController = new BorrowerListModel();
		bListController.setList(bList);
		f.borrowerListPanel.setModel(bListController);

		final BookEditController bEditController = new BookEditController(
				f.bookDetailPanel);

		// event - selection

		f.bookListListPanel.bookListList.getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						BookList bList = (BookList) f.bookListListPanel.bookListList
								.getSelectedValue();
						if (bList == null) {
							return;
						}
						bListTableController.setBookList(bList);
					}
				});
	}
}
