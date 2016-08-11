package gr.blxbrgld.mylist.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Authentication Implementing Spring's UserDetails Interface
 * @author blxbrgld
 */
public class UserDetailsAdapter implements UserDetails {

    private static final long serialVersionUID = 1L;

	private User user;
	private String password;
	
	public UserDetailsAdapter(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public Long getId() {
		return user.getId();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	public Calendar getDateUpdated() {
		return user.getDateUpdated();
	}
	
	public Role getRole() {
		return user.getRole();
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getTitle()));
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}