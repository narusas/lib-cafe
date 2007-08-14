package libcafe;

import java.util.LinkedList;
import java.util.List;

public class Section {

	private final String sectionName;
	List<Tokens> tokens = new LinkedList<Tokens>();

	public Section(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getName() {
		return sectionName;
	}

	public int length() {
		return tokens.size();
	}

	public Tokens getTokens(int i) {
		return tokens.get(i);
	}

	public void addTokens(Tokens token) {
		tokens.add(token);
	}

}
