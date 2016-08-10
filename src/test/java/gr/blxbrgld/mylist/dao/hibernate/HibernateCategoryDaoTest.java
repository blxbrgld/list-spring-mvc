package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.dao.CategoryDao;
import gr.blxbrgld.mylist.dao.DaoTestBase;
import gr.blxbrgld.mylist.model.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author blxbrgld
 */
public class HibernateCategoryDaoTest extends DaoTestBase {

    @Autowired private CategoryDao categoryDao;

    @Test
    public void getAll() {
        List<Category> categoryList = categoryDao.getAll("title", null); //Ascending Order Is The Default
        Assert.assertEquals("Classical Music", categoryList.get(0).getTitle());
        categoryList = categoryDao.getAll("title", "DESC");
        Assert.assertEquals("Popular Music", categoryList.get(0).getTitle());
    }

    @Test
    public void findByTitle() {
        Category category = categoryDao.findByTitle("Greek Music");
        Assert.assertNotNull(category);
    }

    @Test
    public void findByParent() {
        Category parent = categoryDao.findByTitle("Music");
        List<Category> categoryList = categoryDao.findByParent(parent);
        Assert.assertEquals(3, categoryList.size());
    }
}
