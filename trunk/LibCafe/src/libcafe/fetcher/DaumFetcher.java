package libcafe.fetcher;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import libcafe.Book;

public class DaumFetcher {

	private final String apikey = "13672e9ee069b904f2e229f5b6e7d2b362d4306b";
	// String apikey = "13672e9ee069b904f2e229f5b6e7d2b362d4306b";

	private DocumentBuilder builder;

	public DaumFetcher(String apikey) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		}
	}

	public List<Book> query(String query) throws UnsupportedEncodingException,
			SAXException, IOException {

		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod("http://apis.daum.net/search/book?"// 
				+ "result=19" //
				+ "&apikey=" + apikey// 
				+ "&q=" + URLEncoder.encode(query, "euc-kr"));
		client.executeMethod(method);
		byte[] body = method.getResponseBody();

		String xml = new String(body, "utf-8");

		Document doc = builder.parse(new ByteArrayInputStream(xml
				.getBytes("utf-8")));
		NodeList childs = doc.getChildNodes();

		Node channel = childs.item(0);
		childs = channel.getChildNodes();

		List<Book> res = new LinkedList<Book>();
		for (int i = 0; i < childs.getLength(); i++) {
			if ("item".equals(childs.item(i).getNodeName())) {
				Book book = convertItemToBook(childs.item(i));
				res.add(book);
			}
		}

		return res;
	}

	private Book convertItemToBook(Node item) {
		Book book = new Book();
		NodeList childs = item.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node n = childs.item(i);
			String nodeName = n.getNodeName();
			if ("title".equals(nodeName)) {
				book.setTitle(removeTag(getNodeValue(n)));
			} else if ("author".equals(nodeName)) {
				book.setCreator(removeTag(getNodeValue(n)));
			}
		}
		return book;
	}

	private String removeTag(String nodeValue) {
		return nodeValue.replaceAll("<[^>]+>", "");
	}

	private String getNodeValue(Node n) {
		return n.getChildNodes().item(0).getNodeValue();
	}

}
