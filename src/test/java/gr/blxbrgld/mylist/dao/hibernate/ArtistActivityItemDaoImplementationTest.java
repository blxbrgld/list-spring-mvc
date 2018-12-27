package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.*;
import gr.blxbrgld.mylist.model.Activity;
import gr.blxbrgld.mylist.model.Artist;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author blxbrgld
 */
public class ArtistActivityItemDaoImplementationTest extends DaoTestBase {

    @Autowired
    private ArtistActivityItemDao artistActivityItemDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void countArtistActivityItems() {
        Artist artist = artistDao.findByTitle("Aki Kaurismaki");
        Long items = artistActivityItemDao.countArtistActivityItems(artist);
        Assert.assertEquals(1, items.intValue()); //Kaurismaki Rules, Get All His Movies!
    }

    @Test
    public void havingArtistExists() {
        Artist artist = artistDao.findByTitle("Aki Kaurismaki");
        Assert.assertTrue(artistActivityItemDao.havingArtistExists(artist));
    }

    @Test
    public void havingActivityExists() {
        Activity activity = activityDao.findByTitle("Musician");
        Assert.assertTrue(artistActivityItemDao.havingActivityExists(activity));
    }

    @Test
    public void deleteByItem() {
        Artist artist = artistDao.findByTitle("Bruce Willis");
        Long items = artistActivityItemDao.countArtistActivityItems(artist);
        Assert.assertEquals(1, items.intValue()); //An Item Exist
        artistActivityItemDao.deleteByItem(itemDao.get(4L)); //A Little Cheating Here To Delete Bruce's Only Item

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        items = artistActivityItemDao.countArtistActivityItems(artist);
        Assert.assertEquals(0, items.intValue()); //Item Does Not Exist Anymore
    }
}
