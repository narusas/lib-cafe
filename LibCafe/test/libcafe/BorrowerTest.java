package libcafe;

public class BorrowerTest extends BasicTest {
	public void testBorrowBook() {
		Borrower borrower = new Borrower();
		Book book = new Book();

		assertFalse(book.isBorrowed());

		borrower.borrow(book);
		assertTrue(book.isBorrowed());

		assertEquals(book, borrower.getBorrewerredBook(0));
		assertEquals(1, borrower.getBorrewerredBookSize());

		borrower.returnBook(book);
		assertFalse(book.isBorrowed());
		assertEquals(0, borrower.getBorrewerredBookSize());

	}

	// TODO isBorrowed �̿��� �ٸ� �̺�Ʈ�� �����ؾ���.
	/**
	 * å�� �����ų� �ݳ��Ҷ� ������ �̺�Ʈ�� �߻����� �׽�Ʈ��.
	 */
	public void testGenarateBorrowBookEvent() {
		Book book = new Book();

		book.addListener(new BookListener() {
			public void eventNotified(BookEvent e) {
				assertEquals("isBorrowed", e.getName());
				assertEquals(new Boolean(true), e.getValue());
				isTested = true;
			}
		});
		book.setBorrowed(true);

		assertTrue(isTested);

	}

	public void testCreateBorrowerList() {
		BorrowerList list = new BorrowerList();
		Borrower bwer = new Borrower();
		list.add(bwer);
		assertEquals(1, list.size());
		list.remove(bwer);
		assertEquals(0, list.size());
	}

	public void testBorrowerListEvent() {
		BorrowerList list = new BorrowerList();
		final Borrower bwer = new Borrower();

		list.addListener(new BorrowerListListener() {
			public void borrowerAdded(Borrower borrower) {
				assertEquals(bwer, borrower);
				isTested = true;
			}

			@Override
			public void borrowerRemoved(Borrower borrower) {

			}
		});
		list.add(bwer);
		assertTrue(isTested);
	}
}
