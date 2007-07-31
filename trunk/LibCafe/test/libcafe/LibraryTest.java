package libcafe;

import junit.framework.TestCase;

public class LibraryTest extends TestCase {
	
	Library lib;
	WholeBookList wList;
	TagList tList;
	BorrowerList bList;
	boolean isTest;
	
	@Override
	protected void setUp() throws Exception {
		lib = new Library();
		wList = new WholeBookList();
		tList = new TagList();
		bList = new BorrowerList();

		lib.setWholeBookList(wList);
		lib.setTagList(tList);
		lib.setBorrowList(bList);
	}
	
	public void testCreateLibrary() {
		assertEquals(lib.getWholeBookList(), wList);
		assertEquals(lib.getTagList(), tList);
		assertEquals(lib.getBorrowList(), bList);
	}
	
	public void testGenerateBookListAddedEvent(){
		isTest = false;
		lib.addLibraryListener(new LibraryListener(){
			public void wholeBookListModified(WholeBookList list, BookList list2) {
				isTest = true;
			}
			public void wholeBorroerListModified(Borrower borrower) {}
		});
		wList.add(new BookList());
		assertTrue(isTest);
	}

	public void testGenerateBookListRemovedEvent(){
		isTest = false;
		lib.addLibraryListener(new LibraryListener(){
			public void wholeBookListModified(WholeBookList list, BookList list2) {
				isTest = true;
			}
			public void wholeBorroerListModified(Borrower borrower) {}
		});
		BookList list = new BookList();
		wList.add(list);
		wList.remove(list);
		assertTrue(isTest);
	}
	
	public void testGenerateBookListModifiedEvent(){
		isTest = false;
		lib.addLibraryListener(new LibraryListener(){
			public void wholeBookListModified(WholeBookList list, BookList list2) {
				isTest = true;
			}
			public void wholeBorroerListModified(Borrower borrower) {}
		});
		BookList list = new BookList();
		Book book = new Book();
		book.setTitle("test");
		list.add(book);
		wList.add(list);
		
		book.setTitle("tested");

		assertTrue(isTest);
	}
	
	public void testGenerateBorrowListAddedEvent(){
		isTest = false;
		lib.addLibraryListener(new LibraryListener(){
			public void wholeBookListModified(WholeBookList list, BookList list2) {}
			public void wholeBorroerListModified(Borrower borrower) {
				isTest = true;
			}
		});
		bList.add(new Borrower());
		assertTrue(isTest);
	}
	
	public void testGenerateBorrowListRemovedEvent(){
		isTest = false;
		lib.addLibraryListener(new LibraryListener(){
			public void wholeBookListModified(WholeBookList list, BookList list2) {}
			public void wholeBorroerListModified(Borrower borrower) {
				isTest = true;
			}
		});
		Borrower b = new Borrower();
		bList.add(b);
		bList.remove(b);
		assertTrue(isTest);
	}
	
	public void testGenerateBorrowModifiedEvent(){
		isTest = false;
		lib.addLibraryListener(new LibraryListener(){
			public void wholeBookListModified(WholeBookList list, BookList list2) {}
			public void wholeBorroerListModified(Borrower borrower) {
				if(borrower.getName().equals("tested"))
					isTest = true;
			}
		});
		Borrower b = new Borrower();
		bList.add(b);
		
		b.setName("tested");
		assertTrue(isTest);
	}
}
