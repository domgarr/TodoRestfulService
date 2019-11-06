package com.example.todorestful.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.todorestful.model.Task;

public interface TodoDAO {
	final static String TABLE_NAME = "todos";
	
	Task save(Task todo);
	Optional<Task> findById(Long id);
	List<Task> findAll();
	long count();
	void delete(Long id);
	boolean existsById(Long id);
	void updateDescription(Task todo);
	void updateIsDone(Task todo);
	List<Task> findAllByListId(Long id);
}
