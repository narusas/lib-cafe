package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Book;
import libcafe.BookList;

public class BookListTableUITest {
	public static void main(String[] args) {
		final Book book1 = new Book();
		book1.setTitle("New Fun book");
		book1.setCreator("아무개");
		book1.setRating(3);

		final Book book2 = new Book();
		book2.setTitle("Comics Series");
		book2.setCreator("Ano");
		book2.setRating(1);

		final BookList list1 = new BookList();
		list1.add(book1);
		// list.add(book2);

		final Book book3 = new Book();
		book3.setTitle("111");
		book3.setCreator("222no");
		book3.setRating(5);

		final BookList list2 = new BookList();
		list2.add(book3);
		// list.add(book2);

		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.getContentPane().setLayout(new BorderLayout());
		JTable table = new BookListTableUI();
		final BookListTableController model = new BookListTableController();
		model.setBookList(list1);
		table.setModel(model);

		f.getContentPane().add(table, BorderLayout.CENTER);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				list1.add(book2);
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
				book1.setTitle("내가썼다!!!");
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
				model.setBookList(list2);
				System.out.println("List changed");
			}
		}.start();
		
	}
}
