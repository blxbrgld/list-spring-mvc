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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

/**
 * Category Java Bean
 * @author blxbrgld
 */
@NamedQueries({
	@NamedQuery(
			name = "findCategoryByTitle",
			query = "FROM Category WHERE title = :title"),			
	@NamedQuery(
			name = "findCategoriesByParent",
			query = "FROM Category WHERE parent = :parent")
})
@Entity
@Table(name = "Categories")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 3, max = 45)
	@Column(name = "Title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "Parent", referencedColumnName = "Id", nullable = true)
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> categories;
	
	@OneToMany(mappedBy = "category")
	private List<Item> items;

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Category getParent() {
		return parent;
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	/**
	 * @return String Representation Of Category Object 
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("title", title)
            .append("parent", parent)
            .toString();
	}
}