package gr.blxbrgld.mylist.service.implementation;

import gr.blxbrgld.mylist.dao.ArtistActivityItemDao;
import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.ArtistActivityItem;
import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.service.ArtistActivityItemService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * ArtistActivityItem's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ArtistActivityItemServiceImplementation implements ArtistActivityItemService {

	@Autowired private ArtistActivityItemDao artistActivityItemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistArtistActivityItem(ArtistActivityItem artistActivityItem) {
		artistActivityItemDao.persist(artistActivityItem);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteArtistActivityItem(Item item) {
		artistActivityItemDao.deleteByItem(item);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("permitAll")
	public Long countArtistActivityItemsHavingArtist(Artist artist) {
		return artistActivityItemDao.countArtistActivityItems(artist);
	}
}
