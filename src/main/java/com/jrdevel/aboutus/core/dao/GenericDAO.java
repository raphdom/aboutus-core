package com.jrdevel.aboutus.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.jrdevel.aboutus.core.util.ListParams;
import com.jrdevel.aboutus.core.util.ListResult;

/**
 * @author Raphael Domingues
 *
 */
public interface GenericDAO<T, PK extends Serializable>{
	
	public T findUniqueByCriteria(Criterion... criterion);
	public List<T> findByCriteria(Criterion... criterion);
	public T findById(PK id, boolean lock);
	public <R> ListResult<R> findAllByView(ListParams params, Class<R> view);
	public ListResult<T> findAllByCriteria(ListParams params);
	public T makePersistent(T entity);
	public T makeTransient(T entity);

}
