package gr.blxbrgld.mylist.dao.hibernate;

import gr.blxbrgld.mylist.model.Publisher;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * PublisherDao Implementation
 * @author blxbrgld
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class PublisherDaoImplementation extends AbstractHibernateDao<Publisher> implements PublisherDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public Publisher findByTitle(String title) {
        Query query = getSession().getNamedQuery("findPublisherByTitle");
        query.setParameter("title", title);
        return (Publisher) query.uniqueResult();
    }
}
