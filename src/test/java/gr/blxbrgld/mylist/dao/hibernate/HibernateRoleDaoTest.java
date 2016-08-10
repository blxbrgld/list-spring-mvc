package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.dao.RoleDao;
import gr.blxbrgld.mylist.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author blxbrgld
 */
public class HibernateRoleDaoTest extends DaoTestBase {

    @Autowired private RoleDao roleDao;

    @Test
    public void findByTitle() {
        Role role = roleDao.findByTitle("Administrator");
        Assert.assertNotNull(role);
        role = roleDao.findByTitle("administrator");
        Assert.assertNull(role); //Query Is Case Sensitive, No Results
    }
}
