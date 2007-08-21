package libcafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AutoSaver implements LibraryListener {

	private final String location;
	private Library lib;

	public AutoSaver(String location) {
		this.location = location;
	}

	@Override
	public void wholeBookListModified(WholeBookList list, BookList list2) {
		try {
			File f = new File(location);
			if (f.exists()==false){
				f.createNewFile();
			}
			FileOutputStream fout = new FileOutputStream(f);
			Serializer.saveLibrary(fout, lib);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set(Library lib) {
		this.lib = lib;
	}

}
