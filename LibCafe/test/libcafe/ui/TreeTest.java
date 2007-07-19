package libcafe.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class TreeTest {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(400, 400);
		f.getContentPane().setLayout(new BorderLayout());
		JTree ui = new JTree();
		TreeModel model = new FileTreeModel(new File("."));
		ui.setModel(model);
		f.getContentPane().add(ui, BorderLayout.CENTER);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FileTreeModel extends DefaultTreeModel {

	public FileTreeModel(File root) {
		super(new FileTreeNode(root));
	}

}

class FileTreeNode implements TreeNode {

	private final File file;
	private File parent;

	public FileTreeNode(File file) {
		this.file = file;
	}

	public FileTreeNode(File parent, File file) {
		this.parent = parent;
		this.file = file;
	}

	@Override
	public Enumeration children() {
		if (file.isDirectory()) {
			final File[] childs = file.listFiles();
			return new Enumeration() {
				int i = 0;

				@Override
				public boolean hasMoreElements() {
					return i < childs.length;
				}

				@Override
				public Object nextElement() {
					return childs[i++];
				}
			};
		}
		return new Enumeration() {

			@Override
			public boolean hasMoreElements() {
				return false;
			}

			@Override
			public Object nextElement() {
				return null;
			}
		};
	}

	@Override
	public boolean getAllowsChildren() {
		return file.isDirectory();
	}

	@Override
	public TreeNode getChildAt(int index) {
		return new FileTreeNode(file, file.listFiles()[index]);
	}

	@Override
	public int getChildCount() {
		return file.listFiles().length;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return new FileTreeNode(parent);
	}

	@Override
	public boolean isLeaf() {
		return file.isFile();
	}

	@Override
	public String toString() {
		return file.getName();
	}

}
