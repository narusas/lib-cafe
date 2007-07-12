package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

import libcafe.Book;

public class BookEditTest {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.getContentPane().setLayout(new BorderLayout());
		
		
		BookEditUI ui = new BookEditUI();
		BookEditController controller = new BookEditController(ui);
		Book book = new Book();
		controller.setBook(book);

		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
