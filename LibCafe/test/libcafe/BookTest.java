package libcafe;

public class BookTest extends BasicTest {

	
	public void testAddToAllList(){
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
		list.addListener(new BookListListener() {
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
			public void nameChanged() {
				// TODO Auto-generated method stub
				
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

			@Override
			public void bookModified(BookList list, Book book) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void nameChanged() {
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
		});
		wList.add(new BookList());

		assertTrue(isTested);
		assertEquals(1, wList.getBookListSize());

		wList.add(new Book());
		assertEquals(1, wList.size());
	}

}
