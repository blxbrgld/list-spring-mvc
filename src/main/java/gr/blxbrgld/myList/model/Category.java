package gr.blxbrgld.mylist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
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
 * Category Java Bean
 * @author blxbrgld
 */
@Getter
@Setter
@Entity
@Table(name = "Categories")
@NamedQueries({
	@NamedQuery(name = "findCategoryByTitle", query = "FROM Category WHERE title = :title"),
	@NamedQuery(name = "findCategoriesByParent", query = "FROM Category WHERE parent = :parent")
})
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 3, max = 45)
	@Column(name = "Title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "Parent", referencedColumnName = "Id")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> categories;
	
	@OneToMany(mappedBy = "category")
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
			.append("parent", parent)
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
		Category rhs = (Category) obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(title, rhs.title)
			.append(parent, rhs.parent)
			.isEquals();
	}

	/**
	 * Override The Default hashCode() Method
	 * @return Object's Hash Code
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(23, 29)
			.appendSuper(super.hashCode())
			.append(title)
			.append(parent)
			.toHashCode();
	}
}