package com.example.todorestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestful.dao.TodoDAO;
import com.example.todorestful.model.Task;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoController {
	@Autowired
	TodoDAO todoDAO;
	
	//Maps /todo method to getTodo()
	//@RequestMapping maps all operations by default to be specific use (method="GET")
	//Binds description to the String description variable.
	@RequestMapping("/todo")
	public Task getTodo(@RequestParam(value="description", defaultValue="Empty") String description) {
		return new Task(description, false);
	}
	
	@GetMapping("/todos/{list_id}")
	public List<Task> findAllTodosByListId(@PathVariable("list_id") Integer listId) {
		return todoDAO.findAllByListId(listId);
		
	}
	
	@PostMapping("/todos")
	public Task newTask(@RequestBody Task newTask) {
		return todoDAO.save(newTask);
	}
	
	

}
