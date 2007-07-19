package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import libcafe.Book;
import libcafe.Date;

public class BookModifyTest {
	public static void main(String[] args) {
		Book book = new Book();
		book.setTitle("title1");
		book.setPublishDate(new Date(2004, 12, 20));
		
		book.setCreator("me");
		book.setTranslator("trans");
		book.setPublisher("pu");
		book.setCategory("cate");
		book.setDescription("des");
		book.setIsbn("isbn");
		
		book.setIsbn(null);
		
		
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.setLocation(500, 300);
		f.getContentPane().setLayout(new BorderLayout());
		
		BookEditUI ui = new BookEditUI();
		BookEditController controller = new BookEditController(ui);
		
		controller.setBook(book);
		
		f.getContentPane().add(ui, BorderLayout.CENTER);
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}