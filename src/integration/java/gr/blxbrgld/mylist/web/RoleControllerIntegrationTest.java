package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.dao.hibernate.RoleDao;
import gr.blxbrgld.mylist.model.Role;
import gr.blxbrgld.mylist.service.RoleService;
import org.hamcrest.Matchers;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.validator.HibernateValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import java.util.List;

/**
 * @author blxbrgld
 */
public class RoleControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private RoleController roleController;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SessionFactory sessionFactory;

    private SessionFactory badSessionFactory;

    private Model model;
    private LocalValidatorFactoryBean localValidatorFactory;

    private static final String EXPECTED_ROLE_FORM = "role/form";
    private static final String EXPECTED_ROLE_LIST = "role/list";
    private static final String EXPECTED_ROLE_LIST_WITH_REDIRECT = "redirect:/admin/role/list";

    @Before
    public void setUp() {
        this.model = new ExtendedModelMap();

        //Initialize LocalValidatorFactory
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();

        this.badSessionFactory = Mockito.mock(SessionFactory.class);
        Mockito.when(badSessionFactory.getCurrentSession()).thenThrow(new HibernateError("Hibernate Exception"));
    }

    @After
    public void tearDown() {
        this.model = null;
        this.localValidatorFactory = null;
        this.badSessionFactory = null;
    }

    @Test
    public void testList() {
        String viewName = roleController.list("title", "ASC", model); //Order By Title
        Assert.assertEquals(EXPECTED_ROLE_LIST, viewName);
        @SuppressWarnings("unchecked")
        List<Role> roleList = (List<Role>) model.asMap().get("roleList");
        Assert.assertEquals(2, roleList.size()); //Administrator + Viewer
        Assert.assertEquals("Administrator", roleList.get(0).getTitle());
    }

    @Test
    public void testCreateForm() {
        String viewName = roleController.createForm(model);
        Assert.assertEquals(EXPECTED_ROLE_FORM, viewName);
        Role role = (Role) model.asMap().get("role");
        Assert.assertNull(role.getId()); //Not Persisted Yet
    }

    @Test
        public void testUpdateForm() {
        String viewName = roleController.updateForm(1L, model); //The Only Existing Role Is The Administrator
        Assert.assertEquals(EXPECTED_ROLE_FORM, viewName);
        Role role = (Role) model.asMap().get("role");
        Assert.assertEquals("Administrator", role.getTitle());
    }

    @Test
    public void testPersistRole() {
        Role role = new Role();
        role.setTitle("Testing");

        BindingResult result = new BeanPropertyBindingResult(role, "role");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String viewName = roleController.postForm(role, result, model, redirectAttributes);
        Assert.assertEquals(EXPECTED_ROLE_LIST_WITH_REDIRECT, viewName);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<Role> roleList = roleService.getRoles(null, null);
        assertThat(roleList, hasItem(Matchers.<Role>hasProperty("title", equalTo("Testing")))); //Role Persisted
    }

    @Test
    public void testMergeRole() {
        Role existing = roleService.getRole(2L); //Viewer Role
        existing.setTitle("Edited");

        BindingResult result = new BeanPropertyBindingResult(existing, "role");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String viewName = roleController.postForm(existing, result, model, redirectAttributes);
        Assert.assertEquals(EXPECTED_ROLE_LIST_WITH_REDIRECT, viewName);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        Role role = roleService.getRole(2L);
        Assert.assertEquals("Edited", role.getTitle());
    }

    @Test
    public void testValidationErrors() {
        Role role = new Role();
        role.setTitle(""); //min = 3, max = 15
        BindingResult result = new BeanPropertyBindingResult(role, "role");
        localValidatorFactory.validate(role, result);
        Assert.assertTrue(result.hasErrors());
    }

    @Test
    public void testDeleteUnassigned() {
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String viewName = roleController.delete(2L, redirectAttributes); //Role Not Assigned To A User
        Assert.assertEquals(EXPECTED_ROLE_LIST_WITH_REDIRECT, viewName);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<Role> roleList = roleService.getRoles(null, null);
        assertThat(roleList, not(hasItem(Matchers.<Role>hasProperty("title", equalTo("Viewer"))))); //Deleted
    }

    @Test
    public void testDeleteAssigned() {
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        roleController.delete(1L, redirectAttributes); //Role Is Assigned To A User

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<Role> roleList = roleService.getRoles(null, null);
        assertThat(roleList, hasItem(Matchers.<Role>hasProperty("title", equalTo("Administrator"))));
    }

    @Test(expected = HibernateException.class)
    @DirtiesContext //Reload The Context Before Running The Next Test
    public void testListWithBadSessionFactory() {
        ReflectionTestUtils.setField(roleDao, "sessionFactory", badSessionFactory);
        roleController.list(null, null, model);
    }
}
