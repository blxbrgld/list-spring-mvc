package gr.blxbrgld.myList.dao;

import gr.blxbrgld.myList.model.Activity;

/**
 * Activity's DAO Interface
 */
public interface ActivityDao extends AbstractDao<Activity> {

	/**
	 * Find Activity Having The Given Title
	 * @param title Activity's Title
	 * @return Activity Object
	 */
	Activity findByTitle(String title);
}
