package gr.blixabargeld.myList.dao.hibernate;

import java.util.List;

import javax.inject.Inject;

import gr.blixabargeld.myList.dao.UserDao;
import gr.blixabargeld.myList.model.Role;
import gr.blixabargeld.myList.model.User;

import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.sql.JoinType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * User's DAO Implementation
 */
@Repository
public class HibernateUserDao extends AbstractHibernateDao<User> implements UserDao {

	private static final String UPDATE_PASSWORD_QUERY = "UPDATE Users SET Password = ? WHERE Username = ?";
	
	@Inject private JdbcTemplate jdbcTemplate;
	@Inject private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Override AbstractHibernateDao Method To Include Sorting Functionality According To User's Role Title
	 * @param property Property To Order Results By
	 * @param order Ascending or Descending Ordering
	 * @return List Of Users
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll(String property, String order) {
	
		Criteria criteria = getSession().createCriteria(User.class).createAlias("role", "role", JoinType.LEFT_OUTER_JOIN);
		
		if(order!=null && order.equals("DESC")) {
			
			criteria.addOrder(Order.desc(property).nulls(NullPrecedence.FIRST));
		}
		else if(property!=null) {
			
			criteria.addOrder(Order.asc(property).nulls(NullPrecedence.FIRST));
		}
		
		return (List<User>) criteria.list();
	}

	@Override
	public void persist(User user, String password) {
		
		persist(user); //With Hibernate
		
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		jdbcTemplate.update(UPDATE_PASSWORD_QUERY, encodedPassword, user.getUsername()); //With JDBC
	}
	
	@Override
	public void merge(User user, String password) {
		
		merge(user); //With Hibernate
		
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		jdbcTemplate.update(UPDATE_PASSWORD_QUERY, encodedPassword, user.getUsername()); //With JDBC
	}
	
	@Override
	public User findByUsername(String username) {
		
		Query query = getSession().getNamedQuery("findUserByUsername");
		query.setParameter("username", username);
		return (User) query.uniqueResult();
	}
	
	@Override
	public User findByEmail(String email) {
		
		Query query = getSession().getNamedQuery("findUserByEmail");
		query.setParameter("email", email);
		return (User) query.uniqueResult();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByRole(Role role) {
		
		Query query = getSession().getNamedQuery("findUsersByRole");
		query.setParameter("role", role);
		return (List<User>) query.list();
	}

	@Override
	public boolean havingRoleExists(Role role) {
		
		Query query = getSession().getNamedQuery("findUsersByRole");
		query.setParameter("role", role);
		query.setMaxResults(1);
		if(query.list().isEmpty()) return false; else return true;
	}
}