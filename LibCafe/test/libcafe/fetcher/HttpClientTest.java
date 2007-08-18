package libcafe.fetcher;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientTest extends TestCase{
	public void testClient() throws IOException{
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod("http://www.apache.org/");
		client.executeMethod(method);
		byte[] body = method.getResponseBody();
		System.out.println(new String(body));
	}
}
