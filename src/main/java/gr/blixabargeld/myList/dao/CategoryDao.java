package gr.blixabargeld.myList.dao;

import java.util.List;

import gr.blixabargeld.myList.model.Category;

/**
 * Category's DAO Interface
 */
public interface CategoryDao extends AbstractDao<Category> {

	/**
	 * Find Category Having The Given Title
	 * @param title Category's Title
	 * @return Category Object
	 */
	Category findByTitle(String title);
	
	/**
	 * Find Categories Having The Given Parent
	 * @param parent Parent Category Object
	 * @return List Of Categories
	 */
	List<Category> findByParent(Category parent);
}
