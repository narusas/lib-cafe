package libcafe.ui;

import java.io.File;
import java.io.FileInputStream;

import libcafe.AutoSaver;
import libcafe.BorrowerList;
import libcafe.DeSerializer;
import libcafe.Library;
import libcafe.TagList;
import libcafe.WholeBookList;

public class Main {
	public static void main(String[] args) {
		File file = new File("library.txt");
		Library library;
		try {
			library = DeSerializer.loadLibrary(new FileInputStream(file));
		} catch (Exception e) {
			 e.printStackTrace();
			library = new Library();
			library.setWholeBookList(new WholeBookList());
			library.setBorrowList(new BorrowerList());
			library.setTagList(new TagList());
		}

		AutoSaver saver = new AutoSaver("library.txt");
		saver.set(library);
		library.addLibraryListener(saver);

		MainFrame f = new MainFrame();
		MainController c = new MainController(f, library);
		c.init();
		f.setVisible(true);
	}
}
