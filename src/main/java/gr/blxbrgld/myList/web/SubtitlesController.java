package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Subtitles;
import gr.blxbrgld.mylist.service.SubtitlesService;

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
 * Subtitles' Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/subtitles")
public class SubtitlesController {

	@Autowired
	private SubtitlesService subtitlesService;

    private static final String SUBTITLES_FORM = "subtitles/form";
    private static final String SUBTITLES_LIST = "subtitles/list";
    private static final String SUBTITLES_LIST_WITH_REDIRECT = "redirect:/admin/subtitles/list";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "title" });
	}
		
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Subtitles());
		return SUBTITLES_FORM;
	}
		
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, subtitlesService.getSubtitles(id));
		return SUBTITLES_FORM;
	}

	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("subtitles") @Valid Subtitles subtitles, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(subtitles.getId()==null) {
			subtitlesService.persistSubtitles(subtitles, result);
		}
		else {
			subtitlesService.mergeSubtitles(subtitles, result);
		}
		if(result.hasErrors()) {
			populateForm(model, subtitles);
			return SUBTITLES_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.subtitles.create.success");
			return SUBTITLES_LIST_WITH_REDIRECT;
		}
	}
	
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
		model.addAttribute("subtitlesList", subtitlesService.getSubtitles(property, order));
		return SUBTITLES_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(subtitlesService.deleteSubtitles(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.subtitles.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.subtitles.delete.error");
		}
		return SUBTITLES_LIST_WITH_REDIRECT;
	}
	
	/**
	 * Populate Subtitles Form
	 * @param model Model
	 * @param subtitles Subtitles Object
	 */
	void populateForm(Model model, Subtitles subtitles) {
		model.addAttribute("subtitles", subtitles);
	}
}
