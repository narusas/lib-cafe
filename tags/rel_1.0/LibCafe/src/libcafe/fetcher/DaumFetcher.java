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
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import libcafe.Book;

public class DaumFetcher extends ChannelFetcher {
	public DaumFetcher(String apikey) {
		super("13672e9ee069b904f2e229f5b6e7d2b362d4306b");
	}

	protected Node getChannelNode(Document doc) {
		NodeList childs = doc.getChildNodes();
		Node channel = childs.item(0);
		return channel;
	}

	protected HttpMethod createMethod(String query)
			throws UnsupportedEncodingException {
		HttpMethod method = new GetMethod("http://apis.daum.net/search/book?"// 
				+ "result=19" //
				+ "&apikey=" + apikey// 
				+ "&q=" + URLEncoder.encode(query, "euc-kr"));
		return method;
	}
}
