package gr.blxbrgld.myList.web;

import gr.blxbrgld.myList.model.Artist;
import gr.blxbrgld.myList.service.ArtistService;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
 * Artist's Controller
 */
@Controller
@RequestMapping("/admin/artist")
public class ArtistController {

	@Inject private ArtistService artistService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "id", "title", "description" });
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true)); //Convert Empty String Values To NULL
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Artist());
		return "artist/form";
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, artistService.getArtist(id));
		return "artist/form";
	}
	
	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("artist") @Valid Artist artist, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(artist.getId() == null) {
			artistService.persistArtist(artist, result);
		}
		else {
			artistService.mergeArtist(artist, result);
		}
		
		if(result.hasErrors()) {
			populateForm(model, artist);
			return "artist/form";
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.artist.create.success");
			return "redirect:/admin/artist/list";
		}
	}
	
	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						@RequestParam(value = "page", required = false) Integer page, 
						@RequestParam(value = "size", required = false) Integer size,
						Model model) {
		int sizeNo = size == null ? 50 : size.intValue();
		int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		model.addAttribute("artistList", artistService.getArtists(property, order, firstResult, sizeNo));
		float noOfPages = (float) artistService.countArtists() / sizeNo;
		model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || noOfPages == 0.0) ? noOfPages + 1 : noOfPages));
		return "artist/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(artistService.deleteArtist(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.artist.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.artist.delete.error");
		}
		return "redirect:/admin/artist/list";
	}
	
	/**
	 * Populate Artist Form
	 * @param model Model
	 * @param artist Artist Object
	 */
	void populateForm(Model model, Artist artist) {
		model.addAttribute("artist", artist);
	}
}
