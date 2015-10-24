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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * CommentItem Java Bean
 */
@NamedQueries({
	@NamedQuery(
			name = "findCommentItemsByComment",
			query = "FROM CommentItem WHERE idComment = :comment"),
	@NamedQuery(
			name = "deleteCommentItemByItem",
			query = "DELETE FROM CommentItem WHERE idItem = :item")
})
@Entity
@Table(name = "CommentsItems")
public class CommentItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "IdItem", referencedColumnName = "Id", nullable = false)
	private Item idItem;
	
	@ManyToOne
	@JoinColumn(name = "IdComment", referencedColumnName = "Id", nullable = false)
	private Comment idComment;
	
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
	
	public Item getIdItem() {
		return idItem;
	}
	
	public void setIdItem(Item idItem) {
		this.idItem = idItem;
	}
	
	public Comment getIdComment() {
		return idComment;
	}
	
	public void setIdComment(Comment idComment) {
		this.idComment = idComment;
	}
	
	public Calendar getDateUpdated() {
		return dateUpdated;
	}
	
	public void setDateUpdated(Calendar dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	/**
	 * @return String Representation Of CommentItem Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("idItem", idItem)
				.append("idComment", idComment)
				.toString();
	}
}