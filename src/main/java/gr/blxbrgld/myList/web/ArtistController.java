package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.service.ArtistService;

import javax.validation.Valid;

import org.apache.commons.math.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/artist")
public class ArtistController {

	@Autowired
	private ArtistService artistService;

    private static final String ARTIST_FORM = "artist/form";
    private static final String ARTIST_LIST = "artist/list";
    private static final String ARTIST_LIST_WITH_REDIRECT = "redirect:/admin/artist/list";

    private static final double EPSILON = 0.001;

    @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "description");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true)); //Convert Empty String Values To NULL
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Artist());
		return ARTIST_FORM;
	}
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, artistService.getArtist(id));
		return ARTIST_FORM;
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
			return ARTIST_FORM;
		}
		else {
			redirectAttributes.addFlashAttribute("successMessage", "message.artist.create.success");
			return ARTIST_LIST_WITH_REDIRECT;
		}
	}
	
	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						@RequestParam(value = "page", required = false) Integer page, 
						@RequestParam(value = "size", required = false) Integer size,
						Model model) {
		int sizeNo = size == null ? 50 : size;
		int firstResult = page == null ? 0 : (page - 1) * sizeNo;
		model.addAttribute("artistList", artistService.getArtists(property, order, firstResult, sizeNo));
		double noOfPages = (double) artistService.countArtists() / sizeNo;
		model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || MathUtils.equals(noOfPages, 0.0, EPSILON)) ? noOfPages + 1 : noOfPages));
        return ARTIST_LIST;
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(artistService.deleteArtist(id)) {
			redirectAttributes.addFlashAttribute("successMessage", "message.artist.delete.success");
		}
		else {
			redirectAttributes.addFlashAttribute("errorMessage", "message.artist.delete.error");
		}
		return ARTIST_LIST_WITH_REDIRECT;
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
