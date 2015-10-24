package gr.blxbrgld.myList.dao;

import gr.blxbrgld.myList.model.Comment;

/**
 * Comment's DAO Interface
 */
public interface CommentDao extends AbstractDao<Comment> {

	/**
	 * Find Comment Having The Given Title
	 * @param title Comment's Title
	 * @return Comment Object
	 */
	Comment findByTitle(String title);
}
