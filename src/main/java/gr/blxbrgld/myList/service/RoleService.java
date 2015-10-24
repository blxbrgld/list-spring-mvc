package gr.blxbrgld.myList.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blxbrgld.myList.model.Role;

/**
 * Role's Service Interface
 */
public interface RoleService {

	/**
	 * Persist Role Object
	 * @param role Role Object
	 * @param errors BindingResult Errors Of Role Form
	 */
	void persistRole(Role role, Errors errors);
	
	/**
	 * Merge Role Object
	 * @param role Role Object
	 * @param errors BindingResult Errors Of Role Form
	 */
	void mergeRole(Role role, Errors errors);
	
	/**
	 * Get All Role Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Roles
	 */
	List<Role> getRoles(String property, String order);
	
	/**
	 * Get Role Object Given It's Id
	 * @param id Role's Id
	 * @return Role Object
	 */
	Role getRole(Long id);
	
	/**
	 * Delete Role With The Given Id If There Are No Related User Objects
	 * @param id Role's Id
	 * @return TRUE or FALSE
	 */
	boolean deleteRole(Long id);
}
