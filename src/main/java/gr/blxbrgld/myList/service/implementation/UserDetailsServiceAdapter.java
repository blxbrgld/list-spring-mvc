package gr.blxbrgld.myList.service.implementation;

import gr.blxbrgld.myList.dao.UserDetailsDao;
import gr.blxbrgld.myList.model.User;
import gr.blxbrgld.myList.model.UserDetailsAdapter;
import gr.blxbrgld.myList.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetails' Service Implementation
 */
@Service("userDetailsServiceAdapter")
@Transactional
public class UserDetailsServiceAdapter implements UserDetailsService {

	@Inject UserService userService;
	@Inject UserDetailsDao userDetailsDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = userService.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		}
		else if(user.getRole() == null) {
			throw new UsernameNotFoundException("User: " + username + " has no authorities");
		}
		UserDetailsAdapter userAdapter = new UserDetailsAdapter(user);
		userAdapter.setPassword(userDetailsDao.findPasswordByUsername(username));
		return userAdapter;
	}
}