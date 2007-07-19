package libcafe.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.TransferHandler;

import libcafe.Book;
import libcafe.BookList;

public class BooksIntoList extends TransferHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8981273977472075619L;

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
		return true;
	}

	@Override
	public boolean importData(JComponent c, Transferable t) {
		Book book;
		try {
			System.out.println("##");
			book = (Book) t.getTransferData(new DataFlavor(
					DataFlavor.javaJVMLocalObjectMimeType
							+ ";class=java.util.ArrayList"));
			System.out.println("##" + book);

			JList target = (JList) c;
			int index = target.getSelectedIndex();
			BookListListModel model = (BookListListModel) target.getModel();
			BookList bookList = (BookList) model.getElementAt(index);
			bookList.add(book);
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}
}