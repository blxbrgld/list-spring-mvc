package gr.blixabargeld.myList.web;

import gr.blixabargeld.myList.model.Role;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * UserForm Object For User's Controller
 */
@ScriptAssert(
		lang = "javascript",
		script = "_this.confirmPassword.equals(_this.password)",
		message = "user.password.mismatch.message"
		)
public class UserForm {

	private Long id; //No Need For Annotations. It Will Be Used Only As A Hidden Field
		
	@NotNull
	@Length(min = 3, max = 45)
	private String username;
	
	@NotNull
	@Length(min = 3, max = 45)
	private String password;
	
	@NotNull
	@Length(min = 3, max = 45)
	private String confirmPassword;
	
	@Email
	@NotNull
	@Length(min = 3, max = 45)
	private String email;
	
	@NotNull
	private Role role;
	
	public Long getId() {
		
		return id;
	}
	
	public void setId(Long id) {
		
		this.id = id;
	}

	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}

	public String getConfirmPassword() {
		
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}

	public Role getRole() {
		
		return role;
	}
	
	public void setRole(Role role) {
		
		this.role = role;
	}
	
	/**
	 * @return String Representation Of UserForm Object
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("username", username)
				.append("email", email)
				.append("role", role)
				.toString();
	}
}