package gr.blxbrgld.mylist.service;

import gr.blxbrgld.mylist.model.CommentItem;
import gr.blxbrgld.mylist.model.Item;

/**
 * CommentItem's Service Interface
 * @author blxbrgld
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
