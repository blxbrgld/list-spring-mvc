package gr.blxbrgld.myList.utilities;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.bridge.StringBridge;

import gr.blxbrgld.myList.model.Item;

/**
 * 'films' or 'music' As Parent Category In A Dedicated Lucene Index Field Depending On Item's Category
 */
public class ParentCategoryBridge implements StringBridge {

	@Override
	public String objectToString(Object object) {
		Item item = (Item) object;
		if(StringUtils.containsIgnoreCase(item.getCategory().getTitle(), "films")) {
			return "films";
		}
		else {
			return "music";
		}
	}
}