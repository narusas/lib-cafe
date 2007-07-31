package libcafe;

public class Entity {

	public static int staticId;
	protected int id;

	public Entity() {
		id = staticId++;
	}

	public Entity(int id) {
		this.id = id;
		staticId = id + 1;
	}

	public int getID() {
		return id;
	}

}
