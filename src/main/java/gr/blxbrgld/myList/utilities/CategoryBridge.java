package gr.blxbrgld.mylist.utilities;

import org.hibernate.search.bridge.StringBridge;
import org.springframework.util.StringUtils;

import gr.blxbrgld.mylist.model.Item;

/**
 * First Token Of Item's Category Title In A Dedicated Lucene Index Field
 * @author blxbrgld
 */
public class CategoryBridge implements StringBridge {

	@Override
	public String objectToString(Object object) {
		Item item = (Item) object;
		String[] tokens = StringUtils.tokenizeToStringArray(item.getCategory().getTitle(), " ");
		return tokens[0].toLowerCase();
	}
}
