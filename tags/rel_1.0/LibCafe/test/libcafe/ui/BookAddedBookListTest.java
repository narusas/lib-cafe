package libcafe.ui;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import libcafe.Book;

public class BookAddedBookListTest {

	public static void main(String[] args) {

		Fixture fixture = new Fixture();

		fixture.wList.add(fixture.list1);
		fixture.wList.add(fixture.list2);

		fixture.list1.add(fixture.book1);

		JFrame f = SwingUtil.createJFrame(500, 300);

		BookListListUI ui = new BookListListUI();
		final BookListListController controller = new BookListListController(ui);
		controller.setWholeBookList(fixture.wList);

		ui.bookListList.setDragEnabled(true);
		ui.bookListList.setTransferHandler(new BooksIntoList());

		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.validate();

		JTable table = new BookListTableUI();
		BookListTableController tableController = new BookListTableController();
		tableController.setBookList(fixture.list1);
		table.setModel(tableController);

		table.setDragEnabled(true);
		table.setTransferHandler(new BooksExportFromTable());

		f.getContentPane().add(table, BorderLayout.EAST);
		f.validate();

	}
}
