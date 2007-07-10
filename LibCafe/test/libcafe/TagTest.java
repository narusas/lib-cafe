package libcafe;

public class TagTest extends BasicTest {
	public void testCreateTag() {
		TagList list = new TagList();
		Tag tag = list.getTag("tag1");
		assertEquals("tag1", tag.getName());
		Book book = new Book();

		book.addTag(tag);

		assertEquals(tag, book.getTags().get(0));
		assertEquals(book, tag.getBooks().get(0));

		book.removeTag(tag);

		assertEquals(0, book.getTags().size());
		assertEquals(0, tag.getBooks().size());
	}

	public void testNotDuplicatedStore() {
		TagList list = new TagList();
		Tag tag = list.getTag("tag1");
		Book book = new Book();
		book.addTag(tag);
		book.addTag(tag);
		assertEquals(1, book.getTags().size());
		assertEquals(1, tag.getBooks().size());
	}
}
