package gr.blxbrgld.mylist.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.dao.ActivityDao;
import gr.blxbrgld.mylist.dao.ArtistActivityItemDao;
import gr.blxbrgld.mylist.model.Activity;
import gr.blxbrgld.mylist.service.ActivityService;

/**
 * Activity's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ActivityServiceImplementation implements ActivityService {

	@Autowired private ActivityDao activityDao;
	@Autowired private ArtistActivityItemDao artistActivityItemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistActivity(Activity activity, Errors errors) {
		validateTitle(activity, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            activityDao.persist(activity);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeActivity(Activity activity, Errors errors) {
		validateTitle(activity, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            activityDao.merge(activity);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Activity> getActivities(String property, String order) {
		return activityDao.getAll(property, order);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Activity getActivity(Long id) {
		return activityDao.get(id);
	}

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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
