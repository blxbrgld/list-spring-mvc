package gr.blxbrgld.mylist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * User Java Bean
 * @author blxbrgld
 */
@NamedQueries({
	@NamedQuery(
			name = "findUserByUsername",
			query = "FROM User WHERE username = :username"),
	@NamedQuery(
			name = "findUserByEmail",
			query = "FROM User WHERE email = :email"),
	@NamedQuery(
			name = "findUsersByRole",
			query = "FROM User WHERE role = :role")
})
@Entity
@Table(name = "Users")
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return String Representation Of User Object
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
}