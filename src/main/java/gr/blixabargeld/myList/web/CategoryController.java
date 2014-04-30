package gr.blixabargeld.myList.web;

import gr.blixabargeld.myList.model.Category;
import gr.blixabargeld.myList.service.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;

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
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

	@Inject private CategoryService categoryService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		binder.setAllowedFields(new String[] { "id", "title", "parent" } );
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		
		populateForm(model, new Category());
		return "category/form";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		
		populateForm(model, categoryService.getCategory(id));
		return "category/form";
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
			return "category/form";
		}
		else {
			
			redirectAttributes.addFlashAttribute("successMessage", "message.category.create.success");
			return "redirect:/category/list";
		}
	}
	
	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						Model model) {
		
		model.addAttribute("categoryList", categoryService.getCategories(property, order));
		return "category/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		
		if(categoryService.deleteCategory(id)) {
		
			redirectAttributes.addFlashAttribute("successMessage", "message.category.delete.success");
		}
		else {
			
			redirectAttributes.addFlashAttribute("errorMessage", "message.category.delete.error");
		}
		
		return "redirect:/category/list";
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
