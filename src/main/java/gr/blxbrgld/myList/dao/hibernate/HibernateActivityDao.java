package gr.blxbrgld.myList.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.myList.dao.ActivityDao;
import gr.blxbrgld.myList.model.Activity;

/**
 * Activity's DAO Implementation
 */
@Repository
public class HibernateActivityDao extends AbstractHibernateDao<Activity> implements ActivityDao {

	@Override
	public Activity findByTitle(String title) {
		Query query = getSession().getNamedQuery("findActivityByTitle");
		query.setParameter("title", title);
		return (Activity) query.uniqueResult();
	}
}
