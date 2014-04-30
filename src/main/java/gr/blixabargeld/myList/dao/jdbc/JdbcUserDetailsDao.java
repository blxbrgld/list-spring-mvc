package gr.blixabargeld.myList.dao.jdbc;

import gr.blixabargeld.myList.dao.UserDetailsDao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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