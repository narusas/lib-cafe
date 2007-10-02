package libcafe.fetcher;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import libcafe.Book;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class ChannelFetcher {
	protected String apikey;

	protected DocumentBuilder builder;

	public ChannelFetcher(String apikey) {
		this.apikey = apikey;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		}
	}

	public List<Book> query(String query) throws UnsupportedEncodingException,
			SAXException, IOException {
		String xml = fetchXML(query);

		Document doc = createDOM(xml);

		Node channel = getChannelNode(doc);
		NodeList items = channel.getChildNodes();
		return parseItems(items);
	}

	private Document createDOM(String xml) throws SAXException, IOException,
			UnsupportedEncodingException {
		return builder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
	}

	private List<Book> parseItems(NodeList childs) {
		List<Book> res = new LinkedList<Book>();
		for (int i = 0; i < childs.getLength(); i++) {
			if ("item".equals(childs.item(i).getNodeName())) {
				Book book = convertItemToBook(childs.item(i));
				res.add(book);
			}
		}
		return res;
	}

	abstract protected Node getChannelNode(Document doc);

	private String fetchXML(String query) throws UnsupportedEncodingException,
			IOException, HttpException {
		HttpClient client = new HttpClient();
		HttpMethod method = createMethod(query);
		client.executeMethod(method);
		byte[] body = method.getResponseBody();
		String xml = new String(body, "utf-8");
		return xml;
	}

	abstract protected HttpMethod createMethod(String query)
			throws UnsupportedEncodingException;

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
