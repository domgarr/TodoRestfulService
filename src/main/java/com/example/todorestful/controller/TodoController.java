package com.example.todorestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestful.dao.TaskDAO;
import com.example.todorestful.model.Task;

@RestController
public class TodoController {
	@Autowired
	TaskDAO todoDAO;
	
	//Maps /todo method to getTodo()
	//@RequestMapping maps all operations by default to be specific use (method="GET")
	//Binds description to the String description variable.
	@RequestMapping("/todo")
	public Task getTodo(@RequestParam(value="description", defaultValue="Empty") String description) {
		return new Task(description, false);
	}
	
	
	@GetMapping("/task/{list_id}")
	public List<Task> findAllTodosByListId(@PathVariable("list_id") Long listId) {
		return todoDAO.findAllByListId(listId);
		
	}
	
	@PostMapping("/task")
	public Task newTask(@RequestBody Task newTask) {
		return todoDAO.save(newTask);
	}
	
	@DeleteMapping("/task/{id}")
	public void deleteTask(@PathVariable Long id) {
		todoDAO.delete(id);
	}
	
	@PutMapping("/task")
	public void updateTask(@RequestBody Task task) {
		 todoDAO.updateDescription(task);
	}
	
	@PutMapping("/task/is-done")
	public void batchUpdateIsDone(@RequestBody List<Task> tasks) {
		todoDAO.batchUpdateIsDone(tasks);
	}
	
	
	

}
