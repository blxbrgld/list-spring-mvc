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
 * Comment Java Bean
 * @author blxbrgld
 */
@NamedQuery(
		name = "findCommentByTitle",
		query = "FROM Comment WHERE title = :title")
@Entity
@Table(name = "Comments")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 1, max = 45)
	@Column(name = "Title")
	private String title;
	
	@OneToMany(mappedBy = "idComment")
	private List<CommentItem> commentItems;

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
	
	/**
	 * @return String Representation Of Comment Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("title", title)
            .toString();
	}
}