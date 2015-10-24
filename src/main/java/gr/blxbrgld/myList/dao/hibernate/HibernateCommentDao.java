package gr.blxbrgld.myList.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.myList.dao.CommentDao;
import gr.blxbrgld.myList.model.Comment;

/**
 * Comment's DAO Implementation 
 */
@Repository
public class HibernateCommentDao extends AbstractHibernateDao<Comment> implements CommentDao {

	@Override
	public Comment findByTitle(String title) {
		Query query = getSession().getNamedQuery("findCommentByTitle");
		query.setParameter("title", title);
		return (Comment) query.uniqueResult();
	}
}
