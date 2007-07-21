package libcafe;

import libcafe.ui.Fixture;

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

			@Override
			public void borrowerChanged(Borrower borrower) {
				// TODO Auto-generated method stub

			}
		});
		list.add(bwer);
		assertTrue(isTested);
	}

	public void testBorrowerChangedEvent_SetName() {
		final Borrower bwer = new Borrower();
		bwer.setName("����");
		bwer.addListener(new BorrowerListener() {

			public void borrowerChanged(Borrower bwer) {
				isTested = true;
				assertEquals("����", bwer.getName());
			}
		});
		bwer.setName("����");
		assertTrue(isTested);
	}

	public void testBorrowerChangedEvent_AddBook() {
		Fixture fixture = new Fixture();

		Book book1 = fixture.book1;

		final Borrower bwer = new Borrower();
		bwer.setName("����");

		bwer.addListener(new BorrowerListener() {

			public void borrowerChanged(Borrower bwer) {
				isTested = true;
				assertEquals(1, bwer.getBorrewerredBookSize());
			}
		});
		bwer.borrow(book1);

		assertTrue(isTested);

	}

	public void testBorrowerChangedEvent_RemoveBook() {
		Fixture fixture = new Fixture();

		Book book1 = fixture.book1;

		final Borrower bwer = new Borrower();
		bwer.setName("����");

		bwer.borrow(book1);

		bwer.addListener(new BorrowerListener() {

			public void borrowerChanged(Borrower bwer) {
				isTested = true;
				assertEquals(0, bwer.getBorrewerredBookSize());
			}
		});
		bwer.returnBook(book1);
		assertTrue(isTested);

	}

	int count = 0;

	public void testBorrwerListListenBorrwersEvents() {
		final Borrower bwer1 = new Borrower();
		bwer1.setName("����");

		final Borrower bwer2 = new Borrower();
		bwer2.setName("����");

		BorrowerList list = new BorrowerList();
		list.add(bwer1);
		list.add(bwer2);

		list.addListener(new BorrowerListListener() {

			@Override
			public void borrowerAdded(Borrower borrower) {

			}

			@Override
			public void borrowerChanged(Borrower borrower) {
				count++;
			}

			@Override
			public void borrowerRemoved(Borrower borrower) {

			}
		});
		bwer1.setName("����2");
		bwer2.setName("����2");
		assertEquals(2, count);
	}

	public void testDontBorrowSameBook() {
		final Borrower bwer1 = new Borrower();
		bwer1.setName("����");

		Book book1 = new Book();
		bwer1.borrow(book1);
		bwer1.borrow(book1);
		assertEquals(1, bwer1.getBorrewerredBookSize());
	}
}
