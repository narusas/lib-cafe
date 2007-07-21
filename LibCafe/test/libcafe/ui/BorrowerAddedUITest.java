package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Borrower;
import libcafe.BorrowerList;

public class BorrowerAddedUITest {
	public static void main(String[] args) {
		// Borrower ����
		Borrower bwer1 = new Borrower();
		bwer1.setName("����");
		
		BorrowerList bwerList = new BorrowerList();
		bwerList.add(bwer1);
		
		// Borrower UI ���� -> Borrower List ����.
		

		BorrowerListModel controller = new BorrowerListModel();
		controller.setList(bwerList);

		BorrowerListUI ui = new BorrowerListUI();
		ui.setModel(controller);

		
		ui.borrowerList.setDragEnabled(true);
		ui.borrowerList.setTransferHandler(new BooksIntoList());
		
		JFrame f = SwingUtil.createJFrame(400, 400);
		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.validate();	
		
		// å ��� ����    -> å ���̺� UI ����
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
