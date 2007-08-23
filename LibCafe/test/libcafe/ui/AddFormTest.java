package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class AddFormTest {
	public static void main(String[] args) {
		final Fixture fixture = new Fixture();
		
		JFrame frame = SwingUtil.createJFrame(300, 420);
		frame.setLocation(100, 100);
		
		AddFormUI ui = new AddFormUI();
		AddFormController controller = new AddFormController(ui);
//		controller.setBookList(fixture.list1);
		
		BookListTableUI listUI = new BookListTableUI();
		BookListTableController listController = new BookListTableController();
		listController.setBookList(fixture.list1);
		listUI.setModel(listController);
		
		frame.getContentPane().add(ui, BorderLayout.CENTER);
		frame.getContentPane().add(listUI, BorderLayout.SOUTH);
		frame.validate();
	}
	
	
}
