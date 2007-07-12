package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.BookList;
import libcafe.WholeBookList;

public class BookListListUITest {

	public static void main(String[] args) {

		final WholeBookList wList = new WholeBookList();

		final BookList list1 = new BookList();
		list1.setName("list1");
		final BookList list2 = new BookList();
		list2.setName("list2");

		wList.add(list1);
		wList.add(list2);

		JFrame f = new JFrame();
		f.setSize(200, 300);
		f.setLocation(500, 400);
		f.getContentPane().setLayout(new BorderLayout());

		BookListListUI ui = new BookListListUI();
		final BookListListController model = new BookListListController(ui);
		model.setWholeBookList(wList);

		f.getContentPane().add(ui, BorderLayout.CENTER);

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final BookList newList = new BookList();
		
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}

				newList.setName("new List");
				wList.add(newList);
				System.out.println("List Added");
			}
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				wList.remove(list2);
				System.out.println("List Removed");
			}
		}.start();
	
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
				}
				list1.setName("New Name");
				System.out.println("List name changed");
			}
		}.start();
	}
}
