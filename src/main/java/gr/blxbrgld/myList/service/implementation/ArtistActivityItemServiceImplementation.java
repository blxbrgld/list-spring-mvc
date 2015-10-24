package gr.blxbrgld.myList.service.implementation;

import gr.blxbrgld.myList.dao.ArtistActivityItemDao;
import gr.blxbrgld.myList.model.Artist;
import gr.blxbrgld.myList.model.ArtistActivityItem;
import gr.blxbrgld.myList.model.Item;
import gr.blxbrgld.myList.service.ArtistActivityItemService;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * ArtistActivityItem's Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ArtistActivityItemServiceImplementation implements ArtistActivityItemService {

	@Inject private ArtistActivityItemDao artistActivityItemDao;

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistArtistActivityItem(ArtistActivityItem artistActivityItem) {
		artistActivityItemDao.persist(artistActivityItem);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void deleteArtistActivityItem(Item item) {
		artistActivityItemDao.deleteByItem(item);
	}

	@Override
	@PreAuthorize("permitAll")
	public Long countArtistActivityItemsHavingArtist(Artist artist) {
		return artistActivityItemDao.countArtistActivityItems(artist);
	}
}
