package libcafe;

import java.util.Date;

import junit.framework.TestCase;

public class BookTest extends TestCase {
	public void testCreateBook() {
		Book book = new Book();
		book.setTitle("title");
		book.setCoverImageUrl("large");
		book.setCoverImageUrlThumbnail("small");
		book.setDescription("desc");
		book.setAuthor("author");
		book.setTranslator("trans");
		book.setPublisher("publisher");
		book.setPublishDate(new Date(1999, 1, 2));
		book.setCategory("catergory");
		book.setIsbn("0000");
		book.setPrice(100);

		assertEquals("title", book.getTitle());
	}

	public void testBorrowBook() {
		Borrower borrower = new Borrower();
		Book book = new Book();

		assertFalse(book.isBorrowered());

		borrower.borrow(book);
		assertTrue(book.isBorrowered());

		assertEquals(book, borrower.getBorrewerredBook(0));
		assertEquals(1, borrower.getBorrewerredBookSize());

		borrower.returnBook(book);
		assertFalse(book.isBorrowered());
		assertEquals(0, borrower.getBorrewerredBookSize());

	}

	public void testBookList() {
		BookList list = new BookList();

		list.setName("sf");
		assertEquals("sf", list.getName());

		assertEquals(0, list.size());

		Book book = new Book();
		list.add(book);
		assertEquals(1, list.size());

		assertEquals(book, list.get(0));
	}

	boolean isTested = false;

	public void testGenerateBookListAddedEvent() {
		BookList list = new BookList();
		isTested = false;
		list.addListener(new BookListListener() {
			public void bookAdded(BookList list, Book book) {
				isTested = true;
			}

			public void bookRemoved(BookList list, Book book) {
			}
		});
		list.add(new Book());
		assertTrue(isTested);
	}

	public void testGenerateBookListRemoveEvent() {
		BookList list = new BookList();

		isTested = false;

		list.addListener(new BookListListener() {
			public void bookAdded(BookList list, Book book) {
			}

			public void bookRemoved(BookList list, Book book) {
				isTested = true;
			}
		});

		Book book1 = new Book();

		list.add(book1);

		assertEquals(1, list.size());

		list.remove(book1);
		assertTrue(isTested);
		assertEquals(0, list.size());
	}

}
