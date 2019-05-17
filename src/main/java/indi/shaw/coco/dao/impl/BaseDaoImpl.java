package indi.shaw.coco.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.BaseDao;

@Repository("baseDao")
public abstract class BaseDaoImpl<T extends Serializable> implements BaseDao<T, Long> {

	private Class<T> persistentClass;

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * 事务必须是开启的(Required)，否则获取不到
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		this.persistentClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.persistentClass = (Class<T>) p[0];
		}
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		T entity = null;
		if (null != id)
			entity = (T) getSession().get(getPersistentClass(), id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		T entity;
		entity = (T) getSession().load(getPersistentClass(), id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id, LockOptions.READ);
		else
			entity = (T) getSession().load(getPersistentClass(), id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getSession().createQuery("FROM " + persistentClass.getSimpleName()).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public Long create(T entity) {
		return (Long) getSession().save(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public T findByQuery(String hql, Object... param) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < param.length; i++)
			query.setParameter(i, param[i]);
		List<T> list = query.list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public T findBySQLQuery(String sql, Map<String, Object> param, Map<String, Class<?>> entity) {
		SQLQuery query = getSession().createSQLQuery(sql);
		Iterator<String> iter = param.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			query.setParameter(key, param.get(key));
		}
		Iterator<String> iter2 = entity.keySet().iterator();
		while (iter2.hasNext()) {
			String key = iter2.next();
			query.addEntity(key, (Class<?>) entity.get(key));
		}
		List<T> list = query.list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByQuery(String sql, Map<String, Object> param) {
		Query query = getSession().createQuery(sql);
		if (null == param)
			return query.list();
		Iterator<String> iter = param.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			query.setParameter(key, param.get(key));
		}
		return query.list();
	}

	public List<T> listByQuery(String sql) {
		return listByQuery(sql, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> listBySQLQuery(String sql, Map<String, Object> param, Map<String, Class<?>> entity) {
		SQLQuery query = getSession().createSQLQuery(sql);
		Iterator<String> iter = param.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			query.setParameter(key, param.get(key));
		}
		Iterator<String> iter2 = entity.keySet().iterator();
		while (iter2.hasNext()) {
			String key = iter2.next();
			query.addEntity(key, (Class<?>) entity.get(key));
		}
		return query.list();
	}

}
