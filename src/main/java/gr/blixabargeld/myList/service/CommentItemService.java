package gr.blixabargeld.myList.service;

import gr.blixabargeld.myList.model.CommentItem;
import gr.blixabargeld.myList.model.Item;

/**
 * CommentItem's Service Interface
 */
public interface CommentItemService {

	/**
	 * Persist CommentItem Object
	 * @param commentItem CommentItem Object
	 */
	void persistCommentItem(CommentItem commentItem);

	/**
	 * Delete CommentItem Objects Given The Related Item Object
	 * @param item Item Object
	 */
	void deleteCommentItem(Item item);
}
