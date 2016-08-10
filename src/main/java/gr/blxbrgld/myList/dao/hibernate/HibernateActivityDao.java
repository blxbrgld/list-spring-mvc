package gr.blxbrgld.mylist.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.dao.ActivityDao;
import gr.blxbrgld.mylist.model.Activity;

/**
 * Activity's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class HibernateActivityDao extends AbstractHibernateDao<Activity> implements ActivityDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public Activity findByTitle(String title) {
		Query query = getSession().getNamedQuery("findActivityByTitle");
		query.setParameter("title", title);
		return (Activity) query.uniqueResult();
	}
}
