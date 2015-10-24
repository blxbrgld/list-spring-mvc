package gr.blxbrgld.myList.service.implementation;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import gr.blxbrgld.myList.dao.CommentItemDao;
import gr.blxbrgld.myList.model.CommentItem;
import gr.blxbrgld.myList.model.Item;
import gr.blxbrgld.myList.service.CommentItemService;

/**
 * CommentItem's Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class CommentItemServiceImplementation implements CommentItemService {

	@Inject private CommentItemDao commentItemDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistCommentItem(CommentItem commentItem) {
		commentItemDao.persist(commentItem);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteCommentItem(Item item) {
		commentItemDao.deleteByItem(item);
	}
}
