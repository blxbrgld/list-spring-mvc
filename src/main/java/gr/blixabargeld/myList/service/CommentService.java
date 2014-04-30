package gr.blixabargeld.myList.service;

import java.util.List;

import org.springframework.validation.Errors;

import gr.blixabargeld.myList.model.Comment;

/**
 * Comment's Service Interface
 */
public interface CommentService {

	/**
	 * Persist Comment Object
	 * @param comment Comment Object
	 * @param errors BindingResult Errors Of Comment Form
	 */
	void persistComment(Comment comment, Errors errors);
	
	/**
	 * Merge Comment Object
	 * @param comment Comment Object
	 * @param errors BindingResult Errors Of Comment Form
	 */
	void mergeComment(Comment comment, Errors errors);
	
	/**
	 * Get All Comment Objects
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Comments
	 */
	List<Comment> getComments(String property, String order);
	
	/**
	 * Get Comment Object Given It's Id
	 * @param id Comment's Id
	 * @return Comment Object
	 */
	Comment getComment(Long id);
	
	/**
	 * Delete Comment With The Given Id If There Are No Related Item Objects
	 * @param id Comment's Id
	 * @return TRUE or FALSE
	 */
	boolean deleteComment(Long id);
}
