package libcafe;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class LoadTest extends TestCase {
	public void testRegTokens() {
		Pattern p = Pattern.compile("(\\d+).([^=]+)=(.*)$");
		Matcher m = p.matcher("100.title=abc");
		assertTrue(m.find());
	}

	public void testRegKeyValue() {
		Pattern p = Pattern.compile("([^=]+)=(.*)$");
		Matcher m = p.matcher("length=100");
		assertTrue(m.find());
	}

	public void testParseTokens() {
		String src = "100.title=abc";
		Tokens tokens = DeSerializer.parse(src);
		assertEquals("100", tokens.getId());
		assertEquals("title", tokens.getAttribute());
		assertEquals("abc", tokens.getValue());
	}

	public void testParseKeyValue() {
		String src = "length=100";
		Tokens tokens = DeSerializer.parse(src);
		assertEquals("length", tokens.getId());
		assertNull(tokens.getAttribute());
		assertEquals("100", tokens.getValue());
	}

	public void testParseSection() {
		String src = "<Books>\n" + //
				"length=2\n" + //
				"100.title=title1\n" + //
				"101.title=title2\n" + // 
				"101.creator=author\n";

		Section sec = DeSerializer.parseSection(src);
		assertEquals("Books", sec.getName());
		assertEquals(4, sec.length());

		assertEquals(new Tokens("length", null, "2"), sec.getTokens(0));
		assertEquals(new Tokens("101", "creator", "author"), sec.getTokens(3));
	}

	public void testParseSections() {
		String src = "<Books>\n" + // S
				"length=2\n" + //
				"100.title=title1\n" + //
				"101.title=title2\n" + //
				"101.creator=author\n" + //

				"<BookList>\n" + //
				"length=2\n" + //
				"0.name=Whole Book List\n" + //
				"0.items=100,101,\n" + //
				"10.name=list1\n" + //
				"10.items=101,\n";
		List<Section> sections = DeSerializer.parseSections(src);

		assertEquals(2, sections.size());
		assertEquals("Books", sections.get(0).getName());
		assertEquals(4, sections.get(0).length());

		assertEquals("BookList", sections.get(1).getName());
		assertEquals(5, sections.get(1).length());
	}

	public void testExtractTitle() {
		String src = "<Books>";
		Pattern p = Pattern.compile("<([^>]+)>");
		Matcher m = p.matcher(src);
		assertTrue(m.find());
		assertEquals("Books", m.group(1));
	}

	// public void testSystem() {
	// Enumeration<Object> keys = System.getProperties().keys();
	// while (keys.hasMoreElements()) {
	// Object key = (Object) keys.nextElement();
	// System.out.println(key + ":" + System.getProperty((String) key));
	//
	// }
	// }

	public void testSectionsReg() {
		String src = "<a>012\n<b>345\n<c>678\n";
		Pattern p = Pattern.compile("^<([^>]+)>", Pattern.MULTILINE);
		Matcher m = p.matcher(src);
		int pos = 0;
		// while (m.find(pos)) {
		// System.out.println(m.start() + ":" + m.end());
		// pos = m.end() + 1;
		// }
	}

	public void testConvertBooks() {
		String src = "<Books>\n" + // S
				"length=2\n" + //
				"100.title=title1\n" + //
				"101.title=title2\n" + //
				"101.creator=author\n";
		List<Section> sections = DeSerializer.parseSections(src);
		Map<Integer, Book> allBooks = DeSerializer.convertBooksFrom(sections
				.get(0));
		assertEquals(2, allBooks.size());
		Book book1 = allBooks.get(100);
		assertEquals(100, book1.getID());
		assertEquals("title1", book1.getTitle());

		Book book2 = allBooks.get(101);
		assertEquals(101, book2.getID());
		assertEquals("title2", book2.getTitle());
		assertEquals("author", book2.getCreator());
	}

	public void testConvertBookList() {
		String src = "<BookList>\n" + //
				"length=2\n" + //
				"0.name=Whole Book List\n" + //
				"0.items=100,101,\n" + //
				"10.name=list1\n" + // 
				"10.items=101,\n"; //
		List<Section> sections = DeSerializer.parseSections(src);

		Map<BookList, List<Integer>> bookLists = DeSerializer
				.convertBookListsFrom(sections.get(0));

		assertEquals(2, bookLists.size());
		Iterator<BookList> keys = bookLists.keySet().iterator();
		bookLists.entrySet();
		BookList list = (BookList) keys.next();
		assertEquals("list1", list.getName());

		list = (BookList) keys.next();
		assertEquals("Whole Book List", list.getName());

		// TODO 순서는 나중에 고려하기로 함.
	}

	public void testMergeBookAndBookList() {
		String src = "<Books>\n" + //
				"length=2\n" + //
				"100.title=title1\n" + //
				"101.title=title2\n" + // 
				"101.creator=author\n" + //

				"<BookList>\n" + //
				"length=2\n" + //
				"0.name=Whole Book List\n" + //
				"0.items=100,101,\n" + //
				"10.name=list1\n" + // 
				"10.items=101,\n"; //

		List<BookList> bookLists = DeSerializer.parseBooksBookList(src);
		assertEquals(2, bookLists.size());
		

	}

}
