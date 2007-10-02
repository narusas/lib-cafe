package libcafe.ui;

import java.awt.datatransfer.DataFlavor;

import libcafe.Book;

public class Constants {
	public static DataFlavor DATA_FLAVOR;

	public static String DATA_FLAVOR_STR = DataFlavor.javaJVMLocalObjectMimeType + ";class=" + Book.class.getName();

	public static DataFlavor SERIAL_FLAVOR = new DataFlavor(Book.class, "Book");
	static {
		try {
			DATA_FLAVOR = new DataFlavor(DATA_FLAVOR_STR);
		} catch (ClassNotFoundException e) {
		}
	}
}
