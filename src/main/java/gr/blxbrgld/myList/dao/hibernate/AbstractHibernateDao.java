package gr.blxbrgld.mylist.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.NullPrecedence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

/**
 * Generic Class's DAO Implementation
 * @param <T> Generic Class
 * @author blxbrgld
 */
@Slf4j
public abstract class AbstractHibernateDao<T extends Object> implements AbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> domainClass;
	
	/**
	 * Protected Method Allowing Subclasses To Perform Persistent Operations Against The Hibernate Session
	 * @return Hibernate Session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Reflection To Get Actual Domain Class
	 * @return Domain Class
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass() {
		if(domainClass==null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}
	
	/**
	 * Reflection To Get Actual Domain Class Name
	 * @return Domain Class Name
	 */
	private String getDomainClassName() {
		return getDomainClass().getName();
	}
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public void persist(T t) {
		@SuppressWarnings("rawtypes")
        Method method = ReflectionUtils.findMethod(getDomainClass(), "setDateUpdated", new Class[] { Calendar.class });
		if(method!=null) {
			try {
				method.invoke(t, Calendar.getInstance());
			} catch(Exception exception) {
				log.error("Exception", exception);
			}
		}
		getSession().persist(t);
		getSession().flush(); //Flush To Get Id Of Object Persisted
	}
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public void merge(T t) {
        @SuppressWarnings("rawtypes")
		Method method = ReflectionUtils.findMethod(getDomainClass(), "setDateUpdated", new Class[] { Calendar.class });
		if(method!=null) {
			try {
				method.invoke(t, Calendar.getInstance());
			} catch(Exception exception) {
				log.error("Exception", exception);
			}
		}		
		
		getSession().merge(t);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(getDomainClass(), id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getSession().load(getDomainClass(), id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll(String property, String order) {
		Criteria criteria = getSession().createCriteria(getDomainClass());
		if(order!=null && "DESC".equals(order)) {
			criteria.addOrder(Order.desc(property).nulls(NullPrecedence.FIRST));
		} else if(property!=null) {
			criteria.addOrder(Order.asc(property).nulls(NullPrecedence.FIRST));
		}
		return (List<T>) criteria.list();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll(String property, String order, int first, int size) {
		Criteria criteria = getSession().createCriteria(getDomainClass());
		if(order!=null && "DESC".equals(order)) {
			criteria.addOrder(Order.desc(property).nulls(NullPrecedence.FIRST));
		} else if(property!=null) {
			criteria.addOrder(Order.asc(property).nulls(NullPrecedence.FIRST));
		}
		criteria.setFirstResult(first);
		criteria.setMaxResults(size);
		return (List<T>) criteria.list();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public void delete(T t) {
		getSession().delete(t);
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public void deleteById(Serializable id) {
		delete(load(id));
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Long count() {
		return (Long) getSession().createQuery("SELECT COUNT(*) FROM " + getDomainClassName()).uniqueResult();
	}
}