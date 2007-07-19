package libcafe.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import libcafe.Book;

public class BooksExportFromTable extends TransferHandler {
	private static final long serialVersionUID = -6548930467239552647L;
	private Book book;

	@Override
	protected Transferable createTransferable(JComponent c) {
		System.out.println(c);
		BookListTableUI table = (BookListTableUI) c;
		BookListTableController controllerModel = (BookListTableController) table.getModel();

		int row = table.getSelectedRow();
		book = controllerModel.getBookByRow(row);
		return new BookListTransferable(book);
	}

	public class BookListTransferable implements Transferable {
		Book data;

		public BookListTransferable(Book book) {
			data = book;
		}

		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
			return data;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { Constants.DATA_FLAVOR, Constants.SERIAL_FLAVOR };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return true;
		}
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}
}
