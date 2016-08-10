package gr.blxbrgld.mylist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

/**
 * Activity Java Bean
 * @author blxbrgld
 */
@NamedQuery(
		name = "findActivityByTitle",
		query = "FROM Activity WHERE title = :title")
@Entity
@Table(name = "Activities")
public class Activity extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 3, max = 45)
	@Column(name = "Title")
	private String title;
	
	@OneToMany(mappedBy = "idActivity")
	private List<ArtistActivityItem> artistActivityItems;

	public String getTitle() {
		return title;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<ArtistActivityItem> getArtistActivityItems() {
		return artistActivityItems;
	}
	
	public void setArtistActivityItems(List<ArtistActivityItem> artistActivityItems) {
		this.artistActivityItems = artistActivityItems;
	}

	/**
	 * @return String Representation Of Activity Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("title", title)
            .toString();
	}
}