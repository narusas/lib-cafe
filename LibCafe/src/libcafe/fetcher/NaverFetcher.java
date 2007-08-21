package libcafe.fetcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import libcafe.Book;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NaverFetcher extends ChannelFetcher {

	public NaverFetcher(String apikey) {
		super(apikey);
	}

	@Override
	protected HttpMethod createMethod(String isbn)
			throws UnsupportedEncodingException {
		HttpMethod method = new GetMethod("http://openapi.naver.com/search?"// 
				+ "key="
				+ apikey// 
				+ "&target=book_adv"//
				+ "&display=50"//
				+ "&start=1"//
				+ "&d_isbn=" + URLEncoder.encode(isbn, "utf-8")
				+ "&query="
				+ URLEncoder.encode(isbn, "utf-8"));

		return method;
	}

	@Override
	protected Node getChannelNode(Document doc) {
		NodeList childs = doc.getChildNodes();

		Node channel = childs.item(0).getChildNodes().item(0);
		return channel;
	}

	public List<Book> queryByISBN(String isbn)
			throws UnsupportedEncodingException, SAXException, IOException {
		return query(isbn);
	}
}
