package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import libcafe.Book;

public class BookEditTest {
	public static void main(String[] args) {
		JFrame f = SwingUtil.createJFrame(400, 400);

		BookEditUI ui = new BookEditUI();
		BookEditController controller = new BookEditController(ui);
		Book book = new Book();
		controller.setBook(book);

		f.getContentPane().add(ui, BorderLayout.CENTER);
	}
}
