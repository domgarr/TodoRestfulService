package com.example.todorestful.dao;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
	T save(T entity);
	Optional<T> findById(Integer id);
	List<T> findAll();
	long count();
	void delete(T entity);
	boolean existsById(Integer id);
	void updateDescription(T entity);
	void updateIsDone(T entity);
}
