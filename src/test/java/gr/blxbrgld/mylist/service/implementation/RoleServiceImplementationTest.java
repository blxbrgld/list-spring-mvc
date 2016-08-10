package gr.blxbrgld.mylist.service.implementation;

import gr.blxbrgld.mylist.dao.RoleDao;
import gr.blxbrgld.mylist.dao.UserDao;
import gr.blxbrgld.mylist.model.Role;
import org.junit.Assert;
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
public class RoleServiceImplementationTest {

    @Mock private RoleDao roleDao;
    @Mock private UserDao userDao;

    @InjectMocks
    private RoleServiceImplementation roleService;

    private Role role; //The Role To Execute Actions Upon
    private Role existingRole; //An Existing Role

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        role = new Role();
        role.setId(2L);
        role.setTitle("Role");

        existingRole = new Role();
        existingRole.setId(1L);
        existingRole.setTitle("Role");
    }

    @Test
    public void persistDuplicateTitle() {
        Mockito.when(roleDao.findByTitle(Mockito.anyString())).thenReturn(existingRole);
        Errors errors = new BeanPropertyBindingResult(role, "role");
        roleService.persistRole(role, errors);
        Mockito.verify(roleDao, Mockito.never()).persist(role); //persist() Method Not Called
    }

    @Test
    public void persist() {
        Mockito.when(roleDao.findByTitle(Mockito.anyString())).thenReturn(null);
        Errors errors = new BeanPropertyBindingResult(role, "role");
        roleService.persistRole(role, errors);
        Mockito.verify(roleDao, Mockito.times(1)).persist(role); //persist() Method Called
    }

    @Test
    public void mergeDuplicateTitle() {
        Mockito.when(roleDao.findByTitle(Mockito.anyString())).thenReturn(existingRole);
        Errors errors = new BeanPropertyBindingResult(role, "role");
        roleService.mergeRole(role, errors);
        Mockito.verify(roleDao, Mockito.never()).merge(role); //merge() Method Not Called
    }

    @Test
    public void merge() {
        existingRole.setId(2L); //Fake The Update Of The Same Object Scenario
        Mockito.when(roleDao.findByTitle(Mockito.anyString())).thenReturn(existingRole);
        Errors errors = new BeanPropertyBindingResult(role, "role");
        roleService.mergeRole(role, errors);
        Mockito.verify(roleDao, Mockito.times(1)).merge(role); //merge() Method Called
    }

    @Test
    public void deleteRoleAssignedToUsers() {
        Mockito.when(userDao.havingRoleExists(Mockito.any(Role.class))).thenReturn(true);
        roleService.deleteRole(Mockito.anyLong());
        Mockito.verify(roleDao, Mockito.never()).deleteById(Mockito.anyLong()); //deleteById() Method Not Called
    }

    @Test
    public void delete() {
        Mockito.when(userDao.havingRoleExists(Mockito.any(Role.class))).thenReturn(false);
        boolean result = roleService.deleteRole(Mockito.anyLong());
        Assert.assertTrue(result);
        Mockito.verify(roleDao, Mockito.times(1)).deleteById(Mockito.anyLong()); //deleteById() Method Called
    }
}
