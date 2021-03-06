package gr.blxbrgld.mylist.dao.jdbc;

import java.util.List;
import java.util.Map;

/**
 * CategoryDetails' DAO Interface
 * @author blxbrgld
 */
public interface CategoryDetailsDao {

	/**
	 * Tree Of Parent Categories And Their Children Loaded On Application's Startup Via JDBC To Display Menu And Other Navigation Options
	 * @return Map of Every Parent's Title and It's Children As A List
	 */
	Map<String, List<String>> categoriesTree();
}
