package com.example.todorestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestful.dao.JdbcTaskListDAO;
import com.example.todorestful.model.TaskList;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoListController {
	@Autowired
	private JdbcTaskListDAO todoListDAO;
	
	
	@PostMapping("/tasklist")
	TaskList newTaskList(@RequestBody TaskList newTaskList) {
		return todoListDAO.save(newTaskList);
	}
	
	
	
	

}
