package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Book;
import libcafe.BookList;

public class BookListTableUITest {
	public static void main(String[] args) {
		
		final Fixture fixture = new Fixture();
		fixture.list1.add(fixture.book1);
		// list.add(book2);

		fixture.list2.add(fixture.book3);
		// list.add(book2);

		JFrame f = SwingUtil.createJFrame(400, 400);
		JTable table = new BookListTableUI();
		final BookListTableController model = new BookListTableController();
		model.setBookList(fixture.list1);
		table.setModel(model);

		f.getContentPane().add(table, BorderLayout.CENTER);
		f.validate();
		
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				fixture.list1.add(fixture.book2);
				System.out.println("Added");
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				fixture.book1.setTitle("³»°¡½è´Ù!!!");
				System.out.println("Modified");
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
				}
				model.setBookList(fixture.list2);
				System.out.println("List changed");
			}
		}.start();
	}
}
