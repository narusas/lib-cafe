package libcafe.ui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import libcafe.Book;

public class SearchResultDialog extends JDialog{

	private JTable table = new JTable();
	public SearchResultDialog(List<Book> books){
		setLayout(new BorderLayout());
		setSize(300, 500);
		
		SearchResultModel model = new SearchResultModel();
		model.addAll(books);
		table.setModel(model);
		ScrollPane pane = new ScrollPane();
		pane.add(table);
		getContentPane().add(table, BorderLayout.CENTER);
	}

	public Book getBook() {
		return null;
	}
}

class SearchResultModel extends DefaultTableModel
{
	private List<Book> books = new ArrayList<Book>();
	
	public void addAll(List<Book> books) {
		this.books.addAll(books);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class[] clazz = new Class[] { JButton.class, String.class, String.class,
				Integer.class };
		return clazz[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		String[] headers = new String[] { "º±≈√", "Title", "Author" };
		return headers[columnIndex];
	}

	@Override
	public int getRowCount() {
		if (books == null) {
			return 0;
		}
		return books.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (books == null) {
			return null;
		}
		switch (columnIndex) {
		case 0:
			JButton btn = new JButton("+");
			return btn;
		case 1:
			return books.get(rowIndex).getTitle();
		case 2:
			return books.get(rowIndex).getCreator();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean[] editable = new boolean[] { false, false, false };
		return editable[columnIndex];
	}
}
