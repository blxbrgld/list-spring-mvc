package gr.blxbrgld.mylist.dao;

import gr.blxbrgld.mylist.model.Activity;

/**
 * Activity's DAO Interface
 * @author blxbrgld
 */
public interface ActivityDao extends AbstractDao<Activity> {

	/**
	 * Find Activity Having The Given Title
	 * @param title Activity's Title
	 * @return Activity Object
	 */
	Activity findByTitle(String title);
}
