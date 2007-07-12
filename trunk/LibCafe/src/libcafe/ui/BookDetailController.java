package libcafe.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import libcafe.Book;

public class BookDetailController {
	private final BookDetailViewPanel panel;
	private Book book;

	public BookDetailController(BookDetailViewPanel panel) {
		this.panel = panel;

		init();
	}

	private void init() {
		panel.getEditButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				okButtonPressed();
			}
		});
	}

	protected void okButtonPressed() {
		if (book == null) {
			return;
		}
		// book.setTitle(panel.getTitleTextField().getText());
	}

	public void setBook(Book book) {
		this.book = book;
		panel.getTitleLabel().setText(book.getTitle());

		panel.getEditButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

}
