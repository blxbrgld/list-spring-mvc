package gr.blxbrgld.mylist.dao;

import gr.blxbrgld.mylist.model.Comment;
import gr.blxbrgld.mylist.model.CommentItem;
import gr.blxbrgld.mylist.model.Item;

/**
 * CommentItem's DAO Interface
 * @author blxbrgld
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
