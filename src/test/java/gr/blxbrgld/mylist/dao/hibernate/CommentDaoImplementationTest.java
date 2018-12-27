package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.model.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author blxbrgld
 */
public class CommentDaoImplementationTest extends DaoTestBase {

    @Autowired
    private CommentDao commentDao;

    @Test
    public void findByTitle() {
        Comment comment = commentDao.findByTitle("Documentary");
        Assert.assertNotNull(comment);
        comment = commentDao.findByTitle("Poetry");
        Assert.assertNull(comment); //Not Implemented Yet, Maybe In The Future
    }
}
