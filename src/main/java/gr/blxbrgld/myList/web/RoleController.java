package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Role;
import gr.blxbrgld.mylist.service.RoleService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Role's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

    private static final String ROLE_FORM = "role/form";
    private static final String ROLE_LIST = "role/list";
    private static final String ROLE_LIST_WITH_REDIRECT = "redirect:/admin/role/list";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title");
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Role());
		return ROLE_FORM;
	}	
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, roleService.getRole(id));
		return ROLE_FORM;
	}
	
	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("role") @Valid Role role, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(role.getId()==null) {
			roleService.persistRole(role, result);		
		}
		else {
			roleService.mergeRole(role, result);			
		}
		
		if(result.hasErrors()) {
			populateForm(model, role);
			return ROLE_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.role.create.success");
			return ROLE_LIST_WITH_REDIRECT;
		}
	}

	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
		model.addAttribute("roleList", roleService.getRoles(property, order));
		return ROLE_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(roleService.deleteRole(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.role.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.role.delete.error");
		}
		return ROLE_LIST_WITH_REDIRECT;
	}
	
	/**
	 * Populate Role Form
	 * @param model Model
	 * @param role Role Object
	 */
	void populateForm(Model model, Role role) {
		model.addAttribute("role", role);
	}
}
