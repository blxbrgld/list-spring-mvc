package gr.blixabargeld.myList.dao;

import gr.blixabargeld.myList.model.Activity;

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
