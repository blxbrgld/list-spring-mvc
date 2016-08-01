package gr.blxbrgld.myList.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.myList.dao.ArtistActivityItemDao;
import gr.blxbrgld.myList.model.Activity;
import gr.blxbrgld.myList.model.Artist;
import gr.blxbrgld.myList.model.ArtistActivityItem;
import gr.blxbrgld.myList.model.Item;

/**
 * ArtistActivityItem's DAO Implementation
 */
@Repository
public class HibernateArtistActivityItemDao extends AbstractHibernateDao<ArtistActivityItem> implements ArtistActivityItemDao {

	@Override
	public Long countArtistActivityItems(Artist artist) {
		Query query = getSession().getNamedQuery("countItemsHavingArtist");
		query.setParameter("artist", artist);
		return (Long) query.uniqueResult();
	}
	
	@Override
	public boolean havingArtistExists(Artist artist) {
		Query query = getSession().getNamedQuery("findArtistActivityItemsByArtist");
		query.setParameter("artist", artist);
		query.setMaxResults(1);
		if(query.list().isEmpty()) return false; else return true;
	}
	
	@Override
	public boolean havingActivityExists(Activity activity) {
		Query query = getSession().getNamedQuery("findArtistActivityItemsByActivity");
		query.setParameter("activity", activity);
		query.setMaxResults(1);
		if(query.list().isEmpty()) return false; else return true;
	}
	
	@Override
	public void deleteByItem(Item item) {
		Query query = getSession().getNamedQuery("deleteArtistActivityItemByItem");
		query.setParameter("item", item);
		query.executeUpdate();
	}
}