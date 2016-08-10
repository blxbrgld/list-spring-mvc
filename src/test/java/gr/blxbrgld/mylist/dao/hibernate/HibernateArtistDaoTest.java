package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.ArtistDao;
import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.model.Artist;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author blxbrgld
 */
public class HibernateArtistDaoTest extends DaoTestBase {

    @Autowired private ArtistDao artistDao;

    @Test
    public void findByTitle() {
        Artist artist = artistDao.findByTitle("Aki Kaurismaki");
        Assert.assertNotNull(artist);
    }

    @Test
    public void findLast() {
        Artist artist = artistDao.findLast();
        Assert.assertNotNull(artist);
        Assert.assertEquals("Gakuryu Ishii", artist.getTitle());
    }

    @Test
    public void findRandom() {
        Artist artist = artistDao.findRandom();
        Assert.assertNotNull(artist);
    }

    @Test
    public void findLike() {
        List<Artist> artistList = artistDao.findLike("Mark");
        Assert.assertEquals(3, artistList.size());
        Assert.assertEquals("Mark Chung", artistList.get(0).getTitle()); //List Is Ordered By Title ASC
    }
}
