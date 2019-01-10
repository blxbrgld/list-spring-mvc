package gr.blxbrgld.mylist.service;

import gr.blxbrgld.mylist.dao.hibernate.ItemDao;
import gr.blxbrgld.mylist.dao.hibernate.PublisherDao;
import gr.blxbrgld.mylist.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Publisher Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class PublisherServiceImplementation implements PublisherService {

    @Autowired
    private PublisherDao publisherDao;

    @Autowired
    private ItemDao itemDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize("hasRole('Administrator')")
    public void persistPublisher(Publisher publisher, Errors errors) {
        validateTitle(publisher, errors);
        if(!errors.hasErrors()) {
            publisherDao.persist(publisher);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize("hasRole('Administrator')")
    public void mergePublisher(Publisher publisher, Errors errors) {
        validateTitle(publisher, errors);
        if(!errors.hasErrors()) {
            publisherDao.merge(publisher);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize("hasRole('Administrator')")
    public List<Publisher> getPublishers(String property, String order) {
        return publisherDao.getAll(property, order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize("hasRole('Administrator')")
    public Publisher getPublisher(Long id) {
        return publisherDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize("hasRole('Administrator')")
    public boolean deletePublisher(Long id) {
        if(!itemDao.havingPublisherExists(publisherDao.get(id))) { // No Items With This Publisher Exist
            publisherDao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate Uniqueness Of Publisher's Title
     * @param publisher Publisher Object
     * @param errors BindingResult Errors Of Subtitles Form
     */
    private void validateTitle(Publisher publisher, Errors errors) {
        Publisher existing = publisherDao.findByTitle(publisher.getTitle());
        if(existing != null && !existing.getId().equals(publisher.getId()) ) {
            errors.rejectValue("title", "error.duplicate");
        }
    }
}
