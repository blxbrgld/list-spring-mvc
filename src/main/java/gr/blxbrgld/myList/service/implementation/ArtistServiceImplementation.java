package gr.blxbrgld.myList.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blxbrgld.myList.dao.ArtistActivityItemDao;
import gr.blxbrgld.myList.dao.ArtistDao;
import gr.blxbrgld.myList.model.Artist;
import gr.blxbrgld.myList.service.ArtistService;

/**
 * Artist's Service Implementation
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class ArtistServiceImplementation implements ArtistService {

	@Inject private ArtistDao artistDao;
	@Inject private ArtistActivityItemDao artistActivityItemDao;
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistArtist(Artist artist, Errors errors) {
		validateTitle(artist, errors);
		boolean valid = !errors.hasErrors();
		if(valid) artistDao.persist(artist);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistArtist(Artist artist) {
		artistDao.persist(artist);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeArtist(Artist artist, Errors errors) {
		validateTitle(artist, errors);
		boolean valid = !errors.hasErrors();
		if(valid) artistDao.merge(artist);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Artist> getArtists(String property, String order) {
		return artistDao.getAll(property, order);
	}

	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Artist> getArtists(String property, String order, int first, int size) {
		return artistDao.getAll(property, order, first, size);
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Artist getArtist(Long id) {
		return artistDao.get(id);
	}

	@Override
	@PreAuthorize("permitAll")
	public Artist getArtist(String title) {
		return artistDao.findByTitle(title);
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public boolean deleteArtist(Long id) {
		if(!artistActivityItemDao.havingArtistExists(artistDao.get(id))) { //No ArtistActivityItems With This Artist Exist
			artistDao.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Long countArtists() {
		return artistDao.count();
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Artist findLastArtist() {
		return artistDao.findLast();
	}
	
	@Override
	@PreAuthorize("permitAll")
	public Artist findRandomArtist() {
		return artistDao.findRandom();
	}
	
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<String> findArtistsLike(String term) {
		List<Artist> artistList = artistDao.findLike(term);
		List<String> titleList = new ArrayList<String>();
		for(Artist artist : artistList) {
			titleList.add(artist.getTitle());
		}
		return titleList;
	}	
	
	/**
	 * Validate Uniqueness Of Artist's Title
	 * @param artist Artist Object
	 * @param errors BindingResult Errors Of Artist Form
	 */
	private void validateTitle(Artist artist, Errors errors) {
		Artist existing = artistDao.findByTitle(artist.getTitle());
		if(existing != null && !existing.getId().equals(artist.getId()) ) {
			errors.rejectValue("title", "error.duplicate");
		}
	}
}