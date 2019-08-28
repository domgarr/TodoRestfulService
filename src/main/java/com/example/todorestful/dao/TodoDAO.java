package com.example.todorestful.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.todorestful.model.Todo;

public interface TodoDAO {
	final static String TABLE_NAME = "todos";
	
	Todo save(Todo todo);
	Optional<Todo> findById(Integer id);
	List<Todo> findAll();
	long count();
	void delete(Todo todo);
	boolean existsById(Integer id);
	void updateDescription(Todo todo);
	void updateIsDone(Todo todo);
	List<Todo> findAllByListId(Integer id);
}
