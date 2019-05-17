package indi.shaw.coco.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface BaseDao<T extends Serializable, PK extends Serializable> {

	Session getSession();

	T findById(PK id);

	T findById(PK id, boolean lock);

	T get(PK id);

	List<T> findAll();

	List<T> findByExample(T exampleInstance, String... excludeProperty);

	T findByQuery(String sql, Object... param);

	T findBySQLQuery(String sql, Map<String, Object> param, Map<String, Class<?>> entity);

	List<T> listByQuery(String sql, Map<String, Object> param);

	List<T> listByQuery(String sql);

	List<T> listBySQLQuery(String sql, Map<String, Object> param, Map<String, Class<?>> entity);

	PK create(T entity);

	void update(T entity);

	void delete(T entity);
}
