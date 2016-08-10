package gr.blxbrgld.mylist.dao.jdbc;

import gr.blxbrgld.mylist.dao.DaoTestBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

/**
 * @author blxbrgld
 */
public class JdbcCategoryDaoTest extends DaoTestBase {

    @Autowired private JdbcCategoryDao categoryDao;

    @Test
    public void categoriesTree() {
        Map<String, List<String>> categoryTree = categoryDao.categoriesTree();
        Assert.assertEquals(2, categoryTree.size()); //Music + Films
        assertThat(categoryTree, hasEntry("Films", Arrays.asList(new String[]{"DVD Films", "DivX Films"}))); //Ordered By Id
        assertThat(categoryTree, hasEntry("Music", Arrays.asList(new String[] { "Popular Music", "Classical Music", "Greek Music"})));
    }
}
