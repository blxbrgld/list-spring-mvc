package gr.blxbrgld.mylist.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.model.Artist;

/**
 * Artist's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class ArtistDaoImplementation extends AbstractHibernateDao<Artist> implements ArtistDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public Artist findByTitle(String title) {
		Query query = getSession().getNamedQuery("findArtistByTitle");
		query.setParameter("title", title);
		return (Artist) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Artist findLast() {
		Query query = getSession().getNamedQuery("findLastArtist");
		query.setMaxResults(1);
		return (Artist) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Artist findRandom() {
		Query query = getSession().createQuery("FROM Artist ORDER BY RAND()");
		query.setMaxResults(1);
		return (Artist) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> findLike(String term) {
		Query query = getSession().getNamedQuery("findArtistsLike");
		query.setParameter("term", '%' + term + '%');
		query.setMaxResults(5);
		return (List<Artist>) query.list();
	}
}
