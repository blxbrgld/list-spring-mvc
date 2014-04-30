package gr.blixabargeld.myList.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import gr.blixabargeld.myList.dao.CommentItemDao;
import gr.blixabargeld.myList.model.Comment;
import gr.blixabargeld.myList.model.CommentItem;
import gr.blixabargeld.myList.model.Item;

/**
 * CommentItem's DAO Implementation
 */
@Repository
public class HibernateCommentItemDao extends AbstractHibernateDao<CommentItem> implements CommentItemDao {

	@Override
	public boolean havingCommentExists(Comment comment) {
		
		Query query = getSession().getNamedQuery("findCommentItemsByComment");
		query.setParameter("comment", comment);
		query.setMaxResults(1);
		if(query.list().isEmpty()) return false; else return true;
	}
	
	@Override
	public void deleteByItem(Item item) {

		Query query = getSession().getNamedQuery("deleteCommentItemByItem");
		query.setParameter("item", item);
		query.executeUpdate();
	}
}
