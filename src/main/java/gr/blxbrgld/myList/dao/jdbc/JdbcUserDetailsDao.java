package gr.blxbrgld.myList.dao.jdbc;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.myList.dao.UserDetailsDao;

/**
 * User's DAO Implementation
 */
@Repository
public class JdbcUserDetailsDao implements UserDetailsDao {

	@Inject private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_PASSWORD_QUERY = "SELECT Password FROM Users WHERE Username = ?";
	
	@Override
	public String findPasswordByUsername(String username) {
		return jdbcTemplate.queryForObject(FIND_PASSWORD_QUERY, new Object[] { username }, String.class);
	}
}