package libcafe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SwingUtil {
	public static JFrame createJFrame(int width, int height) {
		JFrame f = new JFrame();
		f.setSize(width, height);
		f.getContentPane().setLayout(new BorderLayout());

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return f;
	}
}
