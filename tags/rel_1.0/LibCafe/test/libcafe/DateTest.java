package libcafe;

import junit.framework.TestCase;

public class DateTest extends TestCase {
	public void testCreateMinus() {
		Date d = new Date(2, -1, -1);
		System.out.println(d.getYear());
		System.out.println(d.getMonth());
		System.out.println(d.getDay());
	}
}
