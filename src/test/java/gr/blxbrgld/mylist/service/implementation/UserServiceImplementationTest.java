package gr.blxbrgld.mylist.service.implementation;

import gr.blxbrgld.mylist.dao.hibernate.UserDao;
import gr.blxbrgld.mylist.model.Role;
import gr.blxbrgld.mylist.model.User;
import gr.blxbrgld.mylist.service.UserServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * @author blxbrgld
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplementationTest {

    @Mock private UserDao userDao;

    @InjectMocks
    private UserServiceImplementation userService;

    private User user; //The User To Execute Actions Upon
    private User existingUser; //An Existing User

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(2L);
        user.setRole(new Role());
        user.setEnabled(true);

        existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("Username");
        existingUser.setEmail("email@email.com");
        existingUser.setRole(new Role());
        existingUser.setEnabled(true);
    }

    @Test
    public void persist() {
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(null);
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userService.persistUser(user, "password", errors);
        Mockito.verify(userDao, Mockito.times(1)).persist(user, "password"); //persist() Method Called
    }

    @Test
    public void persistDuplicateUsername() {
        user.setUsername("Username"); //The Duplicate Username Scenario
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(existingUser);
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(null);
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userService.persistUser(user, "password", errors);
        Mockito.verify(userDao, Mockito.never()).persist(user, "password"); //persist() Method Not Called
    }

    @Test
    public void merge() {
        existingUser.setId(2L); //Fake The Update Of The Same Object Scenario
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(existingUser);
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(existingUser);
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userService.mergeUser(user, "password", errors);
        Mockito.verify(userDao, Mockito.times(1)).merge(user, "password"); //merge() Method Called
    }

    @Test
    public void mergeDuplicateEmail() {
        user.setEmail("email@email.com"); //The Duplicate Email Scenario
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(existingUser);
        Errors errors = new BeanPropertyBindingResult(user, "user");
        userService.mergeUser(user, "password", errors);
        Mockito.verify(userDao, Mockito.never()).merge(user, "password"); //merge() Method Not Called
    }
}
