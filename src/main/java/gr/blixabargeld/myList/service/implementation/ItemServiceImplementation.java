package gr.blixabargeld.myList.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blixabargeld.myList.dao.ItemDao;
import gr.blixabargeld.myList.model.Artist;
import gr.blixabargeld.myList.model.ArtistActivityItem;
import gr.blixabargeld.myList.model.Category;
import gr.blixabargeld.myList.model.CommentItem;
import gr.blixabargeld.myList.model.Item;
import gr.blixabargeld.myList.service.ItemService;
import gr.blixabargeld.myList.utilities.ReturningValues;

/**
 * Item's Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ItemServiceImplementation implements ItemService {

	private static final List<String> filmCategories = new ArrayList<String>(Arrays.asList(new String[] {"DVD Films", "DivX Films"}));
	
	@Inject private ItemDao itemDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistItem(Item item, Errors errors) {

		validateCategory(item, errors);
		validateArtistActivityItems(item, errors);
		validateCommentItems(item, errors);
		validateSubtitles(item, errors);
		validateYear(item, errors);
		boolean valid = !errors.hasErrors();
		if(valid) itemDao.persist(item);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeItem(Item item, Errors errors) {

		validateCategory(item, errors);
		validateArtistActivityItems(item, errors);
		validateCommentItems(item, errors);
		validateSubtitles(item, errors);
		validateYear(item, errors);
		boolean valid = !errors.hasErrors();
		if(valid) itemDao.merge(item);
	}

	@Override
	@PreAuthorize("permitAll")
	public List<Item> getItems(String property, String order, int first, int size) {
		
		return itemDao.getAll(property, order, first, size);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Item> getItemsHavingCategory(Category category) {
		
		return itemDao.findByCategory(category);
	}
	
	@Override
	@PreAuthorize("permitAll")
	public List<Item> getItemsHavingArtist(Artist artist, int first, int size) {
		
		return itemDao.findByArtist(artist, first, size);
	}
	
	@Override
	@PreAuthorize("permitAll")
	public ReturningValues searchItems(String searchFor, String searchIn, String property, String order, int first, int size) {
		
		return itemDao.search(searchFor, searchIn, property, order, first, size);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void initializeLucene(boolean synchronously) {
		
		itemDao.lucene(synchronously);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Item getItem(Long id) {

		return itemDao.get(id);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteItem(Item item) {

		itemDao.delete(item);
	}

	@Override
	@PreAuthorize("permitAll")
	public Long countItems() {
		
		return itemDao.count(); 
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Long countItemsHavingCategory(String category) {
		
		return itemDao.countItems(category);
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Item findLastDateHavingParent(String parent) {
		
		return itemDao.findLastDate(parent);
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Integer findNextPlaceHavingParent(String parent) {
		
		return itemDao.findNextPlace(parent);
	}
	
	/**
	 * An Item Can Not Belong To A Root Category
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateCategory(Item item, Errors errors) {
		
		if(item.getCategory() != null) {
			
			if(item.getCategory().getParent() == null) {
				
				errors.rejectValue("category", "error.imperfect.item.category");
			}
		}
	}
	
	/**
	 * Validate Uniqueness Of Item's artistActivityItems. At Least One artistActivityItem Is Required
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateArtistActivityItems(Item item, Errors errors) {
		
		List<ArtistActivityItem> artistActivityItems = item.getArtistActivityItems();
		
		if(artistActivityItems.isEmpty()) { //At Least One Is Required
			
			errors.rejectValue("artistActivityItems", "error.missing.item.artistActivityItems");
		}
		else { //No Duplicates Allowed
			
			Map<String, List<String>> artistActivityMap = new HashMap<String, List<String>>();
			
			for(ArtistActivityItem artistActivityItem : artistActivityItems) {
				
				String artist = artistActivityItem.getIdArtist().getTitle();
				String activity = artistActivityItem.getIdActivity().getTitle();
				
				if(artist.equals("") || activity==null) {
					
					errors.rejectValue("artistActivityItems", "error.imperfect.item.artistActivityItems");
					return; //Stop Processing
				}
				else {
					
					if(artistActivityMap.containsKey(activity)) { //Artists With This Activity Exist
						
						List<String> artistList = artistActivityMap.get(activity);
						artistList.add(artist);
						artistActivityMap.put(activity, artistList);
					}
					else {
					
						List<String> artistList = new ArrayList<String>();
						artistList.add(artist);
						artistActivityMap.put(activity, artistList);
					}
				}
			}
			
			artistLoop:
			for(List<String> value : artistActivityMap.values()) { //Loop Through Map Values To Check For Duplicates
				
				Set<String> artistSet = new HashSet<String>(value);
				
				if(value.size() != artistSet.size()) {
					errors.rejectValue("artistActivityItems", "error.duplicate.item.artistActivityItems");
					break artistLoop; //Stop Processing
				}
			}
		}
	}

	/**
	 * Validate Uniqueness Of Item's commentItems
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateCommentItems(Item item, Errors errors) {
		
		List<String> commentsList = new ArrayList<String>();
		
		for(CommentItem comment : item.getCommentItems()) {
			
			commentsList.add(comment.getIdComment().getTitle());
		}
		
		Set<String> commentsSet = new HashSet<String>(commentsList);
		
		if(commentsSet.size() != commentsList.size()) errors.rejectValue("commentItems", "error.duplicate");
	}
	
	/**
	 * Validate Existence Of Subtitles For Film Items
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateSubtitles(Item item, Errors errors) {
		
		if(item.getCategory() != null) {
			if(filmCategories.contains(item.getCategory().getTitle()) && item.getSubtitles() == null) {	
				errors.rejectValue("subtitles", "error.missing.item.subtitles");
			}
		}
	}
	
	/**
	 * Validate Existence Of Year For Film Items
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateYear(Item item, Errors errors) {
		
		if(item.getCategory() != null) {
			if(filmCategories.contains(item.getCategory().getTitle()) && item.getYear() == null) {
				errors.rejectValue("year", "error.missing.item.year");
			}
		}	
	}
}