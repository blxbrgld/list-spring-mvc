package gr.blixabargeld.myList.utilities;

import gr.blixabargeld.myList.dao.CategoryDetailsDao;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * Menu Categories and Other Attributes Initialization At Application's Startup
 */
public class ApplicationContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		ApplicationContext applicationContext = (ApplicationContext) event.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		CategoryDetailsDao categoryDetailsDao = (CategoryDetailsDao) applicationContext.getBean(gr.blixabargeld.myList.dao.CategoryDetailsDao.class);
	    ServletContext context = event.getServletContext();

	    //Categories For Menus
	    Map<String, List<String>> categoriesTree = categoryDetailsDao.categoriesTree(); 
		context.setAttribute("categoriesTree", categoriesTree);
	    
		//Wallpaper
		String path = context.getInitParameter("imagesFolder") + "wallpapers";
		context.setAttribute("wallpaper", randomWallpaper(path));
		
		//Author's Email Address + Year For Footer
		GregorianCalendar currentDate = new GregorianCalendar();
		int currentYear = currentDate.get(Calendar.YEAR);
		context.setAttribute("currentYear", currentYear);
		
		String authorsEmail = context.getInitParameter("authorsEmail");
		context.setAttribute("authorsEmail", authorsEmail);
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
