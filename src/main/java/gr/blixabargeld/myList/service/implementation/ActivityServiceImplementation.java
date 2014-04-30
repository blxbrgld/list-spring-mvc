package gr.blixabargeld.myList.service.implementation;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blixabargeld.myList.dao.ActivityDao;
import gr.blixabargeld.myList.dao.ArtistActivityItemDao;
import gr.blixabargeld.myList.model.Activity;
import gr.blixabargeld.myList.service.ActivityService;

/**
 * Activity's Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ActivityServiceImplementation implements ActivityService {

	@Inject private ActivityDao activityDao;
	@Inject private ArtistActivityItemDao artistActivityItemDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistActivity(Activity activity, Errors errors) {
		
		validateTitle(activity, errors);
		boolean valid = !errors.hasErrors();
		if(valid) activityDao.persist(activity);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeActivity(Activity activity, Errors errors) {
		
		validateTitle(activity, errors);
		boolean valid = !errors.hasErrors();
		if(valid) activityDao.merge(activity);
	}	
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Activity> getActivities(String property, String order) {
		
		return activityDao.getAll(property, order);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Activity getActivity(Long id) {
		
		return activityDao.get(id);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public boolean deleteActivity(Long id) {
		
		if(!artistActivityItemDao.havingActivityExists(activityDao.get(id))) { //No ArtistActivityItems With This Activity Exist
			
			activityDao.deleteById(id);
			return true;
		}
		else {
			
			return false;
		}
	}

	@Override
	@PreAuthorize("permitAll")
	public Long countActivities() {
		
		return activityDao.count();
	}

	/**
	 * Validate Uniqueness Of Activity's Title
	 * @param activity Activity Object
	 * @param errors BindingResult Errors Of Activity Form
	 */
	private void validateTitle(Activity activity, Errors errors) {

		Activity existing = activityDao.findByTitle(activity.getTitle());
		
		if(existing != null && !existing.getId().equals(activity.getId()) ) {
		
			errors.rejectValue("title", "error.duplicate");
		}
	}
}
