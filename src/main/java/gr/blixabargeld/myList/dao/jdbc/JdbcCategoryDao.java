package gr.blixabargeld.myList.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import gr.blixabargeld.myList.dao.CategoryDetailsDao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Category's DAO Implementation
 */
@Repository
public class JdbcCategoryDao implements CategoryDetailsDao {

	@Inject private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_PARENTS_QUERY = "SELECT Id, Title FROM Categories WHERE Parent IS NULL ORDER BY Id ASC";
	private static final String FIND_BY_PARENT_QUERY = "SELECT Id, Title FROM Categories WHERE Parent = ? ORDER BY Id ASC";

	@Override
	public Map<String, List<String>> categoriesTree() {

		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<Integer, String> categoriesMap = new TreeMap<Integer, String>();
		Map<String, List<String>> categoriesTree = new TreeMap<String, List<String>>();
		
		/*
		 * Create Map Of Parent Categories
		 */
		rows = jdbcTemplate.queryForList(FIND_PARENTS_QUERY);
		
		for(Map<String, Object> row : rows) {
			
			categoriesMap.put((Integer)row.get("Id"), (String)row.get("Title"));
		}
		
		/*
		 * Create Map Of Parent With Children
		 */
		Iterator<Entry<Integer, String>> entries = categoriesMap.entrySet().iterator();
		
		while(entries.hasNext()) {
		
			List<String> categoriesList = new ArrayList<String>();
			Entry<Integer, String> entry = entries.next();
			rows = jdbcTemplate.queryForList(FIND_BY_PARENT_QUERY, new Object[] { entry.getKey() });
			
			for(Map<String, Object> row : rows) { //Temporary List Of Children
				
				categoriesList.add((String)row.get("Title"));
			}
			
			categoriesTree.put(entry.getValue(), categoriesList);
		}

		return categoriesTree;
	}
}
