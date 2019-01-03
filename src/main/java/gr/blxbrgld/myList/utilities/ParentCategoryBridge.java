package gr.blxbrgld.mylist.utilities;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.bridge.StringBridge;

import gr.blxbrgld.mylist.model.Item;

/**
 * 'films' or 'music' As Parent Category In A Dedicated Lucene Index Field Depending On Item's Category
 * @author blxbrgld
 */
public class ParentCategoryBridge implements StringBridge {

	@Override
	public String objectToString(Object object) {
		Item item = (Item) object;
		if(StringUtils.containsIgnoreCase(item.getCategory().getTitle(), "films")) {
			return "films";
		} else if(StringUtils.containsIgnoreCase(item.getCategory().getTitle(), "music")) {
			return "music";
		} else {
			return "books";
		}
	}
}