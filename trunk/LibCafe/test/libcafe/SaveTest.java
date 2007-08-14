package libcafe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

public class SaveTest extends TestCase {

	public void testDummy() {
		Library lib = new Library();
		WholeBookList wBookList = new WholeBookList();
		BorrowerList bwerList = new BorrowerList();

		BookList bList = new BookList(10);

		Book book1 = new Book(100);
		Book book2 = new Book();

		book1.setTitle("title1");
		book2.setTitle("title2");
		book2.setCreator("author");

		bList.setName("list1");

		bList.add(book2);
		wBookList.add(book1);
		wBookList.add(bList);

		Borrower bwer1 = new Borrower(1);
		Borrower bwer2 = new Borrower(2);

		bwer1.setName("¼º¹Î");
		bwer2.setName("Àº¹¬");

		bwer1.add(book1);

		bwerList.add(bwer1);
		bwerList.add(bwer2);
		
		
		
		lib.setWholeBookList(wBookList);
		lib.setBorrowList(bwerList);

		// LibrarySaver libSaver = new LibrarySaver();
		// File f = new File("temp");
		// FileOutputStream fout = new FileOutputStream(f);
		// ByteArrayOutputStream bout = new ByteArrayOutputStream();
		//		
		//		
		// libSaver.save(lib, (OutputStream)bout);
		//		
		// System.out.println(new String(bout.toByteArray()));
		assertEquals("100.title=title1\n", Serializer.serialize(book1));
		assertEquals("101.title=title2\n" + // 
				"101.creator=author\n", Serializer.serialize(book2));

		assertEquals("<Books>\n" + //
				"length=2\n" + //
				"100.title=title1\n" + //
				"101.title=title2\n" + // 
				"101.creator=author\n", Serializer.serializeBooks(wBookList));

		assertEquals("10.name=list1\n" + // 
				"10.items=101,\n", Serializer.serialize(bList));
		
		assertEquals("<BookList>\n" + //
				"length=2\n" + //
				"0.name=Whole Book List\n" + //
				"0.items=100,101,\n" + //
				"10.name=list1\n" + // 
				"10.items=101,\n" , Serializer.serializeBookList(wBookList));

		// borrower 1¸í Å×½ºÆ®.
		assertEquals("1.name=¼º¹Î\n" + "1.items=100,\n", Serializer
				.serialize(bwer1));

		assertEquals("2.name=Àº¹¬\n", Serializer.serialize(bwer2));

		assertEquals("<Borrowers>\n" + //
				"length=2\n" + //
				"1.name=¼º¹Î\n" + //
				"1.items=100,\n" + //
				"2.name=Àº¹¬\n" //
		, Serializer.serializeBorrowerList(bwerList));
		
		
		
		assertEquals("<Books>\n" + //
				"length=2\n" + //
				"100.title=title1\n" + //
				"101.title=title2\n" + // 
				"101.creator=author\n" + //
				
				"<BookList>\n" + //
				"length=2\n" + //
				"0.name=Whole Book List\n" + //
				"0.items=100,101,\n" + //
				"10.name=list1\n" + // 
				"10.items=101,\n" + //
				
				"<Borrowers>\n" + //
				"length=2\n" + //
				"1.name=¼º¹Î\n" + //
				"1.items=100,\n" + //
				"2.name=Àº¹¬\n" //
				, Serializer.serializeLibrary(lib));
				
		
		
	}
}
