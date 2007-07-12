package libcafe.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.FocusManager;
import javax.swing.JTextField;
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

		KeyListener validator = new KeyListener() {

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
		};

		ui.priceTextField.addKeyListener(validator);
		ui.titleTextField.addKeyListener(validator);
		ui.yearTextField.addKeyListener(validator);

	}

	void validateData() {
		try {
			validateTitle(ui);
			validateNumber(ui.priceTextField, 20);
			validateNumber(ui.yearTextField, 4);

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

	protected void validateNumber(JTextField ui, int size) {
		String str = ui.getText();

		if ("".equals(str)) {
			ui.setBackground(Color.WHITE);
			return;
		}

		if (str.length() > size) {
			ui.setBackground(WARNING_COLOR);
			throw new RuntimeException("long size");
		}
		try {
			Integer.parseInt(str);
			ui.setBackground(Color.WHITE);
		} catch (NumberFormatException e) {
			ui.setBackground(WARNING_COLOR);

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
		String year = ui.yearTextField.getText();
		String month = (String) ui.monthComboBox.getSelectedItem();
		String day = (String) ui.dayComboBox.getSelectedItem();

		validate(title);
		book.setTitle(title);
		book.setCreator(ui.creatorTextField.getText());
		book.setTranslator(ui.translatorTextFiled.getText());
		book.setPublisher(ui.publisherTextField.getText());

		if (("".equals(year) || "---".equals(month) || "---".equals(day)) == false) {
			Date d = new Date(Integer.parseInt(year) - 1900, Integer
					.parseInt(month) - 1, Integer.parseInt(day));
			book.setPublishDate(d);
			System.out.println(book.getPublishDate());
		}

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
