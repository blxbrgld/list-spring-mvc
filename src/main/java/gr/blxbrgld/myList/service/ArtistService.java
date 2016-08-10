package gr.blxbrgld.mylist.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.model.Artist;

/**
 * Artist's Service Interface
 * @author blxbrgld
 */
public interface ArtistService {

	/**
	 * Persist Artist Object
	 * @param artist Artist Object
	 * @param errors BindingResult Errors Of Artist Form
	 */
	void persistArtist(Artist artist, Errors errors);
	
	/**
	 * Persist Artist Object Without Validating Values Given
	 * @param artist Artist Object
	 */
	void persistArtist(Artist artist);
	
	/**
	 * Merge Artist Object
	 * @param artist Artist Object
	 * @param errors BindingResult Errors Of Artist Form
	 */
	void mergeArtist(Artist artist, Errors errors);
	
	/**
	 * Get All Artist Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Artists
	 */
	List<Artist> getArtists(String property, String order);
	
	/**
	 * Get All Artist Objects With Pagination
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @param first First Page Result
	 * @param size Results Per Page
	 * @return List Of Artists
	 */
	List<Artist> getArtists(String property, String order, int first, int size);
	
	/**
	 * Get Artist Object Given It's Id
	 * @param id Artist's Id
	 * @return Artist Object
	 */
	Artist getArtist(Long id);
	
	/**
	 * Get Artist Object Given It's Title
	 * @param title Artist's Title
	 * @return Artist Object
	 */
	Artist getArtist(String title);
	
	/**
	 * Delete Artist With The Given Id If There Are No Related ArtistActivityItem Objects
	 * @param id Artist's Id
	 * @return TRUE or FALSE
	 */
	boolean deleteArtist(Long id);
	
	/**
	 * Count All Artist Objects
	 * @return Count Of Artists
	 */
	Long countArtists();
	
	/**
	 * Find Last Created Artist
	 * @return Artist Object
	 */
	Artist findLastArtist();
	
	/**
	 * Find Random Artist Object
	 * @return Artist Object
	 */
	Artist findRandomArtist();
	
	/**
	 * Create A List<String> Of Artist Titles Containing The Given Term to Accomplish JQuery's Autocomplete Functionality 
	 * @param term Search Term
	 * @return List Of Artist Titles
	 */
	List<String> findArtistsLike(String term);
}