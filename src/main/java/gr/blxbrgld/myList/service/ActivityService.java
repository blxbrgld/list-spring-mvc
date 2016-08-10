package gr.blxbrgld.mylist.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.model.Activity;

/**
 * Activity's Service Interface
 * @author blxbrgld
 */
public interface ActivityService {

	/**
	 * Persist Activity Object
	 * @param activity Activity Object
	 * @param errors BindingResult Errors Of Activity Form
	 */
	void persistActivity(Activity activity, Errors errors);
	
	/**
	 * Merge Activity Object
	 * @param activity Activity Object
	 * @param errors BindingResult Errors Of Activity Form
	 */
	void mergeActivity(Activity activity, Errors errors);
		
	/**
	 * Get All Activity Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Activities
	 */
	List<Activity> getActivities(String property, String order);
	
	/**
	 * Get Activity Object Given It's Id
	 * @param id Activity's Id
	 * @return Activity Object
	 */
	Activity getActivity(Long id);
	
	/**
	 * Delete Activity With The Given Id If There Are No Related ArtistActivityItem Objects
	 * @param id Activity's Id
	 * @return TRUE or FALSE
	 */
	boolean deleteActivity(Long id);
	
	/**
	 * Count All Activity Objects
	 * @return Count Of Activities
	 */
	Long countActivities();
}