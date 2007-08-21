package libcafe;

import java.io.File;
import java.io.IOException;

import net.narusas.util.lang.NFile;

import junit.framework.TestCase;

public class AutoSaverTest extends TestCase {
	public void testWork() throws IOException {
		Library lib = new Library();
		WholeBookList wList = new WholeBookList();
		TagList tList = new TagList();
		BorrowerList bList = new BorrowerList();

		lib.setWholeBookList(wList);
		lib.setTagList(tList);
		lib.setBorrowList(bList);

		AutoSaver saver = new AutoSaver("library.txt");
		lib.addLibraryListener(saver);
		saver.set(lib);

		Book book = new Book();
		book.setTitle("test");
		lib.getWholeBookList().add(book);
		// TODO more test!!!!!

		File f = new File("library.txt");
		assertTrue(f.exists());

		String text = NFile.getText(f);
		assertTrue(text.contains("test"));
		f.delete();
	}
}
