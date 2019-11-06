package com.example.todorestful.model;

import java.util.List;




public class TodoList {
	private Long id;
	private Long userId;
	private String description;
	private List<Task> todos;
	
	public TodoList() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Task> getTodos() {
		return todos;
	}
	public void setTodos(List<Task> todos) {
		this.todos = todos;
	}
	
	
}
