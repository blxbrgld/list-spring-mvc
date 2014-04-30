package gr.blixabargeld.myList.utilities;

import gr.blixabargeld.myList.model.Item;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.bridge.StringBridge;

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