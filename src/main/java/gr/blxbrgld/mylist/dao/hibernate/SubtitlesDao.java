package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.model.Subtitles;

/**
 * Subtitles' DAO Interface
 * @author blxbrgld
 */
public interface SubtitlesDao extends AbstractDao<Subtitles> {

	/**
	 * Find Subtitles Having The Given Title
	 * @param title Subtitles' Title
	 * @return Subtitles Object
	 */
	Subtitles findByTitle(String title);
}
