package libcafe;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for libcafe");
		// $JUnit-BEGIN$
		suite.addTestSuite(BookTest.class);
		suite.addTestSuite(BorrowerTest.class);
		suite.addTestSuite(TagTest.class);
		// $JUnit-END$
		return suite;
	}

}
