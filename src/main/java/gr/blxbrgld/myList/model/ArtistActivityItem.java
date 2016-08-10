package gr.blxbrgld.mylist.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * ArtistActivityItem Java Bean
 * @author blxbrgld
 */
@NamedQueries({
	@NamedQuery(
			name = "findArtistActivityItemsByArtist",
			query = "FROM ArtistActivityItem WHERE idArtist = :artist"),
	@NamedQuery(
			name = "findArtistActivityItemsByActivity",
			query = "FROM ArtistActivityItem WHERE idActivity = :activity"),
	@NamedQuery(
			name = "deleteArtistActivityItemByItem",
			query = "DELETE FROM ArtistActivityItem WHERE idItem = :item"),
	@NamedQuery(
			name = "countItemsHavingArtist",
			query = "SELECT COUNT(*) FROM ArtistActivityItem WHERE idArtist = :artist"),
})
@Entity
@Table(name = "ArtistsActivitiesItems")
public class ArtistActivityItem extends BaseEntity {

    private static final long serialVersionUID = 1L;
	
	@IndexedEmbedded
	@ManyToOne
	@JoinColumn(name = "IdArtist", referencedColumnName = "Id", nullable = false)
	private Artist idArtist;
	
	@ContainedIn
	@ManyToOne
	@JoinColumn(name = "IdItem", referencedColumnName = "Id", nullable = false)
	private Item idItem;
	
	@ManyToOne
	@JoinColumn(name = "IdActivity", referencedColumnName = "Id", nullable = false)
	private Activity idActivity;

	public Artist getIdArtist() {
		return idArtist;
	}
	
	public void setIdArtist(Artist idArtist) {
		this.idArtist = idArtist;
	}
	
	public Item getIdItem() {
		return idItem;
	}
	
	public void setIdItem(Item idItem) {
		this.idItem = idItem;
	}
	
	public Activity getIdActivity() {
		return idActivity;
	}
	
	public void setIdActivity(Activity idActivity) {
		this.idActivity = idActivity;
	}
	
	/**
	 * @return String Representation Of ArtistActivityItem Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("idArtist", idArtist)
            .append("idItem", idItem)
            .append("idActivity", idActivity)
            .toString();
	}
}
