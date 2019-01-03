package gr.blxbrgld.mylist.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import gr.blxbrgld.mylist.model.*;
import gr.blxbrgld.mylist.service.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.math.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
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
 * @author blxbrgld
 */
@Slf4j
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommentItemService commentItemService;

	@Autowired
	private ArtistService artistService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ArtistActivityItemService artistActivityItemService;

	@Autowired
	private SubtitlesService subtitlesService;

	@Autowired
	private PublisherService publisherService;

	@Value("${filepath.images}")
	private String filepathImages;

	private static final String ITEM_FORM = "item/form";
    private static final String ITEM_LIST = "item/list";
    private static final String ITEM_UPDATE = "item/update";
    private static final String ITEM_DISPLAY = "item/display";
    private static final String ITEMS_FILEPATH = "items/";
    private static final double EPSILON = 0.001;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "titleEng", "titleEll", "category", "publisher", "description", "year", "rating", "subtitles", "discs", "place", "pages", "photoPath", "photo", "commentItems");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true)); //Convert Empty String Values To NULL
	}

	@RequestMapping(value = "/admin/item/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		populateForm(model, new Item());
		return ITEM_FORM;
	}

	@RequestMapping(value = "/admin/item/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		populateForm(model, itemService.getItem(id));
		return ITEM_FORM;
	}

	@RequestMapping(value = "/admin/item/*", method = RequestMethod.POST)
	public String postForm( @RequestParam("comments") String[] comments, 
							@RequestParam("artists") String[] artists,
							@RequestParam("activities") String[] activities,
							@ModelAttribute("item") @Valid Item item,
							BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(item.getId()==null) { 
			itemService.persistItem(populateItemRelations(item, comments, artists, activities, false), result);
			if(!result.hasErrors()) {
				uploadPhoto(item);
				itemService.mergeItem(populateItemRelations(item, comments, artists, activities, true), result);
			}
		} else {
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
			return ITEM_FORM;
		} else {
			redirectAttributes.addFlashAttribute("successMessage", "message.item.create.success");
			return "redirect:/admin/" + ITEM_UPDATE + "/" + item.getId();
		}
	}

	@RequestMapping(value = "/item/list")
	public String list( @RequestParam(value = "property", required = false) String property,
						@RequestParam(value = "order", required = false) String order,
						@RequestParam(value = "page", required = false) Integer page,
						@RequestParam(value = "size", required = false) Integer size,
						@RequestParam(value = "view", required = false) String view,
						@RequestParam(value = "artist", required = false) Long artist,
						@RequestParam(value = "searchFor", required = false) String searchFor,
						@RequestParam(value = "searchIn", required = false) String searchIn,
						Model model) {
		int sizeNo = size == null ? 50 : size;
		int firstResult = page == null ? 0 : (page - 1) * sizeNo;
		String orderBy = property == null ? "titleEng" : property;
		String ordering = order == null ? "ASC" : order;
		if(artist != null) {
			Artist art;
			if(artist.equals(-1L)) {
				art = artistService.findRandomArtist();
				model.addAttribute("paginateRandom", art.getId()); //Paginate Random Artist If It's Needed
			} else {
				art = artistService.getArtist(artist);
			}
			model.addAttribute("itemList", itemService.getItemsHavingArtist(art, firstResult, sizeNo)); //Ascending titleEng Ordering Is The Default
			double noOfPages = (double) artistActivityItemService.countArtistActivityItemsHavingArtist(art) / sizeNo;
			model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || MathUtils.equals(noOfPages, 0.0, EPSILON)) ? noOfPages + 1 : noOfPages));
			model.addAttribute("pageHeader", "title.item.artists");
			model.addAttribute("pageHeaderAttribute", art.getTitle());
		} else if(searchFor != null) {
			ImmutablePair<Integer, List<Item>> result = itemService.searchItems(searchFor, searchIn, property, order, firstResult, sizeNo); //Order By Relevance Is The Default
			model.addAttribute("itemList", result.getValue());
			double noOfPages = (double) result.getKey() / sizeNo;
			model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || MathUtils.equals(noOfPages, 0.0, EPSILON)) ? noOfPages + 1 : noOfPages));
			if("*".equals(searchFor) && searchIn != null) {
				model.addAttribute("pageHeader", "title.item.categories");
				model.addAttribute("pageHeaderAttribute", searchIn);			
			} else {
				model.addAttribute("pageHeader", "title.item.search");
				model.addAttribute("pageHeaderAttribute", null);
			}
		} else {
			model.addAttribute("itemList", itemService.getItems(orderBy, ordering, firstResult, sizeNo));
			double noOfPages = (double) itemService.countItems() / sizeNo;
			model.addAttribute("maxPages", (int) ((noOfPages > (int) noOfPages || MathUtils.equals(noOfPages, 0.0, EPSILON)) ? noOfPages + 1 : noOfPages));
			model.addAttribute("pageHeader", "title.item.list");
			model.addAttribute("pageHeaderAttribute", null);
		}

		return view!=null && "list".equals(view) ? ITEM_LIST : ITEM_DISPLAY;
	}

	@RequestMapping(value = "/admin/item/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Item item = itemService.getItem(id);
		deletePhoto(item.getPhotoPath());
		itemService.deleteItem(item);
		redirectAttributes.addFlashAttribute("successMessage", "message.item.delete.success");
		return "redirect:/" + ITEM_LIST + "?view=list";
	}

	/**
	 * Given An Item's Image Name, Set The Actual Image To The Output Stream
	 * @param image The Item's Image Name
	 * @param response HttpServletResponse Object
	 */
	@RequestMapping(value = "/item/image/{image:.*}", method = RequestMethod.GET)
	public void getImage(@PathVariable("image") String image, HttpServletResponse response) {
		try {
			File file = new File(filepathImages + "/items/" + image);
			InputStream inputStream = new FileInputStream(file);
			String extension = StringUtils.substringAfterLast(image, ".");
			if("png".equalsIgnoreCase(extension)) { // png, jpg and jpeg Are The Only Allowed Extensions
				response.setContentType(MediaType.IMAGE_PNG_VALUE);
			} else {
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			}
			IOUtils.copy(inputStream, response.getOutputStream());
		} catch(Exception exception) { // The Default Image Will Be Displayed
			log.error("Exception", exception);
		}
	}

    @ResponseBody
	@RequestMapping(value = "/item/getArtists", method = RequestMethod.GET)
    public List<String> getArtistList(@RequestParam("term") String term) {
		return artistService.findArtistsLike(term);
	}

	@RequestMapping(value = "/admin/item/export", method = RequestMethod.GET)
	public ModelAndView getExcel(@RequestParam("parent") String parent) {
		//TODO Different ExcelView For Book Items
		return new ModelAndView("ItemExcel", "itemList", itemService.getItemsHavingCategory(categoryService.getCategory(parent)));
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
        	File file = new File(filepathImages, "temporary/" + filename);
        	try {
        		photo.transferTo(file);
        		Thumbnails
        			.of(file)
        			.size(150, 150)
        			.toFile(new File(filepathImages, ITEMS_FILEPATH + filename));
        		item.setPhotoPath(filename); //Set Object's Attribute
        	} catch(IOException exception) {
        		log.error("IOException", exception);
        	} finally {
        		file.delete(); // Delete Temporary Image
        	}
		}
	}

	/**
	 * Delete Old Photo In Case Of Updating
	 * @param photo Photo's Filename
	 */
	void deletePhoto(String photo) {
		log.info("deletePhoto() Invoked For Photo = {}.", photo);
		if(photo != null) {
			File file = new File(filepathImages, ITEMS_FILEPATH + photo);
			boolean result = file.delete();
			log.info("deletePhoto() Result = {}.", result); // true|false Indicating Success|Failure
		}
	}	
	
	/**
	 * Populate Item Form
	 * @param model Model
	 * @param item Item Object
	 */
	void populateForm(Model model, Item item) {
		model.addAttribute("item", item);
		List<Category> categories = categoryService.getCategories(null, null);
		Iterator<Category> iterator = categories.iterator();
		while(iterator.hasNext()) {
			Category category = iterator.next();
			if(Arrays.asList("Music", "Films").contains(category.getTitle())) { // These Should Not Be A Selection
				iterator.remove();
			}
		}
		model.addAttribute("selectCategory", categories);
		model.addAttribute("selectComment", commentService.getComments("title", "ASC"));
		model.addAttribute("selectActivity", activityService.getActivities(null, null));
		model.addAttribute("selectSubtitles", subtitlesService.getSubtitles(null, null));
		model.addAttribute("selectPublisher", publisherService.getPublishers("title", "ASC"));
		model.addAttribute("selectRating", Arrays.asList("1", "2", "3", "4", "5"));
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
		Set<CommentItem> commentItems = new HashSet<>();
		for(String comment : comments) {
			try {
				CommentItem commentItem = new CommentItem();
				commentItem.setIdItem(item);
				commentItem.setIdComment(commentService.getComment(Long.parseLong(comment)));
				if(persistRelations) { //Persist
					commentItemService.persistCommentItem(commentItem);
				}
				commentItems.add(commentItem);
			} catch(NumberFormatException exception) { //Ignore Null Drop-Down Values
			
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
		List<ArtistActivityItem> artistActivityItems = new ArrayList<>();
		for(int i = 0; i < artists.length; i++) {
			if(!"".equals(artists[i]) || !"".equals(activities[i])) { //Artist or Activity Given
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
				} catch(NumberFormatException exception) { //Ignore Null Drop-Down Values
				
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
