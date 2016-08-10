package gr.blxbrgld.mylist.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CommentItem Java Bean
 * @author blxbrgld
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
public class CommentItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "IdItem", referencedColumnName = "Id", nullable = false)
	private Item idItem;
	
	@ManyToOne
	@JoinColumn(name = "IdComment", referencedColumnName = "Id", nullable = false)
	private Comment idComment;
	
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
	
	/**
	 * @return String Representation Of CommentItem Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("idItem", idItem)
            .append("idComment", idComment)
            .toString();
	}
}