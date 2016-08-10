package gr.blxbrgld.mylist.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import gr.blxbrgld.mylist.dao.CommentItemDao;
import gr.blxbrgld.mylist.model.CommentItem;
import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.service.CommentItemService;

/**
 * CommentItem's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class CommentItemServiceImplementation implements CommentItemService {

	@Autowired private CommentItemDao commentItemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistCommentItem(CommentItem commentItem) {
		commentItemDao.persist(commentItem);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteCommentItem(Item item) {
		commentItemDao.deleteByItem(item);
	}
}
