package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.dao.SubtitlesDao;
import gr.blxbrgld.mylist.model.Subtitles;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author blxbrgld
 */
public class HibernateSubtitlesDaoTest extends DaoTestBase {

    @Autowired private SubtitlesDao subtitlesDao;

    @Test
    public void findByTitle() {
        Subtitles subtitles = subtitlesDao.findByTitle("Greek Subtitles");
        Assert.assertNotNull(subtitles);
        subtitles = subtitlesDao.findByTitle("Greek");
        Assert.assertNull(subtitles);
    }
}
