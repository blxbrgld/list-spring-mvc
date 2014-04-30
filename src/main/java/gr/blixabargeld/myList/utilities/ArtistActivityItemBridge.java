package gr.blixabargeld.myList.utilities;

import gr.blixabargeld.myList.model.ArtistActivityItem;

import java.util.List;

import org.hibernate.search.bridge.StringBridge;

/**
 * Map First Non 'Actor' Artist Of ArtistActivityItem Object To A Single Lucene Index Field
 */
public class ArtistActivityItemBridge implements StringBridge {

	@SuppressWarnings("unchecked")
	@Override
	public String objectToString(Object object) {

		String firstArtist = null;
		
		List<ArtistActivityItem> artistActivityItems = (List<ArtistActivityItem>) object;
			
		for(int i = 0; i < artistActivityItems.size(); i++) {

			if(artistActivityItems.get(i).getIdActivity()!=null && artistActivityItems.get(i).getIdArtist()!=null && !artistActivityItems.get(i).getIdActivity().getTitle().equals("Actor")) {

				firstArtist = artistActivityItems.get(i).getIdArtist().getTitle();
				break; //Just The First One Will Do The Work
			}
		}

		return firstArtist;
	}
}
