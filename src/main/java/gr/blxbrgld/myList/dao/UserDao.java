package gr.blxbrgld.myList.dao;

import java.util.List;

import gr.blxbrgld.myList.model.Role;
import gr.blxbrgld.myList.model.User;

/**
 * User's DAO Interface
 */
public interface UserDao extends AbstractDao<User> {

	/**
	 * Password-Aware persist Method For User Object
	 * @param user User Object
	 * @param password User's Password To Persist
	 */
	void persist(User user, String password);
	
	/**
	 * Password-Aware merge Method For User Object
	 * @param user User Object
	 * @param password User's Password To Merge
	 */
	void merge(User user, String password);
	
	/**
	 * Find User Having The Given Username
	 * @param username User's Username
	 * @return User Object
	 */
	User findByUsername(String username);
	
	/**
	 * Find User Having The Given Email
	 * @param email User's Email
	 * @return User Object
	 */
	User findByEmail(String email);
	
	/**
	 * Find Users Having The Given Role
	 * @param role Role Object
	 * @return List Of Users
	 */
	List<User> findByRole(Role role);
	
	/**
	 * Check If Users Having The Given Role Exist
	 * @param role Role Object
	 * @return TRUE or FALSE
	 */
	boolean havingRoleExists(Role role);
}