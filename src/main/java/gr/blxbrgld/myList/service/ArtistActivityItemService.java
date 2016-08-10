package gr.blxbrgld.mylist.service;

import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.ArtistActivityItem;
import gr.blxbrgld.mylist.model.Item;

/**
 * ArtistActivityItem's Service Interface
 * @author blxbrgld
 */
public interface ArtistActivityItemService {

	/**
	 * Persist ArtistActivityItem Object
	 * @param artistActivityItem ArtistActivityItem Object
	 */
	void persistArtistActivityItem(ArtistActivityItem artistActivityItem);
	
	/**
	 * Delete ArtistActivityItem Objects Given The Related Item Object
	 * @param item Item Object
	 */
	void deleteArtistActivityItem(Item item);
	
	/**
	 * Count All ArtistActivityItem Objects Having The Given Artist Object
	 * @param artist Artist Object
	 * @return Count Of ArtistActivityItems
	 */
	Long countArtistActivityItemsHavingArtist(Artist artist);
}
