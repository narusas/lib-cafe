package libcafe;

public class BookTest extends BasicTest {

	public void testAddToAllList() {
		Book b1 = new Book();
		Book b2 = new Book();
		Book b3 = new Book();
		Book b4 = new Book();

		BookList bList = new BookList();
		WholeBookList wList = new WholeBookList();

		wList.add(bList);

		bList.add(b1);
		bList.add(b2);
		bList.add(b3);
		bList.add(b4);

		assertEquals(4, wList.size());
	}

	public void testCreateBook() {
		Book book = new Book();
		book.setTitle("title");
		book.setCoverImageUrl("large");
		book.setCoverImageUrlThumbnail("small");
		book.setDescription("desc");
		book.setCreator("author");
		book.setTranslator("trans");
		book.setPublisher("publisher");
		book.setPublishDate(new Date(1999, 1, 2));
		book.setCategory("catergory");
		book.setIsbn("0000");
		book.setPrice("100");

		assertEquals("title", book.getTitle());
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

	public void testGenerateBookListAddedEvent() {
		BookList list = new BookList();
		isTested = false;
		list.addBookListener(new BookListListener() {
			public void bookAdded(BookList list, Book book) {
				isTested = true;
			}

			public void bookRemoved(BookList list, Book book) {
			}

			@Override
			public void bookModified(BookList list, Book book) {
				// TODO Auto-generated method stub

			}

			@Override
			public void bookListNameChanged(BookList list) {
				// TODO Auto-generated method stub

			}

		});
		list.add(new Book());
		assertTrue(isTested);
	}

	public void testGenerateBookListRemoveEvent() {
		BookList list = new BookList();

		isTested = false;

		list.addBookListener(new BookListListener() {
			public void bookAdded(BookList list, Book book) {
			}

			public void bookRemoved(BookList list, Book book) {
				isTested = true;
			}

			@Override
			public void bookModified(BookList list, Book book) {
				// TODO Auto-generated method stub

			}

			@Override
			public void bookListNameChanged(BookList list) {
				// TODO Auto-generated method stub

			}

		});

		Book book1 = new Book();

		list.add(book1);

		assertEquals(1, list.size());

		list.remove(book1);
		assertTrue(isTested);
		assertEquals(0, list.size());
	}

	public void testWholeBookList() {
		isTested = false;
		WholeBookList wList = new WholeBookList();
		assertEquals(0, wList.size());

		wList.addWholeBookListListener(new WholeBookListener() {
			@Override
			public void bookListAdded(WholeBookList list, BookList list2) {
				isTested = true;
			}

			@Override
			public void bookListRemoved(WholeBookList list, BookList list2) {

			}

			@Override
			public void bookListModified(WholeBookList list, BookList list2) {
				// TODO Auto-generated method stub

			}
		});
		wList.add(new BookList());

		assertTrue(isTested);
		assertEquals(2, wList.getBookListSize());

		wList.add(new Book());
		assertEquals(1, wList.size());
	}

	public void testRemoveBookFromWholeList_AlsoList() {
		BookList list = new BookList();
		Book book = new Book();
		list.add(book);

		WholeBookList wList = new WholeBookList();
		wList.add(list);

		assertEquals(1, list.size());

		wList.remove(book);
		assertEquals(0, list.size());
	}

	public void testUniqueInTimeBookID() {
		Entity.staticId = 100;
		Book book3 = new Book(100);
		Book book4 = new Book();

		assertNotSame(book3.getID(), book4.getID());
	}

	public void testGenerateEntityId() {
		Entity book = new Book();
		Entity bList1 = new BookList();
		assertSerialIdInEntities(book, bList1);
	}

	public void testEntities() {
		assertSerialIdInEntities(new Book(), new Book());
		assertSerialIdInEntities(new BookList(), new BookList());
		assertSerialIdInEntities(new BorrowerList(), new BorrowerList());
	}

	public void assertSerialIdInEntities(Entity e1, Entity e2) {
		assertEquals(e1.getID() + 1, e2.getID());
	}
}