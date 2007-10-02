package libcafe;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeSerializer {

	static Pattern tokensP = Pattern.compile("(\\d+).([^=]+)=(.*)$");

	static Pattern keyValueP = Pattern.compile("([^=]+)=(.*)$");
	static Pattern headerP = Pattern.compile("<([^>]+)>");

	static Pattern sectionsP = Pattern.compile("^<([^>]+)>", Pattern.MULTILINE);

	public static Tokens parse(String src) {
		Matcher m = tokensP.matcher(src);
		if (m.find()) {
			return new Tokens(m.group(1), m.group(2), m.group(3));
		}
		m = keyValueP.matcher(src);
		if (m.find()) {
			return new Tokens(m.group(1), null, m.group(2));
		}
		return null;
	}

	public static Section parseSection(String src) {
		String[] lines = src.split("\n");
		Section sec = new Section(parseHeader(lines[0]));

		for (int i = 1; i < lines.length; i++) {
			sec.addTokens(parse(lines[i]));
		}

		return sec;
	}

	private static String parseHeader(String src) {
		Matcher m = headerP.matcher(src);
		m.find();
		return m.group(1);
	}

	public static List<Section> parseSections(String src) {
		List<Section> res = new LinkedList<Section>();

		Matcher m = sectionsP.matcher(src);

		int progress = 0;
		int startPos = 0;

		while (m.find(progress)) {
			progress = m.end();
			if (m.start() == 0) {
				continue;
			}
			String sectionSrc = src.substring(startPos, m.start() - 1);
			res.add(parseSection(sectionSrc));
			startPos = m.start();
		}

		String sectionSrc = src.substring(startPos);
		res.add(parseSection(sectionSrc));

		return res;
	}

	public static Map<Integer, Book> convertBooksFrom(Section section) {
		int len = parseSectionLength(section);
		BookList list = new BookList();

		HashMap<Integer, Book> temp = new HashMap<Integer, Book>();

		for (int i = 1; i < section.length(); i++) {
			Tokens token = section.getTokens(i);
			Book book = null;
			if (temp.containsKey(Integer.parseInt(token.getId()))) {
				book = temp.get(Integer.parseInt(token.getId()));
			} else {
				book = new Book();
				book.id = Integer.parseInt(token.getId());
				temp.put(book.id, book);
			}

			setupBook(book, token.getAttribute(), token.getValue());
		}

		return temp;
	}

	private static void setupBook(Book book, String attr, String value) {
		if ("title".equals(attr)) {
			book.setTitle(value);
		} else if ("creator".equals(attr)) {
			book.setCreator(value);
		}
	}

	public static Map<BookList, List<Integer>> convertBookListsFrom(Section section) {
		int len = parseSectionLength(section);
		HashMap<BookList, List<Integer>> res = new HashMap<BookList, List<Integer>>();
		for (int i = 1; i < section.length(); i += 2) {
			Tokens nameToken = section.getTokens(i);
			Tokens itemsToken = section.getTokens(i + 1);

			BookList list = new BookList();
			list.id = Integer.parseInt(nameToken.getId());
			list.name = nameToken.getValue();

			List<Integer> items = new LinkedList<Integer>();
			String[] itemsSrc = itemsToken.getValue().split(",");
			for (String s : itemsSrc) {
				if ("".equals(s)) {
					continue;
				}
				items.add(Integer.parseInt(s.trim()));
			}
			res.put(list, items);
		}
		return res;
	}

	private static int parseSectionLength(Section section) {
		return Integer.parseInt(section.getTokens(0).getValue());
	}

	public static List<BookList> parseBooksAndBookList(Section bookSection, Section booklistSection) {
		List<BookList> res = new LinkedList<BookList>();

		Map<Integer, Book> allBooks = DeSerializer.convertBooksFrom(bookSection);
		Map<BookList, List<Integer>> bookLists = DeSerializer.convertBookListsFrom(booklistSection);

		Iterator<Entry<BookList, List<Integer>>> it = bookLists.entrySet().iterator();
		while (it.hasNext()) {
			Entry<BookList, List<Integer>> entry = (Entry<BookList, List<Integer>>) it.next();

			BookList list = entry.getKey();
			for (Integer itemNo : entry.getValue()) {
				list.add(allBooks.get(itemNo));
			}
			res.add(list);

		}
		return res;
	}

	public static WholeBookList parseWholeBooklist(Section bookSection, Section booklistSection) {
		List<BookList> list = parseBooksAndBookList(bookSection, booklistSection);
		WholeBookList wList = new WholeBookList();
		wList.books.addAll(findWholeBooks(list));
		wList.bookLists.addAll(exceptForWhole(list));
		return wList;
	}

	private static Collection<? extends BookList> exceptForWhole(List<BookList> list) {
		List<BookList> res = new LinkedList<BookList>();
		for (BookList bookList : list) {
			if (bookList.getID() == 0) {
				continue;
			}
			res.add(bookList);
		}
		return res;
	}

	private static Collection<? extends Book> findWholeBooks(List<BookList> list) {
		for (BookList bookList : list) {
			if (bookList.getID() == 0) {
				return bookList.books;
			}
		}
		return null;
	}

	public static BorrowerList parseBorrowerList(Map<Integer, Book> allBooks, Section borrowerSection) {
		Map<BookList, List<Integer>> borrowers = DeSerializer.convertBookListsFrom(borrowerSection);
		Iterator<Entry<BookList, List<Integer>>> it = borrowers.entrySet().iterator();
		BorrowerList res = new BorrowerList();
		while (it.hasNext()) {
			Entry<BookList, List<Integer>> entry = (Entry<BookList, List<Integer>>) it.next();
			Borrower b = new Borrower();
			b.setName(entry.getKey().getName());

			for (Integer itemNo : entry.getValue()) {
				b.add(allBooks.get(itemNo));
			}
			res.add(b);
		}

		return res;
	}

	public static Library loadLibrary(InputStream in) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		byte[] buf = new byte[4096];
		while (true) {
			int r = in.read(buf, 0, 4096);
			if (r == -1) {
				break;
			}
			bout.write(buf, 0, r);
		}
		
		String src = new String(bout.toByteArray());
		
		List<Section> sections = DeSerializer.parseSections(src);

		Map<Integer, Book> allBooks = DeSerializer.convertBooksFrom(sections.get(0));
		WholeBookList wList = DeSerializer.parseWholeBooklist(sections.get(0), sections.get(1));
		BorrowerList bList = DeSerializer.parseBorrowerList(allBooks, sections.get(2));

		Library lib = new Library();
		lib.setBorrowList(bList);
		lib.setWholeBookList(wList);
		return lib;
	}
}
