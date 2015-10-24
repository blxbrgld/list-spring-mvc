package gr.blxbrgld.myList.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * ArtistActivityItem Java Bean
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
public class ArtistActivityItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
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

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "DateUpdated")
	private Calendar dateUpdated;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public Calendar getDateUpdated() {
		return dateUpdated;
	}
	
	public void setDateUpdated(Calendar dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	/**
	 * @return String Representation Of ArtistActivityItem Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("idArtist", idArtist)
				.append("idItem", idItem)
				.append("idActivity", idActivity)
				.toString();
	}
}
