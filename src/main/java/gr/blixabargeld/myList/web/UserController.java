package gr.blixabargeld.myList.web;

import gr.blixabargeld.myList.model.User;
import gr.blixabargeld.myList.service.RoleService;
import gr.blixabargeld.myList.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;

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
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Inject private UserService userService;
	@Inject private RoleService roleService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		binder.setAllowedFields(new String[] { "id", "username", "password", "confirmPassword", "email", "role" });
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		
		populateForm(model, new UserForm());
		return "user/form";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		
		populateForm(model, toUserForm(userService.getUser(id)));
		return "user/form";
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
			return "user/form";
		}
		else {
			
			redirectAttributes.addFlashAttribute("successMessage", "message.user.create.success");
			return "redirect:/user/list";
		}
	}
	
	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						Model model) {
		
		model.addAttribute("userList", userService.getUsers(property, order));
		return "user/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		
		userService.deleteUser(id);
		redirectAttributes.addFlashAttribute("successMessage", "message.user.delete.success");
		return "redirect:/user/list";
	}
	
	/**
	 * Converts the Global Error that @ScriptAssert Generates into an Error on the Password Field
	 * @param result BindingResult Errors Of User Form
	 */
	private static void convertPasswordError(BindingResult result) {
		
		for(ObjectError error : result.getGlobalErrors()) {
			
			String message = error.getDefaultMessage();
			
			if("user.password.mismatch.message".equals(message)) {
				
				if(!result.hasFieldErrors("password")) {
					
					result.rejectValue("password", "error.mismatch");
				}
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