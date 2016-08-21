package gr.blxbrgld.mylist.dao.jdbc;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author blxbrgld
 */
public class JdbcUserDetailsDaoTest extends DaoTestBase {

    @Autowired private JdbcUserDetailsDao userDetailsDao;

    @Test
    public void findPasswordByUsername() {
        String password = userDetailsDao.findPasswordByUsername("blixabargeld");
        Assert.assertTrue(password != null && password.startsWith("$2a$10$A"));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findPasswordByNotExistingUsername() {
        userDetailsDao.findPasswordByUsername("username");
    }
}
