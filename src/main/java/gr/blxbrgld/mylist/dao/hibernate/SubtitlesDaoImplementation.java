package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.model.Subtitles;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Subtitles' DAO Implementation
 * @author blxbrgld
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class SubtitlesDaoImplementation extends AbstractHibernateDao<Subtitles> implements SubtitlesDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public Subtitles findByTitle(String title) {
		Query query = getSession().getNamedQuery("findSubtitlesByTitle");
		query.setParameter("title", title);
		return (Subtitles) query.uniqueResult();
	}
}
