package libcafe.ui;

import libcafe.Book;
import libcafe.BookList;
import libcafe.WholeBookList;

public class Fixture {
	BookList list1 = new BookList();
	BookList list2 = new BookList();
	Book book1 = new Book();

	Book book2 = new Book();

	Book book3 = new Book();

	WholeBookList wList = new WholeBookList();

	public Fixture() {
		list1.setName("list1");
		list2.setName("list2");

		book1.setTitle("New Fun book");
		book1.setCreator("¾Æ¹«°³");
		book1.setRating(3);

		book2.setTitle("Comics Series");
		book2.setCreator("Ano");
		book2.setRating(1);

		book3.setTitle("111");
		book3.setCreator("222no");
		book3.setRating(5);
	}
}
