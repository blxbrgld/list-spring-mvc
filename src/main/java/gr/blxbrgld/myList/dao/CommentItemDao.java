package gr.blxbrgld.myList.dao;

import gr.blxbrgld.myList.model.Comment;
import gr.blxbrgld.myList.model.CommentItem;
import gr.blxbrgld.myList.model.Item;

/**
 * CommentItem's DAO Interface
 */
public interface CommentItemDao extends AbstractDao<CommentItem> {

	/**
	 * Check If CommentItem Objects Having The Given Comment Exist
	 * @param comment Comment Object
	 * @return TRUE or FALSE
	 */
	boolean havingCommentExists(Comment comment);
	
	/**
	 * Delete CommentItem Objects Given The Related Item Object
	 * @param item Item Object
	 */
	void deleteByItem(Item item);
}
