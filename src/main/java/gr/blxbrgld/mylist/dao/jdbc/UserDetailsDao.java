package gr.blxbrgld.mylist.dao.jdbc;

/**
 * UserDetails' DAO Interface
 * @author blxbrgld
 */
public interface UserDetailsDao {

	/**
	 * Given A Username Find User's Password Via JDBC Call To Avoid Having Sensitive Information Hang Around With User Objects
	 * @param username User's Usename
	 * @return User's Password
	 */
	String findPasswordByUsername(String username);
}
