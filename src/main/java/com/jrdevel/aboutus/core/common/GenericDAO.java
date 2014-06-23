package com.jrdevel.aboutus.core.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.jrdevel.aboutus.core.common.to.ListParams;
import com.jrdevel.aboutus.core.common.to.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface GenericDAO<T, PK extends Serializable>{
	
	public T findUniqueByCriteria(Criterion... criterion);
	public <R> R findUniqueByViewAndId (PK key, Class<R> view);
	public List<T> findByCriteria(Criterion... criterion);
	public T findById(PK id, boolean lock);
	public <R> ListResult<R> findAllByView(Class<R> view);
	public <R> ListResult<R> findAllByView(ListParams params, Class<R> view);
	public ListResult<T> findAllByCriteria(ListParams params);
	public T makePersistent(T entity) throws PlanExceededException;
	public T makePersistent(T entity, boolean audit, boolean customer) throws PlanExceededException;
	public T makeTransient(T entity);

}
