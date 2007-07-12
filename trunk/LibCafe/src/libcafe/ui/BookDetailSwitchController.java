package libcafe.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BookDetailSwitchController {
	private final JPanel parent;
	private final BookDetailViewPanel view;
	private final BookDeailEditPanel edit;

	public BookDetailSwitchController(JPanel parent, BookDetailViewPanel view,
			BookDeailEditPanel edit) {
		this.parent = parent;
		this.view = view;
		this.edit = edit;
		view.getEditButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToEdit();
			}
		});
	}

	protected void switchToEdit() {
		parent.remove(view);
		parent.add(edit, BorderLayout.CENTER);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				parent.updateUI();
			}
		});
	}
}
