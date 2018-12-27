package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.model.Role;

/**
 * Role's DAO Interface
 * @author blxbrgld
 */
public interface RoleDao extends AbstractDao<Role> {

	/**
	 * Find Role Having The Given Title
	 * @param title Role's Title
	 * @return Role Object
	 */
	Role findByTitle(String title);
}
