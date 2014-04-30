package gr.blixabargeld.myList.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blixabargeld.myList.model.User;

/**
 * User's Service Interface
 */
public interface UserService {

	/**
	 * Persist User Object
	 * @param user User Object
	 * @param password User's Password
	 * @param errors BindingResult Errors Of User Form
	 */
	void persistUser(User user, String password, Errors errors);
	
	/**
	 * Merge User Object
	 * @param user User Object
	 * @param password User's Password
	 * @param errors BindingResult Errors Of User Form
	 */
	void mergeUser(User user, String password, Errors errors);
	
	/**
	 * Get All User Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Users
	 */
	List<User> getUsers(String property, String order);
	
	/**
	 * Get User Object Given It's Id
	 * @param id User's Id
	 * @return User Object
	 */
	User getUser(Long id);

	/**
	 * Delete User With The Given Id
	 * @param id User's Id
	 */
	void deleteUser(Long id);
	
	/**
	 * Get User Object Given It's Username
	 * @param username User's Username
	 * @return User Object
	 */
	User getUserByUsername(String username);
}
