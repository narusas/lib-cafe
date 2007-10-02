package libcafe;

public class BookEvent {

	private final String name;
	private final Object value;
	private final Book src;

	public BookEvent(Book src, String name, Object value) {
		this.src = src;
		this.name = name;
		this.value = value;
	}

	public Book getSrc() {
		return src;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
