package gr.blxbrgld.myList.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Category Java Bean
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
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
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
	
	public Calendar getDateUpdated() {
		return dateUpdated;
	}
	
	public void setDateUpdated(Calendar dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	/**
	 * @return String Representation Of Category Object 
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("title", title)
				.append("parent", parent)
				.toString();
	}
}