package gr.blixabargeld.myList.dao.hibernate;

import gr.blixabargeld.myList.dao.SubtitlesDao;
import gr.blixabargeld.myList.model.Subtitles;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Subtitles' DAO Implementation
 */
@Repository
public class HibernateSubtitlesDao extends AbstractHibernateDao<Subtitles> implements SubtitlesDao {

	@Override
	public Subtitles findByTitle(String title) {
		
		Query query = getSession().getNamedQuery("findSubtitlesByTitle");
		query.setParameter("title", title);
		return (Subtitles) query.uniqueResult();
	}
}
