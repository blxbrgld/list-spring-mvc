package gr.blixabargeld.myList.dao;

import gr.blixabargeld.myList.model.Comment;

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
