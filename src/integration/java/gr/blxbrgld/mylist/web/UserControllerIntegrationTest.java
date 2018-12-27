package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Role;
import gr.blxbrgld.mylist.model.User;
import gr.blxbrgld.mylist.service.RoleService;
import gr.blxbrgld.mylist.service.UserService;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.hibernate.validator.HibernateValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

/**
 * @author blxbrgld
 */
public class UserControllerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SessionFactory sessionFactory;

    private Model model;
    private LocalValidatorFactoryBean localValidatorFactory;

    private static final String EXPECTED_USER_FORM = "user/form";
    private static final String EXPECTED_USER_LIST = "user/list";
    private static final String EXPECTED_USER_LIST_WITH_REDIRECT = "redirect:/admin/user/list";

    @Before
    public void setUp() {
        this.model = new ExtendedModelMap();
        //Initialize LocalValidatorFactory
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
    }

    @After
    public void tearDown() {
        this.model = null;
        this.localValidatorFactory = null;
    }

    @Test
    public void testList() {
        String viewName = userController.list(null, null, model);
        Assert.assertEquals(EXPECTED_USER_LIST, viewName);
        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) model.asMap().get("userList");
        Assert.assertEquals(1, userList.size()); //test User
        Assert.assertEquals("blixabargeld", userList.get(0).getUsername());
    }

    @Test
    public void testCreateForm() {
        String viewName = userController.createForm(model);
        Assert.assertEquals(EXPECTED_USER_FORM, viewName);
        UserForm userForm = (UserForm) model.asMap().get("user");
        Assert.assertNull(userForm.getId()); //Not Persisted Yet
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) model.asMap().get("selectRole");
        Assert.assertTrue(roles.size()==2); //Roles List Should Be Added To The Model
    }

    @Test
    public void testUpdateForm() {
        String viewName = userController.updateForm(1L, model); //The Only Existing Role Is The test User
        Assert.assertEquals(EXPECTED_USER_FORM, viewName);
        UserForm userForm = (UserForm) model.asMap().get("user");
        Assert.assertEquals("blixabargeld", userForm.getUsername());
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) model.asMap().get("selectRole");
        Assert.assertTrue(roles.size()==2); //Roles List Should Be Added To The Model
    }

    @Test
    public void testPersistUser() {
        UserForm userForm = getUserForm();
        BindingResult result = new BeanPropertyBindingResult(userForm, "user");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String viewName = userController.postForm(userForm, result, model, redirectAttributes);
        Assert.assertEquals(EXPECTED_USER_LIST_WITH_REDIRECT, viewName);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<User> userList = userService.getUsers(null, null);
        assertThat(userList, hasItem(Matchers.<User>hasProperty("username", equalTo("username")))); //User Persisted
    }

    @Test
    public void testMergeUser() {
        UserForm existing = getUserForm();
        existing.setId(1L); //Fake The Update Scenario
        existing.setUsername("Edited");

        BindingResult result = new BeanPropertyBindingResult(existing, "user");
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String viewName = userController.postForm(existing, result, model, redirectAttributes);
        Assert.assertEquals(EXPECTED_USER_LIST_WITH_REDIRECT, viewName);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        User user = userService.getUser(1L);
        Assert.assertEquals("Edited", user.getUsername());
    }

    @Test
    public void testInvalidUsername() {
        UserForm userForm = getUserForm(); //Valid Object
        userForm.setUsername(""); //Now It's Invalid
        BindingResult result = new BeanPropertyBindingResult(userForm, "user");
        localValidatorFactory.validate(userForm, result);
        Assert.assertTrue(result.hasErrors());
    }

    @Test
    public void testInvalidPassword() {
        UserForm userForm = getUserForm(); //Valid Object
        userForm.setPassword(null); //Now It's Invalid
        BindingResult result = new BeanPropertyBindingResult(userForm, "user");
        localValidatorFactory.validate(userForm, result);
        Assert.assertTrue(result.hasErrors());
    }

    @Test
    public void testInvalidPasswords() {
        UserForm userForm = getUserForm(); //Valid Object
        userForm.setConfirmPassword("not matching"); //Now It's Invalid
        BindingResult result = new BeanPropertyBindingResult(userForm, "user");
        localValidatorFactory.validate(userForm, result);
        Assert.assertTrue(result.hasErrors());
    }

    @Test
    public void testInvalidEmail() {
        UserForm userForm = getUserForm(); //Valid Object
        userForm.setEmail(userForm.getEmail().replaceAll("@", "")); //Now It's Invalid
        BindingResult result = new BeanPropertyBindingResult(userForm, "user");
        localValidatorFactory.validate(userForm, result);
        Assert.assertTrue(result.hasErrors());
    }

    @Test
    public void testDeleteUser() {
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        String viewName = userController.delete(1L, redirectAttributes);
        Assert.assertEquals(EXPECTED_USER_LIST_WITH_REDIRECT, viewName);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        List<User> userList = userService.getUsers(null, null);
        Assert.assertTrue(userList.size() == 0);
    }

    /**
     * Return A Valid UserForm Object (Which We'll Make Invalid To Test Our Controller)
     * @return UserForm Object
     */
    private UserForm getUserForm() {
        UserForm userForm = new UserForm();
        userForm.setUsername("username");
        userForm.setPassword("password");
        userForm.setConfirmPassword("password");
        userForm.setEmail("username@email.com");
        userForm.setRole(roleService.getRole(1L));
        return userForm;
    }
}
