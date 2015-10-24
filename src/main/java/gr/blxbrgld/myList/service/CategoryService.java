package gr.blxbrgld.myList.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blxbrgld.myList.model.Category;

/**
 * Category's Service Interface
 */
public interface CategoryService {

	/**
	 * Persist Category Object
	 * @param category Category Object
	 * @param errors BindingResult Errors Of Category Form
	 */
	void persistCategory(Category category, Errors errors);
	
	/**
	 * Merge Category Object
	 * @param category Category Object
	 * @param errors BindingResult Errors Of Category Form
	 */
	void mergeCategory(Category category, Errors errors);
	
	/**
	 * Get All Category Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Categories
	 */
	List<Category> getCategories(String property, String order);

	/**
	 * Get Category Object Given It's Id
	 * @param id Category's Id
	 * @return Category Object
	 */
	Category getCategory(Long id);
	
	/**
	 * Get Category Object Given It's Title
	 * @param title Category's Title
	 * @return Category Object
	 */
	Category getCategory(String title);
	
	/**
	 * Delete Category With The Given Id If There Are No Related Item Objects
	 * @param id Category's Id
	 * @return TRUE or FALSE
	 */
	boolean deleteCategory(Long id);
}
