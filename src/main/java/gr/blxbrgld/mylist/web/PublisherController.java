package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Publisher;
import gr.blxbrgld.mylist.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Publisher's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    private static final String PUBLISHER_FORM = "publisher/form";
    private static final String PUBLISHER_LIST = "publisher/list";
    private static final String PUBLISHER_LIST_WITH_REDIRECT = "redirect:/admin/publisher/list";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(new String[] { "id", "title" });
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        populateForm(model, new Publisher());
        return PUBLISHER_FORM;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        populateForm(model, publisherService.getPublisher(id));
        return PUBLISHER_FORM;
    }

    @RequestMapping(value = "*", method = RequestMethod.POST)
    public String postForm(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if(publisher.getId()==null) {
            publisherService.persistPublisher(publisher, result);
        } else {
            publisherService.mergePublisher(publisher, result);
        }
        if(result.hasErrors()) {
            populateForm(model, publisher);
            return PUBLISHER_FORM;
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "message.publisher.create.success");
            return PUBLISHER_LIST_WITH_REDIRECT;
        }
    }

    @RequestMapping(value = "list")
    public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
        model.addAttribute("publishersList", publisherService.getPublishers(property, order));
        return PUBLISHER_LIST;
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if(publisherService.deletePublisher(id)) {
            redirectAttributes.addFlashAttribute("successMessage", "message.publisher.delete.success");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "message.publisher.delete.error");
        }
        return PUBLISHER_LIST_WITH_REDIRECT;
    }

    /**
     * Populate Publisher Form
     * @param model Model
     * @param publisher Publisher Object
     */
    void populateForm(Model model, Publisher publisher) {
        model.addAttribute("publisher", publisher);
    }
}
