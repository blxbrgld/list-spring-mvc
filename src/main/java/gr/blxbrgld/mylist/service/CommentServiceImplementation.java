package gr.blxbrgld.mylist.service;

import java.util.List;

import gr.blxbrgld.mylist.dao.hibernate.CommentDao;
import gr.blxbrgld.mylist.dao.hibernate.CommentItemDao;
import gr.blxbrgld.mylist.model.Comment;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * Comment's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class CommentServiceImplementation implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CommentItemDao commentItemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistComment(Comment comment, Errors errors) {
		validateTitle(comment, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            commentDao.persist(comment);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeComment(Comment comment, Errors errors) {
		validateTitle(comment, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            commentDao.merge(comment);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Comment> getComments(String property, String order) {
		return commentDao.getAll(property, order);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Comment getComment(Long id) {
		return commentDao.get(id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public boolean deleteComment(Long id) {
		if(!commentItemDao.havingCommentExists(commentDao.get(id))) { //No CommentItems With This Comment Exist
			commentDao.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Validate Uniqueness Of Comment's Title
	 * @param comment Comment Object
	 * @param errors BindingResult Errors Of Comment Form
	 */
	private void validateTitle(Comment comment, Errors errors) {
		Comment existing = commentDao.findByTitle(comment.getTitle());
		if(existing != null && !existing.getId().equals(comment.getId()) ) {
			errors.rejectValue("title", "error.duplicate");
		}
	}
}