package com.example.todorestful.dao;
import java.util.List;
import java.util.Optional;

import com.example.todorestful.model.Task;
import com.example.todorestful.model.TaskList;

public interface TaskListDAO  {
	TaskList save(TaskList task);
	Optional<TaskList> findById(Long id);
	List<TaskList> findAll();
	long count();
	void delete(Long id );
	boolean existsById(Long id);
	void updateName(TaskList taskList);
	List<TaskList> findAllByUserId(Long id);
	
}
