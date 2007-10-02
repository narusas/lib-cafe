package libcafe.ui;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.xml.sax.SAXException;

import libcafe.Book;
import libcafe.BookList;
import libcafe.fetcher.DaumFetcher;
import libcafe.fetcher.NaverFetcher;

public class AddFormController {

	private AddFormUI ui;
	private SearchResultDialog searchResUI;

	List<Book> books = null;
	protected Book book;

	public AddFormController(final AddFormUI ui) {
		this.ui = ui;

		setupLabledTextEdit(ui.title, "Title");
		setupLabledTextEdit(ui.author, "Author");
		setupLabledTextEdit(ui.isbn, "ISBN");

		ui.isbnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				NaverFetcher fetcher = new NaverFetcher("");
				String isbn = ui.isbn.getText();

				try {
					if (isNotEmpty(isbn, "ISBN")) {
						System.out.println("ISBN query");
						books = fetcher.query(isbn);
					}

					setupSearchResultDialog(books);

				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (SAXException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		ui.titleAuthorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DaumFetcher fetcher = new DaumFetcher("");
				String title = ui.title.getText();
				String author = ui.author.getText();

				try {
					if (isNotEmpty(title, "Title")
							&& isNotEmpty(author, "Author")) {
						System.out.println("And query");
						books = fetcher.query(title + " " + author);
					} else if (isNotEmpty(title, "Title")) {
						System.out.println("Title query");
						books = fetcher.query(title);
					} else if (isNotEmpty(author, "Author")) {
						System.out.println("Author query");

						books = fetcher.query(author);
					} else {
						return;
					}

					setupSearchResultDialog(books);

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

	private void setupLabledTextEdit(final JTextField target,
			final String targetText) {
		target.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (targetText.equals(target.getText())) {
					target.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (target.getText() == null
						|| "".equals(target.getText().trim())) {
					target.setText(targetText);
				}

			}

		});
	}

	private boolean isNotEmpty(String text, String defaultText) {
		return !defaultText.equals(text) && !text.isEmpty();
	}

	private void setupSearchResultDialog(List<Book> books) {
		searchResUI = new SearchResultDialog(books);

		searchResUI.selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchResUI.setVisible(false);
			}
		});

		searchResUI.setModal(true);
		searchResUI.setVisible(true);

		book = searchResUI.getBook();

		System.out.println("## " + book);
		notifyBookSelected(book);
	}

	private void notifyBookSelected(Book book) {
		bookSelectedListener.bookSelected(book);
	}

	BookSelectedListener bookSelectedListener;

}

interface BookSelectedListener {
	void bookSelected(Book book);
}
