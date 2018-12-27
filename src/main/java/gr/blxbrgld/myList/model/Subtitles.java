package gr.blxbrgld.mylist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

/**
 * Subtitles Java Bean
 * @author blxbrgld
 */
@Getter
@Setter
@Entity
@Table(name = "Subtitles")
@NamedQuery(name = "findSubtitlesByTitle", query = "FROM Subtitles WHERE title = :title")
public class Subtitles extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 3, max = 45)
	@Column(name = "Title")
	private String title;
	
	@OneToMany(mappedBy = "subtitles")
	private List<Item> items;

	/**
	 * Override The Default toString() Method
	 * @return Object's String Representation
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.appendSuper(super.toString())
			.append("title", title)
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
		Subtitles rhs = (Subtitles) obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(title, rhs.title)
			.isEquals();
	}

	/**
	 * Override The Default hashCode() Method
	 * @return Object's Hash Code
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(59, 61)
			.appendSuper(super.hashCode())
			.append(title)
			.toHashCode();
	}
}
