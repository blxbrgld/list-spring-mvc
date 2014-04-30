package gr.blixabargeld.myList.service;

import gr.blixabargeld.myList.model.Artist;
import gr.blixabargeld.myList.model.ArtistActivityItem;
import gr.blixabargeld.myList.model.Item;

/**
 * ArtistActivityItem's Service Interface
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
