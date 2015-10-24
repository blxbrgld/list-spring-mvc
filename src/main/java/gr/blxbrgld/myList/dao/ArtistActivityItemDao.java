package gr.blxbrgld.myList.dao;

import gr.blxbrgld.myList.model.Activity;
import gr.blxbrgld.myList.model.Artist;
import gr.blxbrgld.myList.model.ArtistActivityItem;
import gr.blxbrgld.myList.model.Item;

/**
 * ArtistActivityItem's DAO Interface
 */
public interface ArtistActivityItemDao extends AbstractDao<ArtistActivityItem> {

	/**
	 * Count All ArtistActivityItem Objects Having The Given Artist
	 * @param artist Artist Object
	 * @return Count Of ArtistActivityItems
	 */
	Long countArtistActivityItems(Artist artist);
	
	/**
	 * Check If ArtistActivityItem Objects Having The Given Activity Exist
	 * @param activity Activity Object
	 * @return TRUE or FALSE
	 */
	boolean havingActivityExists(Activity activity);
	
	/**
	 * Check If ArtistActivityItem Objects Having The Given Artist Exist
	 * @param artist Artist Object
	 * @return TRUE or FALSE
	 */
	boolean havingArtistExists(Artist artist);
	
	/**
	 * Delete ArtistActivityItem Objects Given The Related Item Object
	 * @param item Item Object
	 */
	void deleteByItem(Item item);
}
