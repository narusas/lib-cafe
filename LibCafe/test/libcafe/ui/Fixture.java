package libcafe.ui;

import libcafe.Book;
import libcafe.BookList;
import libcafe.WholeBookList;

public class Fixture {
	public BookList list1 = new BookList();
	public BookList list2 = new BookList();
	public Book book1 = new Book();

	public Book book2 = new Book();

	public Book book3 = new Book();

	public WholeBookList wList = new WholeBookList();

	public Fixture() {
		list1.setName("list1");
		list2.setName("list2");

		book1.setTitle("New Fun book");
		book1.setCreator("�ƹ���");
		book1.setRating(3);

		book2.setTitle("Comics Series");
		book2.setCreator("Ano");
		book2.setRating(1);

		book3.setTitle("111");
		book3.setCreator("222no");
		book3.setRating(5);
	}
}
