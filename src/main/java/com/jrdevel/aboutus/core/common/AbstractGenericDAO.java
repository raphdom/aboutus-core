package com.jrdevel.aboutus.core.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.jrdevel.aboutus.core.authentication.UserAuthenticatedManager;
import com.jrdevel.aboutus.core.common.model.Audit;
import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.to.Filter;
import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;
import com.jrdevel.aboutus.core.common.to.Sort;
import com.jrdevel.aboutus.core.common.view.Projection;


/**
 * @author Raphael Domingues
 *
 */
public abstract class AbstractGenericDAO<T, PK extends Serializable> implements GenericDAO<T, PK>{

	private Class<T> persistentClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractGenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	//@Autowired
	//private User userSession;

	public Session getSession(){
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueByCriteria(Criterion... criterion){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return (T) crit.uniqueResult();
	}
	
	public <R> Object findUniqueByView (PK key, Class<R> view){
		
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.setProjection(getProjectionList(view));
		criteria.setResultTransformer(Transformers.aliasToBean(view));
		
		return criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public ListResult<T> findAllByCriteria(ListParams params){

		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (this.getPersistentClass() != Permission.class){
			criteria.add(Restrictions.eq("customer.id", UserAuthenticatedManager.getCurrentCustomer().getId()));
		}
		setOrder(criteria,params.getSorters());
		setFilters(criteria, params.getFilter());
		setExtraFilters(criteria);
		int count = setPagingInfo(criteria);
		criteria.setFirstResult(params.getStart());
		criteria.setMaxResults(params.getLimit());
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		return new ListResult<T>(criteria.list(), count);
	}
	
	@SuppressWarnings("unchecked")
	public <R> ListResult<R> findAllByView(ListParams params, Class<R> view){
		
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (this.getPersistentClass() != Permission.class){
			criteria.add(Restrictions.eq("customer.id", UserAuthenticatedManager.getCurrentCustomer().getId()));
		}
		setOrder(criteria,params.getSorters());
		setFilters(criteria, params.getFilter());
		setExtraFilters(criteria);
		int count = setPagingInfo(criteria);
		criteria.setFirstResult(params.getStart());
		criteria.setMaxResults(params.getLimit());
		criteria.setProjection(null);
		
		criteria.setProjection(getProjectionList(view));
		
		criteria.setResultTransformer(Transformers.aliasToBean(view));
		
		return new  ListResult<R>(criteria.list(),count);
	}
	
	private ProjectionList getProjectionList(Class<?> viewClass){
		ProjectionList result = Projections.projectionList();
		
		BeanInfo info;
		try {
			info = Introspector.getBeanInfo(viewClass, Object.class);
			PropertyDescriptor[] props = info.getPropertyDescriptors();  
		    for (PropertyDescriptor pd : props) {  
		        Method getter = pd.getReadMethod();  
		        if (getter.isAnnotationPresent(Projection.class)){
		        	String name = pd.getName();
		        	String entityName = getter.getAnnotation(Projection.class).entityName();
		        	if (entityName != null && entityName.trim().equals("")){
		        		entityName = name;
		        	}
		        	result.add(Projections.property(entityName), name);
		        }
		    }
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}  
		
		return result;
	}
	
	private void setOrder(Criteria criteria, List<Sort> sortInfo){
		if (sortInfo!=null && !sortInfo.isEmpty()){
			Sort sort = sortInfo.get(0);
			String sortField = sort.getProperty();
			Order order = null;
			if (sort.getDirection().equals("ASC")){
				order = Order.asc(sortField);
			}else if (sortField != null){
				order = Order.desc(sortField);

			}
			if (order != null){
				criteria.addOrder(order);
			}
		}
	}
	
	private void setFilters(Criteria criteria, List<Filter> filters){
    	if (filters == null){
    		return;
    	}
    	for(Filter filter: filters){
    		if (filter.getType().equals("id") && filter.getOperator().equals("eq")){
    			criteria.add(Restrictions.eq(filter.getProperty(), Integer.parseInt(filter.getValue())));
    		}else if(filter.getType().equals("textfield")){
    			criteria.add(Restrictions.like(filter.getProperty(), "%"+filter.getValue()+"%"));
    		}
    	}
    }
	
	private int setPagingInfo(Criteria criteria){
    	criteria.setProjection(Projections.rowCount()); 
    	Object result = criteria.list().get(0);
    	if (result instanceof Integer){
    		return (Integer) result;
    	}else{
    		return 0;
    	}
    }
	
	@SuppressWarnings("unchecked")
	public T findById(PK id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (T) getSession().get(getPersistentClass(), id);

		return entity;
	}
	
	public T makePersistent(T entity) {
    	return makePersistent(entity, false, true);
    }
	
    public T makePersistent(T entity, boolean audit, boolean customer) {
    	int mode = 0;
    	if (audit){
	    	ClassMetadata hibernateMetadata = getSession().getSessionFactory().getClassMetadata(getPersistentClass());
	        mode = 1;
	        if (hibernateMetadata.getIdentifier(entity, EntityMode.POJO) == null ||
	        		hibernateMetadata.getIdentifier(entity, EntityMode.POJO).hashCode()==0){
	        	mode = 0;
	        }
    	}
        getSession().saveOrUpdate(entity);
        if (audit){
        	audit(entity, mode);
        }
        return entity;
    }
    
    public T makeTransient(T entity) {
    	return makeTransient(entity, false);
    }
    
    public T makeTransient(T entity, boolean audit) {
    	int mode = 2;
        if (audit){
        	audit(entity, mode);
        }
        getSession().delete(entity);
        return entity;
    }
    
    public void audit(T entity, int mode){
        //Audit
        ClassMetadata hibernateMetadata = getSession().getSessionFactory().getClassMetadata(getPersistentClass());
        if (hibernateMetadata == null){
            return;
        }
        AbstractEntityPersister persister = (AbstractEntityPersister) hibernateMetadata;
        
        String tableName = persister.getTableName();
        Integer tableId = hibernateMetadata.getIdentifier(entity, EntityMode.POJO).hashCode();
        
		Audit audit = new Audit();
		audit.setUserId(1);
		audit.setTableId(tableId);
		audit.setTableName(tableName);
		audit.setActionId(mode);
		audit.setUserName(UserAuthenticatedManager.getCurrentUser().getPerson().getName());
//		audit.setObjectName(getObjectName());
//		audit.setObjectTitle(getObjectTitle(entity));
		
		audit.setActionDate(new Date());
		
		getSession().saveOrUpdate(audit);
		
    }


	//Abstract methods

	public abstract void setExtraFilters(Criteria criteria);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
