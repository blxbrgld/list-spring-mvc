package gr.blxbrgld.mylist.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.dao.hibernate.ItemDao;
import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.ArtistActivityItem;
import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.model.CommentItem;
import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.utilities.ReturningValues;

/**
 * Item's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ItemServiceImplementation implements ItemService {

	private static final List<String> filmCategories = new ArrayList<>(Arrays.asList(new String[] {"DVD Films", "DivX Films"}));
	
	@Autowired
	private ItemDao itemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistItem(Item item, Errors errors) {
		validateCategory(item, errors);
		validateArtistActivityItems(item, errors);
		validateCommentItems(item, errors);
		validateSubtitles(item, errors);
		validateYear(item, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            itemDao.persist(item);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeItem(Item item, Errors errors) {
		validateCategory(item, errors);
		validateArtistActivityItems(item, errors);
		validateCommentItems(item, errors);
		validateSubtitles(item, errors);
		validateYear(item, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            itemDao.merge(item);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public List<Item> getItems(String property, String order, int first, int size) {
		return itemDao.getAll(property, order, first, size);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Item> getItemsHavingCategory(Category category) {
		return itemDao.findByCategory(category);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public List<Item> getItemsHavingArtist(Artist artist, int first, int size) {
		return itemDao.findByArtist(artist, first, size);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public ReturningValues searchItems(String searchFor, String searchIn, String property, String order, int first, int size) {
		return itemDao.search(searchFor, searchIn, property, order, first, size);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void initializeLucene(boolean synchronously) {
		itemDao.lucene(synchronously);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Item getItem(Long id) {
		return itemDao.get(id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteItem(Item item) {
		itemDao.delete(item);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public Long countItems() {
		return itemDao.count(); 
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public Long countItemsHavingCategory(String category) {
		return itemDao.countItems(category);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public Item findLastDateHavingParent(String parent) {
		return itemDao.findLastDate(parent);
	}

    /**
     * {@inheritDoc}
     */
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
		if(item.getCategory() != null && item.getCategory().getParent() == null) {
			errors.rejectValue("category", "error.imperfect.item.category");
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
			Map<String, List<String>> artistActivityMap = new HashMap<>();
			for(ArtistActivityItem artistActivityItem : artistActivityItems) {
				String artist = artistActivityItem.getIdArtist().getTitle();
				String activity = artistActivityItem.getIdActivity().getTitle();
				if("".equals(artist) || activity==null) {
					errors.rejectValue("artistActivityItems", "error.imperfect.item.artistActivityItems");
					return; //Stop Processing
				}
                else if(artistActivityMap.containsKey(activity)) { //Artists With This Activity Exist
                    List<String> artistList = artistActivityMap.get(activity);
                    artistList.add(artist);
                    artistActivityMap.put(activity, artistList);
                }
                else {
                    List<String> artistList = new ArrayList<>();
                    artistList.add(artist);
                    artistActivityMap.put(activity, artistList);
                }
			}

			for(List<String> value : artistActivityMap.values()) { //Loop Through Map Values To Check For Duplicates
				Set<String> artistSet = new HashSet<>(value);
				if(value.size() != artistSet.size()) {
					errors.rejectValue("artistActivityItems", "error.duplicate.item.artistActivityItems");
					break; //Stop Processing
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
		List<String> commentsList = new ArrayList<>();
		for(CommentItem comment : item.getCommentItems()) {
			commentsList.add(comment.getIdComment().getTitle());
		}
		Set<String> commentsSet = new HashSet<>(commentsList);
		if(commentsSet.size() != commentsList.size()) {
            errors.rejectValue("commentItems", "error.duplicate");
        }
	}
	
	/**
	 * Validate Existence Of Subtitles For Film Items
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateSubtitles(Item item, Errors errors) {
		if(item.getCategory() != null && filmCategories.contains(item.getCategory().getTitle()) && item.getSubtitles() == null) {
			errors.rejectValue("subtitles", "error.missing.item.subtitles");
		}
	}
	
	/**
	 * Validate Existence Of Year For Film Items
	 * @param item Item Object
	 * @param errors BindingResult Errors Of Item Form
	 */
	private void validateYear(Item item, Errors errors) {
		if(item.getCategory() != null && filmCategories.contains(item.getCategory().getTitle()) && item.getYear() == null) {
			errors.rejectValue("year", "error.missing.item.year");
		}
	}
}