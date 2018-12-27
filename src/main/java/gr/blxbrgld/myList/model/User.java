package gr.blxbrgld.mylist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * User Java Bean
 * @author blxbrgld
 */
@Getter
@Setter
@Entity
@Table(name = "Users")
@NamedQueries({
	@NamedQuery(name = "findUserByUsername", query = "FROM User WHERE username = :username"),
	@NamedQuery(name = "findUserByEmail", query = "FROM User WHERE email = :email"),
	@NamedQuery(name = "findUsersByRole", query = "FROM User WHERE role = :role")
})
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@NotNull
	@Length(min = 3, max = 45)
	@Column(name = "Username")
	private String username;

	@Email
	@NotNull
	@Length(min = 3, max = 45)
	@Column(name = "Email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "Role", referencedColumnName = "Id", nullable = false)
	private Role role;

	@NotNull
	@Column(name = "Enabled")
	private boolean enabled = true;

	/**
	 * Override The Default toString() Method
	 * @return Object's String Representation
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.appendSuper(super.toString())
			.append("username", username)
			.append("email", email)
			.append("role", role)
			.append("enabled", enabled)
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
		User rhs = (User) obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(username, rhs.username)
			.append(email, rhs.email)
			.append(role, rhs.role)
			.append(enabled, rhs.enabled)
			.isEquals();
	}

	/**
	 * Override The Default hashCode() Method
	 * @return Object's Hash Code
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(67, 71)
			.appendSuper(super.hashCode())
			.append(username)
			.append(email)
			.append(role)
			.append(enabled)
			.toHashCode();
	}
}