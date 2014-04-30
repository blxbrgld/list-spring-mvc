package gr.blixabargeld.myList.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blixabargeld.myList.dao.RoleDao;
import gr.blixabargeld.myList.model.Role;

/**
 * Role's DAO Implementation
 */
@Repository
public class HibernateRoleDao extends AbstractHibernateDao<Role> implements RoleDao {

	@Override
	public Role findByTitle(String title) {

		Query query = getSession().getNamedQuery("findRoleByTitle");
		query.setParameter("title", title);
		return (Role) query.uniqueResult();
	}
}