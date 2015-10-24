package gr.blxbrgld.myList.dao;

import gr.blxbrgld.myList.model.Role;

/**
 * Role's DAO Interface
 */
public interface RoleDao extends AbstractDao<Role> {

	/**
	 * Find Role Having The Given Title
	 * @param title Role's Title
	 * @return Role Object
	 */
	Role findByTitle(String title);
}
