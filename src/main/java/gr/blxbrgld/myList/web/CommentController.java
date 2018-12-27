package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Comment;
import gr.blxbrgld.mylist.service.CommentService;

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
 * Comment's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

    private static final String COMMENT_FORM = "comment/form";
    private static final String COMMENT_LIST = "comment/list";
    private static final String COMMENT_LIST_WITH_REDIRECT = "redirect:/admin/comment/list";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "title" });
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Comment());
		return COMMENT_FORM;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, commentService.getComment(id));
		return COMMENT_FORM;
	}
	
	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(comment.getId() == null) {
			commentService.persistComment(comment, result);
		}
		else {
			commentService.mergeComment(comment, result);
		}
		if(result.hasErrors()) {
			populateForm(model, comment);
			return COMMENT_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.comment.create.success");
			return COMMENT_LIST_WITH_REDIRECT;
		}
	}
	
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "property", required = false) String property, @RequestParam(value = "order", required = false) String order, Model model) {
		model.addAttribute("commentList", commentService.getComments(property, order));
		return COMMENT_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(commentService.deleteComment(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.comment.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.comment.delete.error");
		}
		return COMMENT_LIST_WITH_REDIRECT;
	}
	
	/**
	 * Populate Comment Form
	 * @param model Model
	 * @param comment Comment Object
	 */
	void populateForm(Model model, Comment comment) {
		model.addAttribute("comment", comment);
	}
}
