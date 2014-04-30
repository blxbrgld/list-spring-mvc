package gr.blixabargeld.myList.dao;

import java.util.List;

import gr.blixabargeld.myList.model.Artist;
import gr.blixabargeld.myList.model.Category;
import gr.blixabargeld.myList.model.Item;
import gr.blixabargeld.myList.model.Subtitles;
import gr.blixabargeld.myList.utilities.ReturningValues;

/**
 * Item's DAO Interface
 */
public interface ItemDao extends AbstractDao<Item> {

	/**
	 * Get All Category's Items. No Paging Is Required because The Method Is Only Used For .XLS Export
	 * @param category Category Object
	 * @return List Of Items
	 */
	List<Item> findByCategory(Category category);
	
	/**
	 * Find All Item's Related With The Given Artist
	 * @param artist Artist Object
	 * @param first First Page Result
	 * @param size Results Per Page
	 * @return List Of Items
	 */
	List<Item> findByArtist(Artist artist, int first, int size);
	
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
	ReturningValues search(String searchFor, String searchIn, String property, String order, int first, int size);
	
	/**
	 * Re-Build Lucene Index From Scratch
	 * @param synchronously Index Synchronously or Asynchronously
	 */
	void lucene(boolean synchronously);
	
	/**
	 * Check If Items Having The Given Category Exist
	 * @param category Category Object
	 * @return TRUE or FALSE
	 */
	boolean havingCategoryExists(Category category);
	
	/**
	 * Check If Items Having The Given Subtitles Exist
	 * @param subtitles Subtitles Object
	 * @return TRUE or FALSE
	 */
	boolean havingSubtitlesExists(Subtitles subtitles);
	
	/**
	 * Count All Items Existing In Database Having The Given Category
	 * @param category Category's Title
	 * @return Count Of Items
	 */
	Long countItems(String category);
	
	/**
	 * Find Last Created Item Having The Given Parent Category
	 * @param parent Parent Category's Title
	 * @return Item Object
	 */
	Item findLastDate(String parent);
	
	/**
	 * Find Next Available Place For Items With The Given Parent Category
	 * @param parent Parent Category's Title
	 * @return Next Available Place
	 */
	Integer findNextPlace(String parent);
}
