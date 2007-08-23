package libcafe.ui;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.ListModel;

import org.xml.sax.SAXException;

import libcafe.Book;
import libcafe.BookList;
import libcafe.fetcher.DaumFetcher;

public class AddFormController {

	private AddFormUI ui;
	private BookList list;

	public AddFormController(final AddFormUI ui) {
		this.ui = ui;
		ui.titleAuthorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DaumFetcher fetcher = new DaumFetcher("");
				String title = ui.title.getText();
				String author = ui.author.getText();
				List<Book> books;
				try {
					if (!title.isEmpty() && !author.isEmpty())
						books = fetcher.query(title + " " + author);
					else if (!title.isEmpty())
						books = fetcher.query(title);
					else if (!author.isEmpty()) {
						books = fetcher.query(author);
					} else {
						return;
					}
					SearchResultDialog re = new SearchResultDialog(books);
					re.setModal(true);
					re.setVisible(true);
					Book book = re.getBook();
					if (book != null) {
						list.add(book);
					}

				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (SAXException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void setBookList(BookList list) {
		this.list = list;
	}
}
