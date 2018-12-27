package gr.blxbrgld.mylist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.dao.hibernate.RoleDao;
import gr.blxbrgld.mylist.dao.hibernate.UserDao;
import gr.blxbrgld.mylist.model.Role;

/**
 * Role's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class RoleServiceImplementation implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistRole(Role role, Errors errors) {
		validateRole(role, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            roleDao.persist(role);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeRole(Role role, Errors errors) {
		validateRole(role, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            roleDao.merge(role);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Role> getRoles(String property, String order) {
		return roleDao.getAll(property, order);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Role getRole(Long id) {
		return roleDao.get(id);
	}

    /**
     * {@inheritDoc}
     */
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