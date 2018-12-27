package gr.blxbrgld.mylist.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * BaseEntity Java Bean
 * @author blxbrgld
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DateUpdated")
    private Calendar dateUpdated;

    /**
     * Override The Default toString() Method
     * @return Object's String Representation
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
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
        BaseEntity rhs = (BaseEntity) obj;
        return new EqualsBuilder()
            .append(id, rhs.id)
            .isEquals();
    }

    /**
     * Override The Default hashCode() Method
     * @return Object's Hash Code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 5)
            .append(id)
            .toHashCode();
    }
}
