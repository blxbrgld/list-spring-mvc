package gr.blxbrgld.mylist.dao;

import java.util.List;

import gr.blxbrgld.mylist.model.Artist;

/**
 * Artist's DAO Interface
 * @author blxbrgld
 */
public interface ArtistDao extends AbstractDao<Artist> {

	/**
	 * Find Artist Having The Given Title
	 * @param title Artist's Title
	 * @return Artist Object
	 */
	Artist findByTitle(String title);
	
	/**
	 * Find Last Created Artist
	 * @return Artist Object
	 */
	Artist findLast();
	
	/**
	 * Find A Random Artist
	 * @return Artist Object
	 */
	Artist findRandom();
	
	/**
	 * Find Artists With Title Containing The Given Search Term
	 * @param term Search Term
	 * @return List Of Artists
	 */
	List<Artist> findLike(String term);
}
