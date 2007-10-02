package libcafe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import libcafe.Book;
import libcafe.fetcher.DaumFetcher;

import org.xml.sax.SAXException;

public class AddBookBySearchPanelTest {
	static List<Book> books = new LinkedList<Book>();

	public static void main(String[] args) {
		JFrame f = SwingUtil.createJFrame(400, 600);
		final AddBookBySearchPanel p = new AddBookBySearchPanel();
		f.getContentPane().add(p);

		final DefaultTableModel model = new DefaultTableModel() {
			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public int getRowCount() {
				return books.size();
			}

			@Override
			public Object getValueAt(int row, int column) {
				Book book = books.get(row);
				switch (column) {
				case 0:
					return book.getTitle();
				case 1:
					return book.getCreator();
				}
				return null;
			}

			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Title";

				case 1:
					return "Creator";
				}
				return null;
			}
		};
		p.searchedBooksTable.setModel(model);

		p.searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final String text = p.searchTextField.getText();
				if ("".equals(text) || text == null) {
					return;
				}

				new Thread() {
					@Override
					public void run() {
						DaumFetcher fetcher = new DaumFetcher("");
						try {
							books = fetcher.query(text);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (SAXException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								model.fireTableDataChanged();
							}
						});
					}
				}.start();
			}
		});
	}
}
