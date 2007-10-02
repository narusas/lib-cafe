package libcafe.fetcher;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.xml.sax.SAXException;

import junit.framework.TestCase;
import libcafe.Book;

public class NaverFetcherTest extends TestCase {

	public void testWork() throws HttpException, IOException, SAXException {

		String apikey = "41fbf00c99adcd9293d95acb9383c28b";

		NaverFetcher fetcher = new NaverFetcher(apikey);
		List<Book> books = fetcher.queryByISBN("ÅÂ¹é»ê¸Æ");
		assertTrue(books.size() > 0);
		for (Book book : books) {
			System.out.println(book);	
		}
		

	}
}
