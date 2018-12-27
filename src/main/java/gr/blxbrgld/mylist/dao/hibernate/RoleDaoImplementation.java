package gr.blxbrgld.mylist.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.model.Role;

/**
 * Role's DAO Implementation
 * @author blxbrgld
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class RoleDaoImplementation extends AbstractHibernateDao<Role> implements RoleDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public Role findByTitle(String title) {
		Query query = getSession().getNamedQuery("findRoleByTitle");
		query.setParameter("title", title);
		return (Role) query.uniqueResult();
	}
}