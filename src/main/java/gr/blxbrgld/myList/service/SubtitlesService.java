package gr.blxbrgld.mylist.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.model.Subtitles;

/**
 * Subtitles' Service Interface
 * @author blxbrgld
 */
public interface SubtitlesService {

	/**
	 * Persist Subtitles Object
	 * @param subtitles Subtitles Object
	 * @param errors BindingResult Errors Of Subtitles Form
	 */
	void persistSubtitles(Subtitles subtitles, Errors errors);
	
	/**
	 * Merge Subtitles Object
	 * @param subtitles Subtitles Object
	 * @param errors BindingResult Errors Of Subtitles Form
	 */
	void mergeSubtitles(Subtitles subtitles, Errors errors);
	
	/**
	 * Get All Subtitles Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List of Subtitles
	 */
	List<Subtitles> getSubtitles(String property, String order);
	
	/**
	 * Get Subtitles Object Given It's Id
	 * @param id Subtitles' Id
	 * @return Subtitles Object
	 */
	Subtitles getSubtitles(Long id);
	
	/**
	 * Delete Subtitles With The Given Id If There Are No Related Item Objects
	 * @param id Subtitles' Id
	 * @return TRUE or FALSE
	 */
	boolean deleteSubtitles(Long id);
}
