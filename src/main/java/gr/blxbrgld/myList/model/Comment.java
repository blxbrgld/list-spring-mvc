package gr.blxbrgld.myList.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * Comment Java Bean
 */
@NamedQuery(
		name = "findCommentByTitle",
		query = "FROM Comment WHERE title = :title")
@Entity
@Table(name = "Comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@NotNull
	@Length(min = 1, max = 45)
	@Column(name = "Title")
	private String title;
	
	@OneToMany(mappedBy = "idComment")
	private List<CommentItem> commentItems;
	
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
	
	public List<CommentItem> getCommentItems() {
		return commentItems;
	}
	
	public void setCommentItems(List<CommentItem> commentItems) {
		this.commentItems = commentItems;
	}
	
	public Calendar getDateUpdated() {
		return dateUpdated;
	}	
	
	public void setDateUpdated(Calendar dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	/**
	 * @return String Representation Of Comment Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("title", title)
				.toString();
	}
}