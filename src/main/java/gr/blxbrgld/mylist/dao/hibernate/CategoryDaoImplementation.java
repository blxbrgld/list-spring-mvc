package gr.blxbrgld.mylist.dao.hibernate;

import java.util.List;

import gr.blxbrgld.mylist.model.Category;

import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 * Category's DAO Implementation
 * @author blxbrgld
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class CategoryDaoImplementation extends AbstractHibernateDao<Category> implements CategoryDao {

	/**
	 * Override AbstractHibernateDao Method To Include Sorting Functionality According To Category's Parent Title
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Categories
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll(String property, String order) {
		Criteria criteria = getSession().createCriteria(Category.class).createAlias("parent", "parent", JoinType.LEFT_OUTER_JOIN);
		if(order!=null && "DESC".equals(order)) {
			criteria.addOrder(Order.desc(property).nulls(NullPrecedence.FIRST));
		} else if(property!=null) {
			criteria.addOrder(Order.asc(property).nulls(NullPrecedence.FIRST));
		}
		return (List<Category>) criteria.list();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Category findByTitle(String title) {
		Query query = getSession().getNamedQuery("findCategoryByTitle");
		query.setParameter("title", title);
		return (Category) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findByParent(Category parent) {
		Query query = getSession().getNamedQuery("findCategoriesByParent");
		query.setParameter("parent", parent);
		return (List<Category>) query.list();
	}
}
