package gr.blxbrgld.myList.web;

import javax.inject.Inject;
import javax.validation.Valid;

import gr.blxbrgld.myList.model.Activity;
import gr.blxbrgld.myList.service.ActivityService;

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
 * Activity's Controller
 */
@Controller
@RequestMapping("/admin/activity")
public class ActivityController {
	
	@Inject private ActivityService activityService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "title" });
	}
		
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Activity());
		return "activity/form";
	}
		
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, activityService.getActivity(id));
		return "activity/form";
	}

	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("activity") @Valid Activity activity, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(activity.getId()==null) {
			activityService.persistActivity(activity, result);
		}
		else {
			activityService.mergeActivity(activity, result);
		}
		if(result.hasErrors()) {
			populateForm(model, activity);
			return "activity/form";
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.activity.create.success");
			return "redirect:/admin/activity/list";
		}
	}
	
	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						Model model) {
		model.addAttribute("activityList", activityService.getActivities(property, order));
		return "activity/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(activityService.deleteActivity(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.activity.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.activity.delete.error");
		}
		return "redirect:/admin/activity/list";
	}
	
	/**
	 * Populate Activity Form
	 * @param model Model
	 * @param activity Activity Object
	 */
	void populateForm(Model model, Activity activity) {
		model.addAttribute("activity", activity);
	}
}
