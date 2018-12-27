package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.Comment;
import gr.blxbrgld.mylist.model.Subtitles;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

/**
 * @author blxbrgld
 */
public class HibernateAbstractDaoTest extends DaoTestBase {

    @Autowired
    private SubtitlesDao subtitlesDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void persist() {
        Subtitles subtitles = new Subtitles();
        subtitles.setTitle("French Subtitles");
        subtitlesDao.persist(subtitles);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        subtitles = subtitlesDao.findByTitle("French Subtitles");
        Assert.assertTrue(subtitles.getId().equals(4L)); //Auto Incremented
        Assert.assertNotNull(subtitles.getDateUpdated()); //Date Persisted Via Reflection
    }

    @Test
    public void merge() {
        Artist artist = artistDao.findByTitle("Peter Greenaway");
        artist.setTitle("Edited");
        artistDao.persist(artist);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        Artist edited = artistDao.findByTitle("Edited");
        Assert.assertEquals(artist.getId(), edited.getId()); //Id Did Not Change
        Assert.assertTrue(DateUtils.isSameDay(edited.getDateUpdated(), Calendar.getInstance())); //But dateUpdated Did
    }

    @Test
    public void get() {
        Subtitles subtitles = subtitlesDao.get(1L);
        Assert.assertEquals("No Subtitles", subtitles.getTitle());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void load() {
        Subtitles subtitles = subtitlesDao.load(4L);
        Assert.assertNull(subtitles); //This Is The Row That Will Raise The Exception
    }

    @Test
    public void delete() {
        Comment comment = commentDao.findByTitle("Split Single");
        commentDao.delete(comment);

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        comment = commentDao.findByTitle("Split Single");
        Assert.assertNull(comment); //Deleted
    }

    @Test
    public void deleteById() {
        Comment comment = commentDao.findByTitle("Split EP");
        commentDao.deleteById(comment.getId());

        sessionFactory.getCurrentSession().flush(); //Ensure That Hibernate Session Is Flushed
        comment = commentDao.findByTitle("Split EP");
        Assert.assertNull(comment); //Deleted
    }

    @Test
    public void count() {
        Long count = subtitlesDao.count();
        Assert.assertEquals(3, count.intValue());
    }
}
