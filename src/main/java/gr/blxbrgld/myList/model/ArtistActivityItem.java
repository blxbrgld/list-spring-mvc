package gr.blxbrgld.mylist.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * ArtistActivityItem Java Bean
 * @author blxbrgld
 */
@Getter
@Setter
@Entity
@Table(name = "ArtistsActivitiesItems")
@NamedQueries({
	@NamedQuery(name = "findArtistActivityItemsByArtist", query = "FROM ArtistActivityItem WHERE idArtist = :artist"),
	@NamedQuery(name = "findArtistActivityItemsByActivity", query = "FROM ArtistActivityItem WHERE idActivity = :activity"),
	@NamedQuery(name = "deleteArtistActivityItemByItem", query = "DELETE FROM ArtistActivityItem WHERE idItem = :item"),
	@NamedQuery(name = "countItemsHavingArtist", query = "SELECT COUNT(*) FROM ArtistActivityItem WHERE idArtist = :artist"),
})
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

	/**
	 * Override The Default toString() Method
	 * @return Object's String Representation
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

	/**
	 * Override The Default equals() Method
	 * @return TRUE If It's Equal, Else FALSE
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) { return false; }
		if(obj==this) { return true; }
		if(obj.getClass()!=getClass()) {
			return false;
		}
		ArtistActivityItem rhs = (ArtistActivityItem) obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(idArtist, rhs.idArtist)
			.append(idItem, rhs.idItem)
			.append(idActivity, rhs.idActivity)
			.isEquals();
	}

	/**
	 * Override The Default hashCode() Method
	 * @return Object's Hash Code
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 19)
			.appendSuper(super.hashCode())
			.append(idArtist)
			.append(idItem)
			.append(idActivity)
			.toHashCode();
	}
}
