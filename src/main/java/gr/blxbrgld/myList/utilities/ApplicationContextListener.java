package gr.blxbrgld.mylist.utilities;

import gr.blxbrgld.mylist.dao.jdbc.CategoryDetailsDao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		CategoryDetailsDao categoryDetailsDao = applicationContext.getBean(CategoryDetailsDao.class);
	    /**
	     * Read Values From miscellaneous.properties File
	     */
		final String props = "/configuration.properties";
		final Properties propsFromFile = new Properties();
		try {
			propsFromFile.load(getClass().getResourceAsStream(props));
		} catch(IOException exception) {
			log.error("IOException", exception);
		}
		for(String prop : propsFromFile.stringPropertyNames()) {
            if(prop.startsWith("filepath") && System.getProperty(prop) == null) { //Set filepath Properties As System Properties
                System.setProperty(prop, propsFromFile.getProperty(prop));
            }
		}
	    /**
	     * Set Miscellaneous Context Parameters For Menu, Footer etc.
	     */
	    context.setAttribute("categoriesTree", categoryDetailsDao.categoriesTree()); //Menu Categories
		GregorianCalendar currentDate = new GregorianCalendar();
		int currentYear = currentDate.get(Calendar.YEAR);
		context.setAttribute("currentYear", currentYear); //Year
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//Nothing To Do
	}
}
