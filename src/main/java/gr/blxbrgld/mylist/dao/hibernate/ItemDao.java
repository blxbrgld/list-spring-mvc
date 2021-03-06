package gr.blxbrgld.mylist.dao.hibernate;

import java.util.List;

import gr.blxbrgld.mylist.model.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Item's DAO Interface
 * @author blxbrgld
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
	 * @return ImmutablePair Containing The Number Of Results As The Key And The Actual Results As The Value
	 */
	ImmutablePair<Integer, List<Item>> search(String searchFor, String searchIn, String property, String order, int first, int size);
	
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
	 * Check If Items Having The Given Publisher Exist
	 * @param publisher Publisher Object
	 * @return TRUE or FALSE
	 */
	boolean havingPublisherExists(Publisher publisher);

	/**
	 * Count All Items Existing In Database Having The Given Category
	 * @param title Category's Title
	 * @return Count Of Items
	 */
	Long countItems(String title);
	
	/**
	 * Find Last Created Item Having The Given Category
	 * @param title Category's Title
	 * @param parent Boolean Indicating If The Given Category Is A Parent Category Or Not
	 * @return Item Object
	 */
	Item findLastDate(String title, boolean parent);
	
	/**
	 * Find Next Available Place For Items With The Given Parent Category
	 * @param parent Parent Category's Title
	 * @return Next Available Place
	 */
	Integer findNextPlace(String parent);
}
