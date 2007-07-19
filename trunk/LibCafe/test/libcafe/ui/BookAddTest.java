package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Book;

public class BookAddTest {
	public static void main(String[] args) {
		
		final Fixture fixture = new Fixture();
		fixture.list1.add(fixture.book1);

		JFrame f = SwingUtil.createJFrame(200, 400);
		f.setLocation(200, 200);
		JTable table = new BookListTableUI();
		final BookListTableController model = new BookListTableController();
		model.setBookList(fixture.list1);
		table.setModel(model);
		
		f.getContentPane().add(table, BorderLayout.CENTER);
		f.validate();
		
		JFrame editFrame = SwingUtil.createJFrame(400, 400);
		editFrame.setLocation(400,200);

		BookEditUI ui = new BookEditUI();
		BookEditController controller = new BookEditController(ui);
		Book book = new Book();
		controller.setBook(book);

		editFrame.getContentPane().add(ui, BorderLayout.CENTER);
		
	}
}
