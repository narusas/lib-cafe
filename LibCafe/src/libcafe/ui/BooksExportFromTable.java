package libcafe.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import libcafe.Book;

public class BooksExportFromTable extends TransferHandler {
	DataFlavor localArrayListFlavor, serialArrayListFlavor;
	String localArrayListType = DataFlavor.javaJVMLocalObjectMimeType
			+ ";class=java.util.ArrayList";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6548930467239552647L;
	private Book book;

	public BooksExportFromTable() {
		try {
			localArrayListFlavor = new DataFlavor(localArrayListType);
		} catch (ClassNotFoundException e) {
			System.out
					.println("ArrayListTransferHandler: unable to create data flavor");
		}
		serialArrayListFlavor = new DataFlavor(ArrayList.class, "ArrayList");
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		System.out.println(c);
		BookListTableUI table = (BookListTableUI) c;
		BookListTableController controllerModel = (BookListTableController) table
				.getModel();

		int row = table.getSelectedRow();
		book = controllerModel.getBookByRow(row);
		return new BookListTransferable(book);
	}

	public class BookListTransferable implements Transferable {
		Book data;

		public BookListTransferable(Book book) {
			data = book;
		}

		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException {
			return data;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { localArrayListFlavor,
					serialArrayListFlavor };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return true;
		}
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}

}
