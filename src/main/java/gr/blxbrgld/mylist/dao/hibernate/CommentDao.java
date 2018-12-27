package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.model.Comment;

/**
 * Comment's DAO Interface
 * @author blxbrgld
 */
public interface CommentDao extends AbstractDao<Comment> {

	/**
	 * Find Comment Having The Given Title
	 * @param title Comment's Title
	 * @return Comment Object
	 */
	Comment findByTitle(String title);
}
