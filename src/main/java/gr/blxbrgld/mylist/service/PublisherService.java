package gr.blxbrgld.mylist.service;

import gr.blxbrgld.mylist.model.Publisher;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Publisher Service Interface
 * @author blxbrgld
 */
public interface PublisherService {

    /**
     * Persist Publisher Object
     * @param publisher Publisher Object
     * @param errors BindingResult Errors Of Publisher Form
     */
    void persistPublisher(Publisher publisher, Errors errors);

    /**
     * Merge Publisher Object
     * @param publisher Publisher Object
     * @param errors BindingResult Errors Of Publisher Form
     */
    void mergePublisher(Publisher publisher, Errors errors);

    /**
     * Get All Publisher Objects
     * @param property Property To Order Results By
     * @param order Ascending or Descending Ordering
     * @return List of Publishers
     */
    List<Publisher> getPublishers(String property, String order);

    /**
     * Get Publisher Object Given It's Id
     * @param id Publisher's Id
     * @return Publisher Object
     */
    Publisher getPublisher(Long id);

    /**
     * Get Publisher Object Given It's Title
     * @param title Publisher's Title
     * @return Publisher Object
     */
    Publisher getPublisherByTitle(String title);

    /**
     * Delete Publisher With The Given Id If There Are No Related Item Objects
     * @param id Publisher's Id
     * @return TRUE or FALSE
     */
    boolean deletePublisher(Long id);
}
