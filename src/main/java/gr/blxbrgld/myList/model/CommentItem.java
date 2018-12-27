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

/**
 * CommentItem Java Bean
 * @author blxbrgld
 */
@Getter
@Setter
@Entity
@Table(name = "CommentsItems")
@NamedQueries({
	@NamedQuery(name = "findCommentItemsByComment", query = "FROM CommentItem WHERE idComment = :comment"),
	@NamedQuery(name = "deleteCommentItemByItem", query = "DELETE FROM CommentItem WHERE idItem = :item")
})
public class CommentItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "IdItem", referencedColumnName = "Id", nullable = false)
	private Item idItem;
	
	@ManyToOne
	@JoinColumn(name = "IdComment", referencedColumnName = "Id", nullable = false)
	private Comment idComment;

	/**
	 * Override The Default toString() Method
	 * @return Object's String Representation
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.appendSuper(super.toString())
			.append("idItem", idItem)
			.append("idComment", idComment)
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
		CommentItem rhs = (CommentItem) obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(idItem, rhs.idItem)
			.append(idComment, rhs.idComment)
			.isEquals();
	}

	/**
	 * Override The Default hashCode() Method
	 * @return Object's Hash Code
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(41, 43)
			.appendSuper(super.hashCode())
			.append(idItem)
			.append(idComment)
			.toHashCode();
	}
}