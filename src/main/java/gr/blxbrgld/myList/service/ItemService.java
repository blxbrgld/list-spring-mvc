package gr.blxbrgld.mylist.service;

import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.utilities.ReturningValues;

import java.util.List;

import org.springframework.validation.Errors;

/**
 * Item's Service Interface
 * @author blxbrgld
 */
public interface ItemService {

	/**
	 * Persist Item Object
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	void persistItem(Item item, Errors errors);
	
	/**
	 * Merge Item Object
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	void mergeItem(Item item, Errors errors);

	/**
	 * Get All Item Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @param first First Page Result
	 * @param size Results Per Page
	 * @return List Of Items
	 */
	List<Item> getItems(String property, String order, int first, int size);

	/**
	 * Get All Items Having The Given Category
	 * @param category Category Object
	 * @return List Of Items
	 */
	List<Item> getItemsHavingCategory(Category category);
	
	/**
	 * Get All Items Related To The Given Artist
	 * @param artist Artist Object
	 * @param first First Page Result
	 * @param size Results Per Page
	 * @return List Of Items
	 */
	List<Item> getItemsHavingArtist(Artist artist, int first, int size);
	
	/**
	 * Search For Items
	 * @param searchFor Search Term
	 * @param searchIn Limit Results Setting This Parameter to 'Music', 'Films' etc.
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @param first First Page Result
	 * @param size Results Per Page
	 * @return Returning Values Object Containing A List Of Items and Number Of Results
	 */
	ReturningValues searchItems(String searchFor, String searchIn, String property, String order, int first, int size);
	
	/**
	 * Re-Build Lucene Index From Scratch
	 * @param synchronously Index Synchronously or Asynchronously
	 */
	void initializeLucene(boolean synchronously);
	
	/**
	 * Get Item Object Given It's Id
	 * @param id Item's Id
	 * @return Item Object
	 */
	Item getItem(Long id);
	
	/**
	 * Delete Item Object
	 * @param item Item Object
	 */
	void deleteItem(Item item);
	
	/**
	 * Count All Item Objects
	 * @return Count Of Items
	 */
	Long countItems();
	
	/**
	 * Count All Items Having The Given Category
	 * @param category Category's Title
	 * @return Count Of Items
	 */
	Long countItemsHavingCategory(String category);
	
	/**
	 * Find Last Item Having The Given Category As Parent Category
	 * @param parent Parent Category
	 * @return Item Object
	 */
	Item findLastDateHavingParent(String parent);
	
	/**
	 * Find Next Available Place For Items With The Given Category As Parent Category
	 * @param parent Parent Category
	 * @return Next Available Place
	 */
	Integer findNextPlaceHavingParent(String parent);
}
