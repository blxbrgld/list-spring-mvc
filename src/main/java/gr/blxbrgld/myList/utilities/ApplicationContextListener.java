package gr.blxbrgld.mylist.utilities;

import gr.blxbrgld.mylist.dao.jdbc.CategoryDetailsDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Menu Categories and Other Attributes Initialization At Application's Startup
 * @author blxbrgld
 */
@Slf4j
public class ApplicationContextListener implements ServletContextListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		CategoryDetailsDao categoryDetailsDao = applicationContext.getBean(CategoryDetailsDao.class);
	    context.setAttribute("categoriesTree", categoryDetailsDao.categoriesTree()); // Menu Categories
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//Nothing To Do
	}
}
