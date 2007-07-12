package libcafe.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.FocusManager;
import javax.swing.SwingUtilities;

import libcafe.Book;

public class BookEditController {

	private final BookEditUI ui;
	private Book book;

	static Color WARNING_COLOR = new Color(240, 180, 150);

	public BookEditController(final BookEditUI ui) {
		this.ui = ui;
		ui.saveButton.setEnabled(false);
		ui.saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		ui.priceTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				validateData();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		ui.titleTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				validateData();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		
	}

	void validateData() {
		try {
			validateTitle(ui);
			validatePrice(ui);

			enableSaveButton(ui, true);
		} catch (RuntimeException ex) {
			enableSaveButton(ui, false);
		}

	}

	private void validateTitle(final BookEditUI ui) {
		String str = ui.titleTextField.getText();
		if ("".equals(str) || "".equals(str.trim())) {
			ui.titleTextField.setBackground(WARNING_COLOR);
			throw new RuntimeException("no title");
		}

		ui.titleTextField.setBackground(Color.WHITE);
	}

	protected void validatePrice(BookEditUI ui) {
		System.out.println("test Validate price");
		String str = ui.priceTextField.getText();
		if ("".equals(str)) {
			ui.priceTextField.setBackground(Color.WHITE);
			return;
		}
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ui.priceTextField.setBackground(WARNING_COLOR);

			throw new RuntimeException(e);
		}
	}

	private void enableSaveButton(final BookEditUI ui, final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ui.saveButton.setEnabled(b);
			}
		});

	}

	protected void save() {
		System.out.println("saved");
		String title = ui.titleTextField.getText();
		String price = ui.priceTextField.getText();

		validate(title);
		book.setTitle(title);
		book.setCreator(ui.creatorTextField.getText());
		book.setTranslator(ui.translatorTextFiled.getText());
		book.setPublisher(ui.publisherTextField.getText());
		
		book.setCategory(ui.categoryTextField.getText());
		book.setDescription(ui.descriptionTextField.getText());
		book.setIsbn(ui.isbnTextField.getText());

		if ("".equals(price) == false) {
			book.setPrice(Integer.parseInt(price));
		}

	}

	private void validate(String str) {
		if ("".equals(str) || "".equals(str.trim())) {
			throw new RuntimeException("No Title");
		}
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
