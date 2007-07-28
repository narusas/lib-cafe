package libcafe;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for libcafe");
		//$JUnit-BEGIN$
		suite.addTestSuite(BookTest.class);
		suite.addTestSuite(DateTest.class);
		suite.addTestSuite(BorrowerTest.class);
		suite.addTestSuite(BasicTest.class);
		suite.addTestSuite(TagTest.class);
		suite.addTestSuite(LibraryTest.class);
		//$JUnit-END$
		return suite;
	}

}
