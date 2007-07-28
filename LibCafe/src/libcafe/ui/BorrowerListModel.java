package libcafe.ui;

import javax.swing.AbstractListModel;

import libcafe.Borrower;
import libcafe.BorrowerList;
import libcafe.BorrowerListListener;

public class BorrowerListModel extends AbstractListModel implements BorrowerListListener {

	private BorrowerList list;

	public void setList(BorrowerList list) {
		this.list = list;
		list.addListener(this);

	}

	@Override
	public Object getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list == null ? 0 :list.size();
	}

	@Override
	public void borrowerAdded(Borrower borrower) {
		fireContentsChanged(this, 0, list.size());
	}

	@Override
	public void borrowerRemoved(Borrower borrower) {
		fireContentsChanged(this, 0, list.size());
	}

	@Override
	public void borrowerChanged(Borrower borrower) {
		fireContentsChanged(this, 0, list.size());
	}
}
