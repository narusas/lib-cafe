package libcafe.ui;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookDetailViewPanel extends JPanel {
	private JLabel titleLabel;
	private JButton editButton;

	public BookDetailViewPanel() {
		super();
	}

	public JLabel getTitleLabel() {
		if (titleLabel == null) {
			titleLabel = new JLabel();
			titleLabel.setText("Title");
		}
		return titleLabel;
	}

	public JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setText("Edit");
		}
		return editButton;
	}

}
