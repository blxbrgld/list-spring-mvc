package gr.blixabargeld.myList.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blixabargeld.myList.dao.ArtistDao;
import gr.blixabargeld.myList.model.Artist;

/**
 * Artist's DAO Implementation
 */
@Repository
public class HibernateArtistDao extends AbstractHibernateDao<Artist> implements ArtistDao {

	@Override
	public Artist findByTitle(String title) {
	
		Query query = getSession().getNamedQuery("findArtistByTitle");
		query.setParameter("title", title);
		return (Artist) query.uniqueResult();
	}

	@Override
	public Artist findLast() {

		Query query = getSession().getNamedQuery("findLastArtist");
		query.setMaxResults(1);
		return (Artist) query.uniqueResult();
	}
	
	@Override
	public Artist findRandom() {
		
		Query query = getSession().createQuery("FROM Artist ORDER BY RAND()");
		query.setMaxResults(1);
		return (Artist) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> findLike(String term) {
	
		Query query = getSession().getNamedQuery("findArtistsLike");
		query.setParameter("term", '%' + term + '%');
		query.setMaxResults(5);
		return (List<Artist>) query.list();
	}
}
