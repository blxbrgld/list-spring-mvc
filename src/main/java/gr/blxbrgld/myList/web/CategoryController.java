package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.service.CategoryService;

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
 * Category's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

    private static final String CATEGORY_FORM = "category/form";
    private static final String CATEGORY_LIST = "category/list";
    private static final String CATEGORY_LIST_WITH_REDIRECT = "redirect:/admin/category/list";

    @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "title", "parent" } );
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Category());
		return CATEGORY_FORM;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, categoryService.getCategory(id));
		return CATEGORY_FORM;
	}
	
	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("category") @Valid Category category, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(category.getId()==null) {
			categoryService.persistCategory(category, result);
		}
		else {
			categoryService.mergeCategory(category, result);
		}
		if(result.hasErrors()) {
			populateForm(model, category);
			return CATEGORY_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.category.create.success");
			return CATEGORY_LIST_WITH_REDIRECT;
		}
	}
	
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
		model.addAttribute("categoryList", categoryService.getCategories(property, order));
		return CATEGORY_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(categoryService.deleteCategory(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.category.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.category.delete.error");
		}
		return CATEGORY_LIST_WITH_REDIRECT;
	}
	
	/**
	 * Populate Category Form
	 * @param model Model
	 * @param category Category Object
	 */
	void populateForm(Model model, Category category) {
		model.addAttribute("category", category);
		model.addAttribute("selectParent", categoryService.getCategories(null, null));
	}
}
