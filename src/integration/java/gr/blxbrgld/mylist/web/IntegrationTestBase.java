package gr.blxbrgld.mylist.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author blxbrgld
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:dataSource-integration.xml", //Integration Tests Database
    "file:src/main/webapp/WEB-INF/spring/application.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class IntegrationTestBase {

}
