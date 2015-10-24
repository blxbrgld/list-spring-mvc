package gr.blxbrgld.myList.service.implementation;

import java.util.List;

import gr.blxbrgld.myList.dao.ItemDao;
import gr.blxbrgld.myList.dao.SubtitlesDao;
import gr.blxbrgld.myList.model.Subtitles;
import gr.blxbrgld.myList.service.SubtitlesService;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * Subtitles' Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class SubtitlesServiceImplementation implements SubtitlesService {

	@Inject SubtitlesDao subtitlesDao;
	@Inject ItemDao itemDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistSubtitles(Subtitles subtitles, Errors errors) {
		validateTitle(subtitles, errors);
		boolean valid = !errors.hasErrors();
		if(valid) subtitlesDao.persist(subtitles);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeSubtitles(Subtitles subtitles, Errors errors) {
		validateTitle(subtitles, errors);
		boolean valid = !errors.hasErrors();
		if(valid) subtitlesDao.merge(subtitles);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Subtitles> getSubtitles(String property, String order) {
		return subtitlesDao.getAll(property, order);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Subtitles getSubtitles(Long id) {
		return subtitlesDao.get(id);
	}

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
