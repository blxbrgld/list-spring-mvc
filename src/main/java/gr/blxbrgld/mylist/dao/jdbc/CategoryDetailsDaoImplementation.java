package gr.blxbrgld.mylist.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Category's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class CategoryDetailsDaoImplementation implements CategoryDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_PARENTS_QUERY = "SELECT Id, Title FROM Categories WHERE Parent IS NULL ORDER BY Id ASC";
	private static final String FIND_BY_PARENT_QUERY = "SELECT Id, Title FROM Categories WHERE Parent = ? ORDER BY Id ASC";

    /**
     * {@inheritDoc}
     */
	@Override
	public Map<String, List<String>> categoriesTree() {
		Map<Integer, String> categoriesMap = new TreeMap<>();
		Map<String, List<String>> categoriesTree = new TreeMap<>();
		/*
		 * Create Map Of Parent Categories
		 */
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(FIND_PARENTS_QUERY);
		for(Map<String, Object> row : rows) {
			categoriesMap.put((Integer)row.get("Id"), (String)row.get("Title"));
		}
		/*
		 * Create Map Of Parent With Children
		 */
		Iterator<Entry<Integer, String>> entries = categoriesMap.entrySet().iterator();
		while(entries.hasNext()) {
			List<String> categoriesList = new ArrayList<>();
			Entry<Integer, String> entry = entries.next();
			//noinspection RedundantArrayCreation
			rows = jdbcTemplate.queryForList(FIND_BY_PARENT_QUERY, new Object[] { entry.getKey() });
			for(Map<String, Object> row : rows) { //Temporary List Of Children
				categoriesList.add((String)row.get("Title"));
			}
			categoriesTree.put(entry.getValue(), categoriesList);
		}
		return categoriesTree;
	}
}
