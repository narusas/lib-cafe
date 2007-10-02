package libcafe;

import java.util.HashMap;

public class TagList {
	HashMap<String, Tag> map = new HashMap<String, Tag>();

	public Tag getTag(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		Tag tag = new TagImpl(name);
		map.put(name, tag);
		return tag;
	}

	class TagImpl extends Tag {
		protected TagImpl(String name) {
			super(name);
		}
	}
}
