package gr.blxbrgld.mylist.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blxbrgld.mylist.dao.CommentItemDao;
import gr.blxbrgld.mylist.model.Comment;
import gr.blxbrgld.mylist.model.CommentItem;
import gr.blxbrgld.mylist.model.Item;

/**
 * CommentItem's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class HibernateCommentItemDao extends AbstractHibernateDao<CommentItem> implements CommentItemDao {

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean havingCommentExists(Comment comment) {
		Query query = getSession().getNamedQuery("findCommentItemsByComment");
		query.setParameter("comment", comment);
		query.setMaxResults(1);
        return query.list().isEmpty() ? false : true;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void deleteByItem(Item item) {
		Query query = getSession().getNamedQuery("deleteCommentItemByItem");
		query.setParameter("item", item);
		query.executeUpdate();
	}
}
