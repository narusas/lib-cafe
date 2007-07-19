package libcafe.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import libcafe.Book;

public class BookAddTest {
	public static void main(String[] args) {

		final Fixture fixture = new Fixture();
		fixture.list1.add(fixture.book1);
		fixture.list1.add(fixture.book2);

		JFrame f = SwingUtil.createJFrame(200, 400);
		f.setLocation(200, 200);
		final JTable table = new BookListTableUI();
		final BookListTableController model = new BookListTableController();
		model.setBookList(fixture.list1);
		table.setModel(model);

		f.getContentPane().add(table, BorderLayout.CENTER);
		f.validate();

		JFrame editFrame = SwingUtil.createJFrame(400, 400);
		editFrame.setLocation(400, 200);

		BookEditUI ui = new BookEditUI();
		final BookEditController controller = new BookEditController(ui);
		Book book = new Book();
		controller.setBook(book);

		editFrame.getContentPane().add(ui, BorderLayout.CENTER);
		editFrame.validate();
		table.getSelectionModel().setSelectionMode(0);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							return;
						}
						int index = table.getSelectedRow();
						if (index == -1) {
							return;
						}
						Book book = fixture.list1.get(index);
						controller.setBook(book);
						System.out.println(e.getSource());
					}
				});

		ui.saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK");
			}
		});
	}
}