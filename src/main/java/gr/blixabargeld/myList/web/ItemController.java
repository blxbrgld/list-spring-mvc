package gr.blixabargeld.myList.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gr.blixabargeld.myList.model.Activity;
import gr.blixabargeld.myList.model.Artist;
import gr.blixabargeld.myList.model.ArtistActivityItem;
import gr.blixabargeld.myList.model.CommentItem;
import gr.blixabargeld.myList.model.Item;
import gr.blixabargeld.myList.service.ActivityService;
import gr.blixabargeld.myList.service.ArtistActivityItemService;
import gr.blixabargeld.myList.service.ArtistService;
import gr.blixabargeld.myList.service.CategoryService;
import gr.blixabargeld.myList.service.CommentItemService;
import gr.blixabargeld.myList.service.CommentService;
import gr.blixabargeld.myList.service.ItemService;
import gr.blixabargeld.myList.service.SubtitlesService;
import gr.blixabargeld.myList.utilities.ReturningValues;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.coobird.thumbnailator.Thumbnails;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Item's Controller
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Inject private ItemService itemService;
	@Inject private CategoryService categoryService;
	@Inject private CommentService commentService;
	@Inject private CommentItemService commentItemService;
	@Inject private ArtistService artistService;
	@Inject private ActivityService activityService;
	@Inject private ArtistActivityItemService artistActivityItemService;
	@Inject private SubtitlesService subtitlesService;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		binder.setAllowedFields(new String[] { "id", "titleEng", "titleEll", "category", "description", "year", "rating", "subtitles", "discs", "place", "photoPath", "photo", "commentItems" });
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true)); //Convert Empty String Values To NULL
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		
		populateForm(model, new Item());
		return "item/form";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		
		populateForm(model, itemService.getItem(id));
		return "item/form";
	}

	@RequestMapping(value = "*", method = RequestMethod.POST)
	public String postForm( @RequestParam("comments") String[] comments, 
							@RequestParam("artists") String[] artists,
							@RequestParam("activities") String[] activities,
							@ModelAttribute("item") @Valid Item item,
							BindingResult result,
							Model model,
							RedirectAttributes redirectAttributes) {
		
		if(item.getId()==null) { 
			
			itemService.persistItem(populateItemRelations(item, comments, artists, activities, false), result); 
			
			if(!result.hasErrors()) {
				uploadPhoto(item);
				itemService.mergeItem(populateItemRelations(item, comments, artists, activities, true), result);
			}
		}
		else {
			
			itemService.mergeItem(populateItemRelations(item, comments, artists, activities, false), result);
			
			if(!result.hasErrors()) {
				
				uploadPhoto(item);
				commentItemService.deleteCommentItem(item);
				artistActivityItemService.deleteArtistActivityItem(item);
				itemService.mergeItem(populateItemRelations(item, comments, artists, activities, true), result);
			}
		}

		if(result.hasErrors()) {
			
			populateForm(model, populateItemRelations(item, comments, artists, activities, false));
			return "item/form";
		}
		else {
		
			redirectAttributes.addFlashAttribute("successMessage", "message.item.create.success");
			return "redirect:/item/update/" + item.getId();
		}
	}

	@RequestMapping(value = "list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						@RequestParam(value = "page", required = false) Integer page,
						@RequestParam(value = "size", required = false) Integer size,
						@RequestParam(value = "view", required = false) String view,
						@RequestParam(value = "artist", required = false) Long artist,
						@RequestParam(value = "searchFor", required = false) String searchFor,
						@RequestParam(value = "searchIn", required = false) String searchIn,
						Model model) {
		
		int sizeNo = size == null ? 50 : size.intValue();
		int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		String orderBy = property == null ? "titleEng" : property;
		String ordering = order == null ? "ASC" : order;
		
		if(artist != null) {
		
			Artist art = new Artist();
			
			if(artist.equals(-1L)) {
				
				art = artistService.findRandomArtist();
				model.addAttribute("paginateRandom", art.getId()); //Paginate Random Artist If It's Needed
			}
			else {
				
				art = artistService.getArtist(artist);
			}

			model.addAttribute("itemList", itemService.getItemsHavingArtist(art, firstResult, sizeNo)); //Ascending titleEng Ordering Is The Default
			float noOfPages = (float) artistActivityItemService.countArtistActivityItemsHavingArtist(art) / sizeNo;
			model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || noOfPages == 0.0) ? noOfPages + 1 : noOfPages));
			
			model.addAttribute("pageHeader", "title.item.artists");
			model.addAttribute("pageHeaderAttribute", art.getTitle());
		}
		else if(searchFor != null) {

			ReturningValues result = itemService.searchItems(searchFor, searchIn, property, order, firstResult, sizeNo); //Order By Relevance Is The Default
			
			model.addAttribute("itemList", result.getResults());
			float noOfPages = (float) result.getNoOfResults() / sizeNo;
			model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || noOfPages == 0.0) ? noOfPages + 1 : noOfPages));
			
			if(searchFor.equals("*") && searchIn != null) {
				
				model.addAttribute("pageHeader", "title.item.categories");
				model.addAttribute("pageHeaderAttribute", searchIn);			
			}
			else if(searchIn != null && searchIn.equals("Lists")) {
				
				model.addAttribute("pageHeader", "title.item.favorites");
				model.addAttribute("pageHeaderAttribute", null);
			}
			else {
			
				model.addAttribute("pageHeader", "title.item.search");
				model.addAttribute("pageHeaderAttribute", null);
			}
		}
		else {

			model.addAttribute("itemList", itemService.getItems(orderBy, ordering, firstResult, sizeNo));
			float noOfPages = (float) itemService.countItems() / sizeNo;
			model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || noOfPages == 0.0) ? noOfPages + 1 : noOfPages));
			
			model.addAttribute("pageHeader", "title.item.list");
			model.addAttribute("pageHeaderAttribute", null);
		}

		if(view != null && view.equals("list")) { //List View
			
			return "item/list";
		}
		else {
			
			return "item/display";
		}
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		
		Item item = itemService.getItem(id);
		deletePhoto(item.getPhotoPath());
		itemService.deleteItem(item);
		redirectAttributes.addFlashAttribute("successMessage", "message.item.delete.success");
		return "redirect:/item/list?view=list";
	}

	@RequestMapping(value = {"/get_artists", "/update/get_artists"}, method = RequestMethod.GET)
	public @ResponseBody List<String> getArtistList(@RequestParam("term") String term) {
		
		return artistService.findArtistsLike(term);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ModelAndView getExcel(@RequestParam("parent") String parent) {
	
		return new ModelAndView("ItemExcel", "itemList", itemService.getItemsHavingCategory(categoryService.getCategory(parent)));
	}
	
	/**
	 * Display Item's Photo Stored In Filesystem
	 * @param id Item's Id
	 * @param response HttpServletResponse
	 * @param model Model
	 * @return null
	 */
	@RequestMapping(value = { "/update/{id}/photo" }, method = RequestMethod.GET)
	public String displayPhoto(@PathVariable("id") Long id, HttpServletResponse response, Model model) {
		
		Item current = itemService.getItem(id);
		
		if(current != null) {
			
			try {
				File currentPhoto = new File(System.getProperty("filepath.images"), "items/" + current.getPhotoPath());
				OutputStream output = response.getOutputStream();
				Files.copy(FileSystems.getDefault().getPath(System.getProperty("filepath.images") + "items/" + (currentPhoto.exists() ? current.getPhotoPath() : "no-image.png")), output);
				output.flush();
			} 
			catch (IOException exception) {
				
				exception.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Upload Photo, Create Thumbnail and Set Photo Attribute of Given Item
	 * @param item Item Object
	 */
	void uploadPhoto(Item item) {
	
		CommonsMultipartFile photo = item.getPhoto();
		if(photo != null && photo.getSize() != 0) {
			
			deletePhoto(item.getPhotoPath()); //Delete Old Image
			String filename = "item_" + String.format("%05d", item.getId()) + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf('.'));
        	File file = new File(System.getProperty("filepath.images"), "temporary/" + filename);
        	
        	try {
        	
        		photo.transferTo(file);
        		Thumbnails
        			.of(file)
        			.size(150, 150)
        			.toFile(new File(System.getProperty("filepath.images"), "items/" + filename));
        		item.setPhotoPath(filename); //Set Object's Attribute
        	}
        	catch(IOException exception) {
        		
        		exception.printStackTrace();
        	}
        	finally {
        		
        		file.delete(); //Delete Temporary Image
        	}
		}
	}
	
	/**
	 * Delete Old Photo In Case Of Updating
	 * @param photo Photo's Filename
	 */
	void deletePhoto(String photo) {
		
		if(photo != null) {
			File file = new File(System.getProperty("filepath.images"), "items/" + photo);
			file.delete();
		}
	}	
	
	/**
	 * Populate Item Form
	 * @param model Model
	 * @param item Item Object
	 */
	void populateForm(Model model, Item item) {
		
		model.addAttribute("item", item);
		model.addAttribute("selectCategory", categoryService.getCategories(null, null));
		model.addAttribute("selectComment", commentService.getComments(null, null));
		model.addAttribute("selectActivity", activityService.getActivities(null, null));
		model.addAttribute("selectSubtitles", subtitlesService.getSubtitles(null, null));
		model.addAttribute("selectRating", Arrays.asList(new String[] {"1", "2", "3", "4", "5"}));
	}
	
	/**
	 * Given comments, artists and activities as String[], Populate Item With It's CommentItems, ArtistActivityItems Relations
	 * @param item Item Object Without Relations
	 * @param comments String[] Of Given Comment Ids
	 * @param artists String[] Of Given Artist Titles
	 * @param activities String[] Of Given Activity Ids
	 * @param persistRelations If TRUE Persist CommentItems and ArtistActivityItems Objects
	 * @return Item Object With Relations
	 */
	Item populateItemRelations(Item item, String[] comments, String[] artists, String[] activities, boolean persistRelations) {

		item.setCommentItems(commentItemsSetFromArray(item, comments, persistRelations));
		item.setArtistActivityItems(artistActivityItemsListFromArrays(item, artists, activities, persistRelations));
		
		return item;
	}
	
	/**
	 * Given comments as String[], Populate Item With CommentItems Properly Set
	 * @param item Item Object
	 * @param comments String[] Of Given Comment Ids
	 * @param persistRelations If TRUE Persist CommentItems
	 * @return Item Object With CommentItems
	 */
	Set<CommentItem> commentItemsSetFromArray(Item item, String[] comments, boolean persistRelations) {
		
		Set<CommentItem> commentItems = new HashSet<CommentItem>();
		
		for(String comment : comments) {
			
			try {
				
				CommentItem commentItem = new CommentItem();
				commentItem.setIdItem(item);
				commentItem.setIdComment(commentService.getComment(Long.parseLong(comment)));
				
				if(persistRelations) { //Persist
					
					commentItemService.persistCommentItem(commentItem);
				}
				
				commentItems.add(commentItem);
			}
			catch(NumberFormatException exception) { //Ignore Null Drop-Down Values
			
			}
		}
		return commentItems;
	}

	/**
	 * Given artists and activities as String[], Populate Item With ArtistActivityItems Properly Set
	 * @param item Item Object
	 * @param artists String[] Of Given Artist Titles
	 * @param activities String[] Of Given Activity Ids
	 * @param persistRelations If TRUE Persist New Artists and ArtistActivityItems
	 * @return Item Object With ArtistActivityItems
	 */
	List<ArtistActivityItem> artistActivityItemsListFromArrays(Item item, String[] artists, String[] activities, boolean persistRelations) {
	
		List<ArtistActivityItem> artistActivityItems = new ArrayList<ArtistActivityItem>();
		
		for(int i = 0; i < artists.length; i++) {
		
			if(!artists[i].equals("") || !activities[i].equals("")) { //Artist or Activity Given
			
				ArtistActivityItem artistActivityItem = new ArtistActivityItem();
				artistActivityItem.setIdItem(item);
				
				/*
				 * 1 : Set Artist
				 */
				Artist idArtist = artistService.getArtist(artists[i]);
				
				if(idArtist == null) {
					
					idArtist = new Artist();
					idArtist.setTitle(artists[i]);
				
					if(persistRelations) { //Persist New Artist
						
						artistService.persistArtist(idArtist);
					}
				}
				
				artistActivityItem.setIdArtist(idArtist);
				
				/*
				 * 2 : Set Activity
				 */
				Activity idActivity = new Activity();
				
				try {
					
					idActivity = activityService.getActivity(Long.parseLong(activities[i]));
				}
				catch(NumberFormatException exception) { //Ignore Null Drop-Down Values
				
				}
				
				artistActivityItem.setIdActivity(idActivity);
				
				/*
				 * 3 : Populate ArtistActivityItem Object
				 */
				if(persistRelations) { //Persist
				
					artistActivityItemService.persistArtistActivityItem(artistActivityItem);
				}
				
				artistActivityItems.add(artistActivityItem);
			}
		}
		return artistActivityItems;
	}
}
