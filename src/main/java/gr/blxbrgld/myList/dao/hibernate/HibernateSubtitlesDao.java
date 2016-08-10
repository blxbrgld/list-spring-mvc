package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.SubtitlesDao;
import gr.blxbrgld.mylist.model.Subtitles;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Subtitles' DAO Implementation
 * @author blxbrgld
 */
@Repository
public class HibernateSubtitlesDao extends AbstractHibernateDao<Subtitles> implements SubtitlesDao {

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
