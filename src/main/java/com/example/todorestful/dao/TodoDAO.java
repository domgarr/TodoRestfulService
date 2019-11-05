package com.example.todorestful.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.todorestful.model.Task;

public interface TodoDAO {
	final static String TABLE_NAME = "todos";
	
	Task save(Task todo);
	Optional<Task> findById(Integer id);
	List<Task> findAll();
	long count();
	void delete(Task todo);
	boolean existsById(Integer id);
	void updateDescription(Task todo);
	void updateIsDone(Task todo);
	List<Task> findAllByListId(Integer id);
}
