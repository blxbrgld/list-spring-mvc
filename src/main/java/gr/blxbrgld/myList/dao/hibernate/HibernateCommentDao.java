package gr.blxbrgld.mylist.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.dao.CommentDao;
import gr.blxbrgld.mylist.model.Comment;

/**
 * Comment's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class HibernateCommentDao extends AbstractHibernateDao<Comment> implements CommentDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public Comment findByTitle(String title) {
		Query query = getSession().getNamedQuery("findCommentByTitle");
		query.setParameter("title", title);
		return (Comment) query.uniqueResult();
	}
}
