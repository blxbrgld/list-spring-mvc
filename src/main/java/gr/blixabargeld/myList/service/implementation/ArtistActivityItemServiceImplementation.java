package gr.blixabargeld.myList.service.implementation;

import gr.blixabargeld.myList.dao.ArtistActivityItemDao;
import gr.blixabargeld.myList.model.Artist;
import gr.blixabargeld.myList.model.ArtistActivityItem;
import gr.blixabargeld.myList.model.Item;
import gr.blixabargeld.myList.service.ArtistActivityItemService;

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
