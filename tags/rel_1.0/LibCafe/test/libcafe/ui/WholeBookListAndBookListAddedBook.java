package libcafe.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libcafe.Book;
import libcafe.BookList;

public class WholeBookListAndBookListAddedBook {
	
	public static void main(String[] args) {
		final Fixture fixture = new Fixture();
		fixture.list1.add(fixture.book1);
		fixture.list1.add(fixture.book2);
		fixture.list2.add(fixture.book3);

		JFrame f = SwingUtil.createJFrame(500, 400);
		f.setLocation(200, 200);
		final JTable table = new BookListTableUI();
		final BookListTableController model = new BookListTableController();
		model.setBookList(fixture.list1);
		table.setModel(model);
		
		f.getContentPane().add(table, BorderLayout.WEST);
		f.validate();
		

		BookListListUI ui = new BookListListUI();
		BookListListController controller = new BookListListController(ui);
		fixture.list1.addBookListener(fixture.wList);
		fixture.wList.add(fixture.wList);
		fixture.wList.add(fixture.list1);
		fixture.wList.add(fixture.list2);
		controller.setWholeBookList(fixture.wList);

		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.validate();
		
		ui.bookListList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList j = (JList) e.getSource();
				BookList list = fixture.wList.getBookList(j.getSelectedIndex());
				model.setBookList(list);
			}
		});
		table.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE)
				{
					model.remove(model.getBookByRow(table.getSelectedRow()));
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
		});
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Book book = new Book();
				book.setTitle("tested");
				fixture.list1.add(book);
				System.out.println("added");
				System.out.println(fixture.wList.size());
			}
		}).start();
		
	}
}
