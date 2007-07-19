package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.BookList;
import libcafe.WholeBookList;

public class BookListListUITest {

	public static void main(String[] args) {

		final Fixture fixture = new Fixture();

		fixture.wList.add(fixture.list1);
		fixture.wList.add(fixture.list2);

		JFrame f = SwingUtil.createJFrame(200, 300);

		BookListListUI ui = new BookListListUI();
		final BookListListController model = new BookListListController(ui);
		model.setWholeBookList(fixture.wList);

		f.getContentPane().add(ui, BorderLayout.CENTER);

		final BookList newList = new BookList();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}

				newList.setName("new List");
				fixture.wList.add(newList);
				System.out.println("List Added");
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				fixture.wList.remove(fixture.list2);
				System.out.println("List Removed");
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
				}
				fixture.list1.setName("New Name");
				System.out.println("List name changed");
			}
		}.start();
	}
}
