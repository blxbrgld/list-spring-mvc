package gr.blxbrgld.mylist.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.dao.UserDetailsDao;

/**
 * User's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class JdbcUserDetailsDao implements UserDetailsDao {

	@Autowired private JdbcTemplate jdbcTemplate;
	
	private static final String SELECT_QUERY = "SELECT Password FROM Users WHERE Username = ?";

    /**
     * {@inheritDoc}
     */
	@Override
	public String findPasswordByUsername(String username) {
		return jdbcTemplate.queryForObject(SELECT_QUERY, new Object[] { username }, String.class);
	}
}