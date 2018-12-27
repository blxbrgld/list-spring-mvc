package gr.blxbrgld.mylist.web;

import javax.validation.Valid;

import gr.blxbrgld.mylist.model.Activity;
import gr.blxbrgld.mylist.service.ActivityService;

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
 * Activity's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;

    private static final String ACTIVITY_FORM = "activity/form";
    private static final String ACTIVITY_LIST = "activity/list";
    private static final String ACTIVITY_LIST_WITH_REDIRECT = "redirect:/admin/activity/list";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "title" });
	}
		
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Activity());
		return ACTIVITY_FORM;
	}
		
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, activityService.getActivity(id));
		return ACTIVITY_FORM;
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
			return ACTIVITY_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.activity.create.success");
			return ACTIVITY_LIST_WITH_REDIRECT;
		}
	}
	
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
		model.addAttribute("activityList", activityService.getActivities(property, order));
		return ACTIVITY_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(activityService.deleteActivity(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.activity.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.activity.delete.error");
		}
		return ACTIVITY_LIST_WITH_REDIRECT;
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
