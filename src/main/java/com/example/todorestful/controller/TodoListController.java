package com.example.todorestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestful.dao.JdbcTodoListDAO;
import com.example.todorestful.model.TodoList;

@RestController
public class TodoListController {
	@Autowired
	private JdbcTodoListDAO todoListDAO;
	
	
	@PostMapping("todolists")
	TodoList newTodoList(@RequestBody TodoList newTodoList) {
		return todoListDAO.save(newTodoList);
	}
	
	

}
