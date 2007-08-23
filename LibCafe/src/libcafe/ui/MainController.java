package libcafe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libcafe.Book;
import libcafe.BookList;
import libcafe.Borrower;
import libcafe.Library;

public class MainController {
	private final MainFrame ui;
	private final Library lib;
	private BookListListController bListListController;
	private BookListTableController bListTableController;
	private BorrowerListModel bListController;
	private BookEditController bEditController;

	public MainController(MainFrame ui, Library lib) {
		this.ui = ui;
		this.lib = lib;
	}

	public void init() {
		/*
		 * 각 UI의 Controller 생성.
		 */
		bListListController = new BookListListController(ui.bookListListPanel);
		bListListController.setWholeBookList(lib.getWholeBookList());
		ui.bookListListPanel.validate();

		bListTableController = new BookListTableController();
		ui.bookListTablePanel.setModel(bListTableController);
		bListTableController.setBookList(lib.getWholeBookList());

		bListController = new BorrowerListModel();
		bListController.setList(lib.getBorrowList());
		ui.borrowerListPanel.setModel(bListController);

		bEditController = new BookEditController(ui.bookDetailPanel);
		bEditController.addSaveActionListener(new SaveActionListener() {
			@Override
			public void saved(Book book) {
				bListListController.getSelectionList().add(book);
			}
		});

		// event - selection : BookList 선택 시

		ui.bookListListPanel.bookListList.getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						BookList bList = (BookList) ui.bookListListPanel.bookListList
								.getSelectedValue();
						if (bList == null) {
							return;
						}
						bListTableController.setBookList(bList);
					}
				});

		// event - selection : BorrowerList 선택 시

		ui.borrowerListPanel.borrowerList.getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						BookList bList = (BookList) ui.borrowerListPanel.borrowerList
								.getSelectedValue();
						if (bList == null) {
							System.out.println("null");
							return;
						}

						bListTableController.setBookList(bList);
					}
				});

		// table 의 북 선택시 BookDetailPanel 에 보여주기.

		ui.bookListTablePanel.getSelectionModel().setSelectionMode(0);
		ui.bookListTablePanel.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						int index = ui.bookListTablePanel.getSelectedRow();
						if (index == -1) {
							return;
						}
						Book book = bListTableController.getBookByRow(index);
						bEditController.setBook(book);

					}
				});

		// event - Add BookList
		ui.actionPanel.addBookListButton
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						BookList bList = new BookList();
						lib.getWholeBookList().add(bList);
					}
				});

		ui.actionPanel.addBorrowerButton
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Borrower bwer = new Borrower();
						lib.getWholeBookList().add(bwer);
					}
				});
		ui.bookActionPanel.bookAddBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startBookAdd();
			}
		});
	}

	protected void startBookAdd() {
		changeToAddBookTypePanel();
	}

	private void changeToAddBookTypePanel() {
		AddFormUI p = new AddFormUI();
		AddFormController controller = new AddFormController(p);
		controller.bookSelectedListener = new BookSelectedListener() {

			@Override
			public void bookSelected(Book book) {
				lib.getWholeBookList().add(book);
			}

		};
		ui.jSplitPane3.setRightComponent(p);
		p.directBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeToAddBookDetalPanel();
			}
		});
	}

	private void changeToAddBookDetalPanel() {
		ui.jSplitPane3.setRightComponent(ui.bookDetailPanel);
		Book book = new Book();
		bEditController.setBook(book);
	}
}
