package gr.blixabargeld.myList.service.implementation;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blixabargeld.myList.dao.RoleDao;
import gr.blixabargeld.myList.dao.UserDao;
import gr.blixabargeld.myList.model.Role;
import gr.blixabargeld.myList.service.RoleService;

/**
 * Role's Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class RoleServiceImplementation implements RoleService {

	@Inject private RoleDao roleDao;
	@Inject private UserDao userDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistRole(Role role, Errors errors) {
		
		validateRole(role, errors);
		boolean valid = !errors.hasErrors();
		if(valid) roleDao.persist(role);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeRole(Role role, Errors errors) {
		
		validateRole(role, errors);
		boolean valid = !errors.hasErrors();
		if(valid) roleDao.merge(role);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Role> getRoles(String property, String order) {

		return roleDao.getAll(property, order);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Role getRole(Long id) {

		return roleDao.get(id);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public boolean deleteRole(Long id) {

		if(!userDao.havingRoleExists(roleDao.get(id))) { //No Users With This Role Exist
			
			roleDao.deleteById(id);
			return true;
		}
		else {
			
			return false;
		}
	}

	/**
	 * Validate Uniqueness Of Role's Title
	 * @param role Role Object
	 * @param errors BindingResult Errors Of Role Form
	 */
	private void validateRole(Role role, Errors errors) {

		Role existing = roleDao.findByTitle(role.getTitle());
		
		if(existing != null && !existing.getId().equals(role.getId()) ) {
		
			errors.rejectValue("title", "error.duplicate");
		}
	}	
}