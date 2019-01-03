package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.model.Publisher;

/**
 * PublisherDao Interface
 * @author blxbrgld
 */
public interface PublisherDao extends AbstractDao<Publisher> {

    /**
     * Find Publisher Having The Given Title
     * @param title Publisher's Title
     * @return Publisher Object
     */
    Publisher findByTitle(String title);
}
