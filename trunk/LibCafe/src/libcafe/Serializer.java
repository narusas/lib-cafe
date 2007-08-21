package libcafe;

import java.io.IOException;
import java.io.OutputStream;

public class Serializer {

	public static String serialize(Book book) {
		String res = "";

		res += getParam(book.getID(), "title", book.getTitle());
		res += getParam(book.getID(), "creator", book.getCreator());

		return res;
	}

	public static String serialize(Borrower bwer) {
		String res = "";

		res += getParam(bwer.getID(), "name", bwer.getName());
		res += getParam(bwer.getID(), "items", collectIDs(bwer));
		return res;
	}

	public static String serialize(BookList list) {
		String res = "";

		res += getParam(list.getID(), "name", list.getName());
		res += getParam(list.getID(), "items", collectIDs(list));

		return res;
	}

	private static String collectIDs(BookList list) {
		String temp = "";

		for (int i = 0; i < list.size(); i++) {
			temp += list.get(i).getID() + ",";
		}
		return temp;
	}

	// 특정한 string 형식으로 리턴.
	private static String getParam(int id, String key, Object value) {
		if (value == null || "".equals(value.toString())) {
			return "";
		}
		return id + "." + key + "=" + value + "\n";
	}

	public static String serializeBooks(WholeBookList bookList) {

		String res = "<Books>\n";
		res += "length=" + bookList.size() + "\n";

		for (int i = 0; i < bookList.size(); i++) {
			res += serialize(bookList.get(i));
		}
		return res;
	}


	public static String serializeBorrowerList(BorrowerList bwerList) {

		String res = "<Borrowers>\n";
		res += "length=" + bwerList.size() + "\n";

		for (int i = 0; i < bwerList.size(); i++) {
			res += serialize(bwerList.get(i));
		}
		return res;
	}

	public static String serializeBookList(WholeBookList wList) {
		String res = "<BookList>\n";

		res += "length=" + wList.size() + "\n";

		for (int i = 0; i < wList.size(); i++) {
			res += serialize(wList.getBookList(i));
		}
		return res;
	}

	public static String serializeLibrary(Library lib) {
		String res = "";

		res += serializeBooks(lib.getWholeBookList());
		res += serializeBookList(lib.getWholeBookList());
		res += serializeBorrowerList(lib.getBorrowList());
		return res;
	}
	
	public static void saveLibrary(OutputStream out, Library lib) throws IOException{
		String data = serializeLibrary(lib);
		out.write(data.getBytes());
		out.flush();
		out.close();
	}

}
