package gr.blixabargeld.myList.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Role Java Bean
 */
@NamedQuery(
		name = "findRoleByTitle",
		query = "FROM Role WHERE title = :title")
@Entity
@Table(name = "Roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@NotNull
	@Length(min = 3, max = 15)
	@Column(name = "Title")
	private String title;
	
	@OneToMany(mappedBy = "role")
	private List<User> users;
	
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

	public String getTitle() {
		
		return title;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public List<User> getUsers() {
	
		return users;
	}
	
	public void setUsers(List<User> users) {
		
		this.users = users;
	}
	
	public Calendar getDateUpdated() {
		
		return dateUpdated;
	}
	
	public void setDateUpdated(Calendar dateUpdated) {
		
		this.dateUpdated = dateUpdated;
	}

	/**
	 * @return String Representation Of Role Object
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("title", title)
				.toString();
	}
}