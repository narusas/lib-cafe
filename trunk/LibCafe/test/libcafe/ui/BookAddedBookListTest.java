package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import libcafe.BookList;
import libcafe.WholeBookList;

public class BookAddedBookListTest {

	public static void main(String[] args) {
		final WholeBookList wList = new WholeBookList();

		final BookList list1 = new BookList();
		list1.setName("list1");
		final BookList list2 = new BookList();
		list2.setName("list2");

		wList.add(list1);
		wList.add(list2);

		JFrame f = SwingUtil.createJFrame(200, 300);

		BookListListUI ui = new BookListListUI();
		final BookListListController controller = new BookListListController(ui);
		controller.setWholeBookList(wList);

		f.getContentPane().add(ui, BorderLayout.CENTER);

	}
}
