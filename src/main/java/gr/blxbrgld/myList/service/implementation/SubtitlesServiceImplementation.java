package gr.blxbrgld.mylist.service.implementation;

import java.util.List;

import gr.blxbrgld.mylist.dao.ItemDao;
import gr.blxbrgld.mylist.dao.SubtitlesDao;
import gr.blxbrgld.mylist.model.Subtitles;
import gr.blxbrgld.mylist.service.SubtitlesService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * Subtitles' Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class SubtitlesServiceImplementation implements SubtitlesService {

	@Autowired SubtitlesDao subtitlesDao;
	@Autowired ItemDao itemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistSubtitles(Subtitles subtitles, Errors errors) {
		validateTitle(subtitles, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            subtitlesDao.persist(subtitles);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeSubtitles(Subtitles subtitles, Errors errors) {
		validateTitle(subtitles, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            subtitlesDao.merge(subtitles);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Subtitles> getSubtitles(String property, String order) {
		return subtitlesDao.getAll(property, order);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Subtitles getSubtitles(Long id) {
		return subtitlesDao.get(id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public boolean deleteSubtitles(Long id) {
		if(!itemDao.havingSubtitlesExists(subtitlesDao.get(id))) { //No Items With This Subtitles Exist
			subtitlesDao.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Validate Uniqueness Of Subtitles' Title
	 * @param subtitles Subtitles Object
	 * @param errors BindingResult Errors Of Subtitles Form
	 */
	private void validateTitle(Subtitles subtitles, Errors errors) {
		Subtitles existing = subtitlesDao.findByTitle(subtitles.getTitle());
		if(existing != null && !existing.getId().equals(subtitles.getId()) ) {
			errors.rejectValue("title", "error.duplicate");
		}
	}
}
