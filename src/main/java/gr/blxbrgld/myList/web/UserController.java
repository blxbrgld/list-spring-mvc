package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.User;
import gr.blxbrgld.mylist.service.RoleService;
import gr.blxbrgld.mylist.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired private UserService userService;
	@Autowired private RoleService roleService;

    private static final String USER_FORM = "user/form";
    private static final String USER_LIST = "user/list";
    private static final String USER_LIST_WITH_REDIRECT = "redirect:/admin/user/list";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "username", "password", "confirmPassword", "email", "role" });
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new UserForm());
		return USER_FORM;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, toUserForm(userService.getUser(id)));
		return USER_FORM;
	}
		
	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("user") @Valid UserForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(form.getId()==null) {
			convertPasswordError(result);
			userService.persistUser(toUser(form), form.getPassword(), result);		
		}
		else {
			convertPasswordError(result);
			userService.mergeUser(toUser(form), form.getPassword(), result);			
		}
		if(result.hasErrors()) {
			populateForm(model, form);
			return USER_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.user.create.success");
			return USER_LIST_WITH_REDIRECT;
		}
	}
	
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
		model.addAttribute("userList", userService.getUsers(property, order));
		return USER_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("successMessage", "message.user.delete.success");
		return USER_LIST_WITH_REDIRECT;
	}
	
	/**
	 * Converts the Global Error that @ScriptAssert Generates into an Error on the Password Field
	 * @param result BindingResult Errors Of User Form
	 */
	private static void convertPasswordError(BindingResult result) {
		for(ObjectError error : result.getGlobalErrors()) {
			String message = error.getDefaultMessage();
			if("user.password.mismatch.message".equals(message) && !result.hasFieldErrors("password")) {
				result.rejectValue("password", "error.mismatch");
			}
		}
	}
	
	/**
	 * Convert UserForm Given To A User Object
	 * @param form UserForm Object
	 * @return User Object
	 */
	private static User toUser(UserForm form) {
		User user = new User();
		user.setId(form.getId());
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setEnabled(true); //Enabled By Default
		user.setRole(form.getRole());
		return user;
	}

	/**
	 * Convert User To A UserForm Object. Don't Set password or confirmPassword Fields
	 * @param user User Object
	 * @return UserForm Object
	 */
	private static UserForm toUserForm(User user) {
		UserForm form = new UserForm();
		form.setId(user.getId());
		form.setUsername(user.getUsername());
		form.setEmail(user.getEmail());
		form.setRole(user.getRole());
		return form;
	}
	
	/**
	 * Populate User Form
	 * @param model Model
	 * @param userForm UserForm Object
	 */
	void populateForm(Model model, UserForm userForm) {
		model.addAttribute("user", userForm);
		model.addAttribute("selectRole", roleService.getRoles(null, null));
	}
}