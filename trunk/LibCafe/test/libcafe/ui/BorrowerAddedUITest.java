package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Borrower;
import libcafe.BorrowerList;

public class BorrowerAddedUITest {
	public static void main(String[] args) {
		// Borrower 积己
		Borrower bwer1 = new Borrower();
		bwer1.setName("己刮");
		
		BorrowerList bwerList = new BorrowerList();
		bwerList.add(bwer1);
		
		// Borrower UI 积己 -> Borrower List 积己.
		

		BorrowerListModel controller = new BorrowerListModel();
		controller.setList(bwerList);

		BorrowerListUI ui = new BorrowerListUI();
		ui.setModel(controller);

		
		ui.borrowerList.setDragEnabled(true);
		ui.borrowerList.setTransferHandler(new BooksIntoList());
		
		JFrame f = SwingUtil.createJFrame(400, 400);
		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.validate();	
		
		// 氓 格废 积己    -> 氓 抛捞喉 UI 积己
		Fixture fixture = new Fixture();

		fixture.wList.add(fixture.list1);
		fixture.wList.add(fixture.list2);

		fixture.list1.add(fixture.book1);

		
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
