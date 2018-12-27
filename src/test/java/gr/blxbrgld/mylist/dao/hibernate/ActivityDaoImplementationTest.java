package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.model.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author blxbrgld
 */
public class ActivityDaoImplementationTest extends DaoTestBase {

    @Autowired
    private ActivityDao activityDao;

    @Test
    public void findByTitle() {
        Activity activity = activityDao.findByTitle("Musician");
        Assert.assertNotNull(activity);
        activity = activityDao.findByTitle("Author");
        Assert.assertNull(activity); //Not Implemented Yet, Maybe In The Future
    }
}
