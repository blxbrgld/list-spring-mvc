package gr.blixabargeld.myList.utilities;

import gr.blixabargeld.myList.model.Item;

import org.hibernate.search.bridge.StringBridge;
import org.springframework.util.StringUtils;

/**
 * First Token Of Item's Category Title In A Dedicated Lucene Index Field
 */
public class CategoryBridge implements StringBridge {

	@Override
	public String objectToString(Object object) {

		Item item = (Item) object;
	
		String[] tokens = StringUtils.tokenizeToStringArray(item.getCategory().getTitle(), " ");
		
		return tokens[0].toLowerCase();
	}
}
