package gr.blxbrgld.myList.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.myList.dao.RoleDao;
import gr.blxbrgld.myList.model.Role;

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