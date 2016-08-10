package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.CommentDao;
import gr.blxbrgld.mylist.dao.CommentItemDao;
import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.dao.ItemDao;
import gr.blxbrgld.mylist.model.Comment;
import gr.blxbrgld.mylist.model.Item;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author blxbrgld
 */
public class HibernateCommentItemDaoTest extends DaoTestBase {

    @Autowired private CommentItemDao commentItemDao;
    @Autowired private CommentDao commentDao;
    @Autowired private ItemDao itemDao;
    @Autowired private SessionFactory sessionFactory;

    @Test
    public void havingCommentExists() {
        Comment comment = commentDao.findByTitle("Documentary");
        Assert.assertTrue(commentItemDao.havingCommentExists(comment));
    }

    @Test
    public void deleteByItem() {
        Comment comment = commentDao.findByTitle("Single");
        Assert.assertTrue(commentItemDao.havingCommentExists(comment)); //An Item Exist
        commentItemDao.deleteByItem(itemDao.get(5570L)); //A Little Cheating Here To Delete The Item

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        Assert.assertFalse(commentItemDao.havingCommentExists(comment)); //Deleted
    }
}
