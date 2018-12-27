package gr.blxbrgld.mylist.service;

import gr.blxbrgld.mylist.dao.jdbc.UserDetailsDao;
import gr.blxbrgld.mylist.model.User;
import gr.blxbrgld.mylist.model.UserDetailsAdapter;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetails' Service Implementation
 * @author blxbrgld
 */
@Service("userDetailsServiceAdapter")
@Transactional
public class UserDetailsServiceAdapter implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsDao userDetailsDao;

    /**
     * Load A UserDetails Object Given A Username
     * @param username User's Username
     * @return UserDetails Object
     * @throws UsernameNotFoundException In Case Of An Error
     */
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userService.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("No such user: " + username);
		} else if(user.getRole() == null) {
			throw new UsernameNotFoundException("User: " + username + " has no authorities");
		}
		UserDetailsAdapter userAdapter = new UserDetailsAdapter(user);
		userAdapter.setPassword(userDetailsDao.findPasswordByUsername(username));
		return userAdapter;
	}
}