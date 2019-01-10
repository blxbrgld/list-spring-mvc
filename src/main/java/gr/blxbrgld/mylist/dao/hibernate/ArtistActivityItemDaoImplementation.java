package gr.blxbrgld.mylist.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.model.Activity;
import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.ArtistActivityItem;
import gr.blxbrgld.mylist.model.Item;

/**
 * ArtistActivityItem's DAO Implementation
 * @author blxbrgld
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class ArtistActivityItemDaoImplementation extends AbstractHibernateDao<ArtistActivityItem> implements ArtistActivityItemDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public Long countArtistActivityItems(Artist artist) {
		Query query = getSession().getNamedQuery("countItemsHavingArtist");
		query.setParameter("artist", artist);
		return (Long) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean havingArtistExists(Artist artist) {
		Query query = getSession().getNamedQuery("findArtistActivityItemsByArtist");
		query.setParameter("artist", artist);
		query.setMaxResults(1);
        return !query.list().isEmpty();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean havingActivityExists(Activity activity) {
		Query query = getSession().getNamedQuery("findArtistActivityItemsByActivity");
		query.setParameter("activity", activity);
		query.setMaxResults(1);
        return !query.list().isEmpty();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void deleteByItem(Item item) {
		Query query = getSession().getNamedQuery("deleteArtistActivityItemByItem");
		query.setParameter("item", item);
		query.executeUpdate();
	}
}
