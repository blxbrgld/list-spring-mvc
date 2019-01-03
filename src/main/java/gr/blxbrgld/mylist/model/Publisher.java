package gr.blxbrgld.mylist.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Publisher Domain Object
 * @author blxbrgld
 */
@Getter
@Setter
@Entity
@Table(name = "Publishers")
@NamedQuery(name = "findPublisherByTitle", query = "FROM Publisher WHERE title = :title")
public class Publisher extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Length(min = 3, max = 100)
    @Column(name = "Title")
    private String title;

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
        Publisher rhs = (Publisher) obj;
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
        return new HashCodeBuilder(3, 29)
            .appendSuper(super.hashCode())
            .append(title)
            .toHashCode();
    }
}
