package libcafe.fetcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import junit.framework.TestCase;
import libcafe.Book;

import org.xml.sax.SAXException;

public class HttpClientTest extends TestCase {

	public void testDaumFetcher() throws UnsupportedEncodingException,
			SAXException, IOException {
		String apikey = "13672e9ee069b904f2e229f5b6e7d2b362d4306b";
		DaumFetcher fetcher = new DaumFetcher(apikey);
		List<Book> books = fetcher.query("¾ß¿Í¶ó");
		assertTrue(books.size() > 0);

		books = fetcher.query("TDD");
		assertTrue(books.size() > 0);
	}
}
