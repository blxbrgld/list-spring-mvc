package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.dao.RoleDao;
import gr.blxbrgld.mylist.dao.UserDao;
import gr.blxbrgld.mylist.model.Role;
import gr.blxbrgld.mylist.model.User;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author blxbrgld
 */
public class HibernateUserDaoTest extends DaoTestBase {

    @Autowired private UserDao userDao;
    @Autowired private RoleDao roleDao;
    @Autowired private SessionFactory sessionFactory;

    @Test
    public void getAll() {
        List<User> userList = userDao.getAll(null, null); //There's Only One User, We Can Not Test Ordering Functionality
        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void persist() {
        Role role = roleDao.findByTitle("Viewer");
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setRole(role);
        user.setEnabled(true);
        userDao.persist(user, "password");

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<User> userList = userDao.findByRole(role);
        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void merge() {
        Role role = roleDao.findByTitle("Viewer");
        User user = userDao.findByUsername("blixabargeld");
        user.setRole(role); //Change User's Role
        userDao.merge(user, "password");

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<User> userList = userDao.findByRole(role);
        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void findByUsername() {
        User user = userDao.findByUsername("blixabargeld");
        Assert.assertNotNull(user);
        Assert.assertEquals("nikolaos.i.papadopoulos@gmail.com", user.getEmail());
    }

    @Test
    public void findByEmail() {
        User user = userDao.findByEmail("nikolaos.i.papadopoulos@gmail.com");
        Assert.assertNotNull(user);
        Assert.assertEquals("blixabargeld", user.getUsername());
    }

    @Test
    public void findByRole() {
        Role role = roleDao.findByTitle("Administrator");
        List<User> userList = userDao.findByRole(role);
        Assert.assertEquals(1, userList.size());
    }

    @Test
    public void havingRoleExists() {
        Role role = roleDao.findByTitle("Administrator");
        Assert.assertTrue(userDao.havingRoleExists(role)); //We Have One Administrator
        role = roleDao.findByTitle("Viewer");
        Assert.assertFalse(userDao.havingRoleExists(role)); //No Viewers Exist
    }
}
