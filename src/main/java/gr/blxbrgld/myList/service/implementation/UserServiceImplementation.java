package gr.blxbrgld.myList.service.implementation;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blxbrgld.myList.dao.UserDao;
import gr.blxbrgld.myList.model.User;
import gr.blxbrgld.myList.service.UserService;

/**
 * User's Service Implementation
 */
@Service
@Transactional
public class UserServiceImplementation implements UserService {

	@Inject private UserDao userDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistUser(User user, String password, Errors errors) {
		validateUsername(user, errors);
		validateEmail(user, errors);
		boolean valid = !errors.hasErrors();
		if(valid) userDao.persist(user, password);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeUser(User user, String password, Errors errors) {
		validateUsername(user, errors);
		validateEmail(user, errors);
		boolean valid = !errors.hasErrors();
		if(valid) userDao.merge(user, password);		
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<User> getUsers(String property, String order) {
		return userDao.getAll(property, order);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public User getUser(Long id) {
		return userDao.get(id);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}
	
	/**
	 * Get User Object Given It's Username
	 * @param username User's Username
	 * @return User Object
	 */
	public User getUserByUsername(String username) {
		return userDao.findByUsername(username);		
	}
	
	/**
	 * Validate Uniqueness Of User's Username
	 * @param user User Object
	 * @param errors BindingResult Errors Of User Form
	 */
	private void validateUsername(User user, Errors errors) {
		User existing = userDao.findByUsername(user.getUsername());
		if(existing != null && !existing.getId().equals(user.getId())) { //Duplicate Username
			errors.rejectValue("username", "error.duplicate");
		}
	}
	
	/**
	 * Validate Uniqueness Of User's Email
	 * @param user User Object
	 * @param errors BindingResult Errors Of User Form
	 */
	private void validateEmail(User user, Errors errors) {
		User existing = userDao.findByEmail(user.getEmail());
		if(existing != null && !existing.getId().equals(user.getId())) { //Duplicate Email
			errors.rejectValue("email", "error.duplicate");
		}
	}
}