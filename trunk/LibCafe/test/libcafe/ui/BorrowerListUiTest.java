package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import libcafe.Borrower;
import libcafe.BorrowerList;

public class BorrowerListUiTest {
	public static void main(String[] args) {

		final Borrower borrower1 = new Borrower();
		borrower1.setName("¼º¹Î");
		final Borrower borrower2 = new Borrower();
		borrower2.setName("¼º¹Î2");
		final BorrowerList list = new BorrowerList();
		list.add(borrower1);

		BorrowerListModel controller = new BorrowerListModel();
		controller.setList(list);

		BorrowerListUI ui = new BorrowerListUI();
		ui.setModel(controller);

		JFrame f = SwingUtil.createJFrame(400, 400);
		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.validate();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				

				list.add(borrower2);

				System.out.println("List Added");
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				
				list.remove(borrower1);

				System.out.println("List Removed");
			}
		}.start();


		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
				}
				borrower2.setName("¼º¹Î3");

				System.out.println("Borrower Changed");
			}
		}.start();

	}
}
