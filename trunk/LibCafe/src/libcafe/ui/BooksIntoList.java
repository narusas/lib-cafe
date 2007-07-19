package libcafe.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import libcafe.Book;
import libcafe.BookList;

public class BooksIntoList extends TransferHandler {

	

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
		return true;
	}

	@Override
	public boolean importData(JComponent c, Transferable t) {
		Book book;
		try {
			book = (Book) t.getTransferData(Constants.DATA_FLAVOR);
			JList target = (JList) c;
			int index = target.getSelectedIndex();
			BookListListModel model = (BookListListModel) target.getModel();
			BookList bookList = (BookList) model.getElementAt(index);
			bookList.add(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}
}