package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.*;
import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.model.Subtitles;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author blxbrgld
 */
public class ItemDaoImplementationTest extends DaoTestBase {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private SubtitlesDao subtitlesDao;

    @Test
    public void findByCategory() {
        List<Item> dvd = itemDao.findByCategory(categoryDao.findByTitle("DVD Films"));
        Assert.assertNotNull(dvd);
        List<Item> divX = itemDao.findByCategory(categoryDao.findByTitle("DivX Films"));
        Assert.assertNotNull(divX);
        List<Item> films = itemDao.findByCategory(categoryDao.findByTitle("Films")); //Film Category Should Return The Child Items
        Assert.assertEquals(films.size(), dvd.size() + divX.size());
    }

    @Test
    public void findByArtist() {
        Artist artist = artistDao.findByTitle("Aki Kaurismaki");
        List<Item> itemList = itemDao.findByArtist(artist, 0, 1000); //A Really Big Page
        Assert.assertEquals(1, itemList.size());
        itemList = itemDao.findByArtist(artist, 1, 1000); //Non Inclusive
        Assert.assertEquals(0, itemList.size());
    }

    @Test
    public void havingCategoryExists() {
        Category category = categoryDao.findByTitle("DVD Films");
        Assert.assertTrue(itemDao.havingCategoryExists(category));
    }

    @Test
    public void havingSubtitlesExists() {
        Subtitles subtitles = subtitlesDao.findByTitle("No Subtitles");
        Assert.assertTrue(itemDao.havingSubtitlesExists(subtitles));
    }

    @Test
    public void findLastDate() {
        Category category = categoryDao.findByTitle("Films");
        Item item = itemDao.findLastDate(category.getTitle(), true); // The Most Recent Film Item
        Assert.assertEquals("24 Hour Party People", item.getTitleEng());
    }

    @Test
    public void findNextPlace() {
        Category category = categoryDao.findByTitle("Films");
        Assert.assertTrue(itemDao.findNextPlace(category.getTitle()).equals(63));
    }
}
