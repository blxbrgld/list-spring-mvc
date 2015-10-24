package gr.blxbrgld.myList.utilities;

import gr.blxbrgld.myList.dao.CategoryDetailsDao;

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

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Menu Categories and Other Attributes Initialization At Application's Startup
 */
public class ApplicationContextListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(ApplicationContextListener.class);
			
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		CategoryDetailsDao categoryDetailsDao = applicationContext.getBean(gr.blxbrgld.myList.dao.CategoryDetailsDao.class);
	    /**
	     * Read Values From miscellaneous.properties File
	     */
		final String props = "/miscellaneous.properties";
		final Properties propsFromFile = new Properties();
		try {
			propsFromFile.load(getClass().getResourceAsStream(props));
		} 
		catch(IOException exception) {
			logger.error(exception);
		}
		for(String prop : propsFromFile.stringPropertyNames()) {
			if(System.getProperty(prop) == null) {
				System.setProperty(prop, propsFromFile.getProperty(prop));
			}
		}
	    /**
	     * Set Miscellaneous Context Parameters For Menu, Footer etc.
	     */
	    context.setAttribute("categoriesTree", categoryDetailsDao.categoriesTree()); //Menu Categories
		context.setAttribute("wallpaper", randomWallpaper(System.getProperty("filepath.images") + "wallpapers")); //Wallpaper
		GregorianCalendar currentDate = new GregorianCalendar();
		int currentYear = currentDate.get(Calendar.YEAR);
		context.setAttribute("currentYear", currentYear); //Year
		context.setAttribute("authorsEmail", System.getProperty("author.email")); //Author's Email
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { //Nothing To Do

	}
	
	/**
	 * Read /images/wallpapers/ Folder's Filenames and Return One Random
	 * @param path Filesystem Folder Containing Application's Wallpapers
	 * @return Random Filename to be Used As Wallpaper
	 */
	private static String randomWallpaper(String path) {
		String[] files = new File(path).list();
		List<String> filenames = new ArrayList<String>();
		for(String f : files) { filenames.add(f); }
		return filenames.get(randomInteger(0, filenames.size()-1));
	}
	
	/**
	 * @param minimum The 'minimum' Possible Value
	 * @param maximum The 'maximum' Possible Value
	 * @return Random Integer In Range [minimum, maximum]
	 */
	private static int randomInteger(int minimum, int maximum) {
		Random generator = new Random();
		return generator.nextInt(maximum - minimum + 1) + minimum;
	}
}
