package libcafe.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Borrower;
import libcafe.BorrowerList;

public class BorrowerAddedUITest {
	public static void main(String[] args) {
		// Borrower 积己
		Borrower bwer1 = new Borrower();
		bwer1.setName("己刮");

		Borrower bwer2 = new Borrower();
		bwer2.setName("己刮2");

		final BorrowerList bwerList = new BorrowerList();
		bwerList.add(bwer1);
		bwerList.add(bwer2);

		// Borrower UI 积己 -> Borrower List 积己.

		BorrowerListModel controller = new BorrowerListModel();
		controller.setList(bwerList);

		final BorrowerListUI ui = new BorrowerListUI();
		ui.setModel(controller);

		ui.borrowerList.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					Borrower bwer = (Borrower) ui.borrowerList.getSelectedValue();
					bwerList.remove(bwer);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		ui.borrowerList.setDragEnabled(true);
		ui.borrowerList.setTransferHandler(new BooksIntoList());

		JFrame f = SwingUtil.createJFrame(400, 400);
		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.validate();

		// 氓 格废 积己 -> 氓 抛捞喉 UI 积己
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
