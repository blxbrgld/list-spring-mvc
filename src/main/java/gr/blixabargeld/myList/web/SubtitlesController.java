package gr.blixabargeld.myList.web;

import gr.blixabargeld.myList.model.Subtitles;
import gr.blixabargeld.myList.service.SubtitlesService;

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
 * Subtitles' Controller
 */
@Controller
@RequestMapping("/subtitles")
public class SubtitlesController {

	@Inject private SubtitlesService subtitlesService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		binder.setAllowedFields(new String[] { "id", "title" });
	}
		
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		
		populateForm(model, new Subtitles());
		return "subtitles/form";
	}
		
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		
		populateForm(model, subtitlesService.getSubtitles(id));
		return "subtitles/form";
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
			return "subtitles/form";
		}
		else {
			
			redirectAttributes.addFlashAttribute("successMessage", "message.subtitles.create.success");
			return "redirect:/subtitles/list";
		}
	}
	
	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
					  	@RequestParam(value = "order", required = false) String order,
					  	Model model) {
		
		model.addAttribute("subtitlesList", subtitlesService.getSubtitles(property, order));
		return "subtitles/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		
		if(subtitlesService.deleteSubtitles(id)) {
			
			redirectAttributes.addFlashAttribute("successMessage", "message.subtitles.delete.success");
		}
		else {

			redirectAttributes.addFlashAttribute("errorMessage", "message.subtitles.delete.error");
		}
		
		return "redirect:/subtitles/list";
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
