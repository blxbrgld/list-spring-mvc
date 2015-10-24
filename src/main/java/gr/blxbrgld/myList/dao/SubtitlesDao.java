package gr.blxbrgld.myList.dao;

import gr.blxbrgld.myList.model.Subtitles;

/**
 * Subtitles' DAO Interface 
 */
public interface SubtitlesDao extends AbstractDao<Subtitles> {

	/**
	 * Find Subtitles Having The Given Title
	 * @param title Subtitles' Title
	 * @return Subtitles Object
	 */
	Subtitles findByTitle(String title);
}
