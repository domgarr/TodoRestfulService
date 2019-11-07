package com.example.todorestful.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.todorestful.model.Task;

public interface TaskDAO {
	final static String TABLE_NAME = "task";
	
	Task save(Task task);
	Optional<Task> findById(Long id);
	List<Task> findAll();
	long count();
	void delete(Long id);
	boolean existsById(Long id);
	void updateDescription(Task task);
	void updateIsDone(Task task);
	List<Task> findAllByListId(Long id);
}
