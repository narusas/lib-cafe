package libcafe;

public class BookEvent {

	private final String name;
	private final Object value;

	public BookEvent(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}
