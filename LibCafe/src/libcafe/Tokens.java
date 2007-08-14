package libcafe;

public class Tokens {

	private final String id;
	private final String attr;
	private final String value;

	public Tokens(String id, String attr, String value) {
		this.id = id;
		this.attr = attr;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public String getAttribute() {
		return attr;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		Tokens target = (Tokens) obj;

		return id.equals(target.id) && value.equals(target.value)
				&& (attr == target.attr || attr.equals(target.attr));
	}
}
